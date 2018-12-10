import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IResultadoAprendizaje, defaultValue } from 'app/shared/model/resultado-aprendizaje.model';

export const ACTION_TYPES = {
  FETCH_RESULTADOAPRENDIZAJE_LIST: 'resultadoAprendizaje/FETCH_RESULTADOAPRENDIZAJE_LIST',
  FETCH_RESULTADOAPRENDIZAJE: 'resultadoAprendizaje/FETCH_RESULTADOAPRENDIZAJE',
  CREATE_RESULTADOAPRENDIZAJE: 'resultadoAprendizaje/CREATE_RESULTADOAPRENDIZAJE',
  UPDATE_RESULTADOAPRENDIZAJE: 'resultadoAprendizaje/UPDATE_RESULTADOAPRENDIZAJE',
  DELETE_RESULTADOAPRENDIZAJE: 'resultadoAprendizaje/DELETE_RESULTADOAPRENDIZAJE',
  RESET: 'resultadoAprendizaje/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IResultadoAprendizaje>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ResultadoAprendizajeState = Readonly<typeof initialState>;

// Reducer

export default (state: ResultadoAprendizajeState = initialState, action): ResultadoAprendizajeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RESULTADOAPRENDIZAJE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RESULTADOAPRENDIZAJE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_RESULTADOAPRENDIZAJE):
    case REQUEST(ACTION_TYPES.UPDATE_RESULTADOAPRENDIZAJE):
    case REQUEST(ACTION_TYPES.DELETE_RESULTADOAPRENDIZAJE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_RESULTADOAPRENDIZAJE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RESULTADOAPRENDIZAJE):
    case FAILURE(ACTION_TYPES.CREATE_RESULTADOAPRENDIZAJE):
    case FAILURE(ACTION_TYPES.UPDATE_RESULTADOAPRENDIZAJE):
    case FAILURE(ACTION_TYPES.DELETE_RESULTADOAPRENDIZAJE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_RESULTADOAPRENDIZAJE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_RESULTADOAPRENDIZAJE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_RESULTADOAPRENDIZAJE):
    case SUCCESS(ACTION_TYPES.UPDATE_RESULTADOAPRENDIZAJE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_RESULTADOAPRENDIZAJE):
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

const apiUrl = 'api/resultado-aprendizajes';

// Actions

export const getEntities: ICrudGetAllAction<IResultadoAprendizaje> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_RESULTADOAPRENDIZAJE_LIST,
  payload: axios.get<IResultadoAprendizaje>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IResultadoAprendizaje> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RESULTADOAPRENDIZAJE,
    payload: axios.get<IResultadoAprendizaje>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IResultadoAprendizaje> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RESULTADOAPRENDIZAJE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IResultadoAprendizaje> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RESULTADOAPRENDIZAJE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IResultadoAprendizaje> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RESULTADOAPRENDIZAJE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
