import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAprendiz, defaultValue } from 'app/shared/model/aprendiz.model';

export const ACTION_TYPES = {
  FETCH_APRENDIZ_LIST: 'aprendiz/FETCH_APRENDIZ_LIST',
  FETCH_APRENDIZ: 'aprendiz/FETCH_APRENDIZ',
  CREATE_APRENDIZ: 'aprendiz/CREATE_APRENDIZ',
  UPDATE_APRENDIZ: 'aprendiz/UPDATE_APRENDIZ',
  DELETE_APRENDIZ: 'aprendiz/DELETE_APRENDIZ',
  RESET: 'aprendiz/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAprendiz>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AprendizState = Readonly<typeof initialState>;

// Reducer

export default (state: AprendizState = initialState, action): AprendizState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_APRENDIZ_LIST):
    case REQUEST(ACTION_TYPES.FETCH_APRENDIZ):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_APRENDIZ):
    case REQUEST(ACTION_TYPES.UPDATE_APRENDIZ):
    case REQUEST(ACTION_TYPES.DELETE_APRENDIZ):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_APRENDIZ_LIST):
    case FAILURE(ACTION_TYPES.FETCH_APRENDIZ):
    case FAILURE(ACTION_TYPES.CREATE_APRENDIZ):
    case FAILURE(ACTION_TYPES.UPDATE_APRENDIZ):
    case FAILURE(ACTION_TYPES.DELETE_APRENDIZ):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_APRENDIZ_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_APRENDIZ):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_APRENDIZ):
    case SUCCESS(ACTION_TYPES.UPDATE_APRENDIZ):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_APRENDIZ):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/aprendizs';

// Actions

export const getEntities: ICrudGetAllAction<IAprendiz> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_APRENDIZ_LIST,
  payload: axios.get<IAprendiz>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAprendiz> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_APRENDIZ,
    payload: axios.get<IAprendiz>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAprendiz> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_APRENDIZ,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAprendiz> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_APRENDIZ,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAprendiz> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_APRENDIZ,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
