import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISede, defaultValue } from 'app/shared/model/sede.model';

export const ACTION_TYPES = {
  FETCH_SEDE_LIST: 'sede/FETCH_SEDE_LIST',
  FETCH_SEDE: 'sede/FETCH_SEDE',
  CREATE_SEDE: 'sede/CREATE_SEDE',
  UPDATE_SEDE: 'sede/UPDATE_SEDE',
  DELETE_SEDE: 'sede/DELETE_SEDE',
  RESET: 'sede/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISede>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type SedeState = Readonly<typeof initialState>;

// Reducer

export default (state: SedeState = initialState, action): SedeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SEDE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SEDE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SEDE):
    case REQUEST(ACTION_TYPES.UPDATE_SEDE):
    case REQUEST(ACTION_TYPES.DELETE_SEDE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SEDE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SEDE):
    case FAILURE(ACTION_TYPES.CREATE_SEDE):
    case FAILURE(ACTION_TYPES.UPDATE_SEDE):
    case FAILURE(ACTION_TYPES.DELETE_SEDE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SEDE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SEDE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SEDE):
    case SUCCESS(ACTION_TYPES.UPDATE_SEDE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SEDE):
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

const apiUrl = 'api/sedes';

// Actions

export const getEntities: ICrudGetAllAction<ISede> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SEDE_LIST,
  payload: axios.get<ISede>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ISede> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SEDE,
    payload: axios.get<ISede>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISede> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SEDE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISede> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SEDE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISede> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SEDE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
