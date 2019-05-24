import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDia, defaultValue } from 'app/shared/model/dia.model';

export const ACTION_TYPES = {
  FETCH_DIA_LIST: 'dia/FETCH_DIA_LIST',
  FETCH_DIA: 'dia/FETCH_DIA',
  CREATE_DIA: 'dia/CREATE_DIA',
  UPDATE_DIA: 'dia/UPDATE_DIA',
  DELETE_DIA: 'dia/DELETE_DIA',
  RESET: 'dia/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDia>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DiaState = Readonly<typeof initialState>;

// Reducer

export default (state: DiaState = initialState, action): DiaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DIA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DIA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DIA):
    case REQUEST(ACTION_TYPES.UPDATE_DIA):
    case REQUEST(ACTION_TYPES.DELETE_DIA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DIA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DIA):
    case FAILURE(ACTION_TYPES.CREATE_DIA):
    case FAILURE(ACTION_TYPES.UPDATE_DIA):
    case FAILURE(ACTION_TYPES.DELETE_DIA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DIA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DIA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DIA):
    case SUCCESS(ACTION_TYPES.UPDATE_DIA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DIA):
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

const apiUrl = 'api/dias';

// Actions

export const getEntities: ICrudGetAllAction<IDia> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DIA_LIST,
  payload: axios.get<IDia>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDia> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DIA,
    payload: axios.get<IDia>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDia> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DIA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDia> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DIA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDia> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DIA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
