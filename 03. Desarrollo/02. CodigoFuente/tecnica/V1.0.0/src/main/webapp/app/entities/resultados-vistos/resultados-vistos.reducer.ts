import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IResultadosVistos, defaultValue } from 'app/shared/model/resultados-vistos.model';

export const ACTION_TYPES = {
  FETCH_RESULTADOSVISTOS_LIST: 'resultadosVistos/FETCH_RESULTADOSVISTOS_LIST',
  FETCH_RESULTADOSVISTOS: 'resultadosVistos/FETCH_RESULTADOSVISTOS',
  CREATE_RESULTADOSVISTOS: 'resultadosVistos/CREATE_RESULTADOSVISTOS',
  UPDATE_RESULTADOSVISTOS: 'resultadosVistos/UPDATE_RESULTADOSVISTOS',
  DELETE_RESULTADOSVISTOS: 'resultadosVistos/DELETE_RESULTADOSVISTOS',
  RESET: 'resultadosVistos/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IResultadosVistos>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ResultadosVistosState = Readonly<typeof initialState>;

// Reducer

export default (state: ResultadosVistosState = initialState, action): ResultadosVistosState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RESULTADOSVISTOS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RESULTADOSVISTOS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_RESULTADOSVISTOS):
    case REQUEST(ACTION_TYPES.UPDATE_RESULTADOSVISTOS):
    case REQUEST(ACTION_TYPES.DELETE_RESULTADOSVISTOS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_RESULTADOSVISTOS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RESULTADOSVISTOS):
    case FAILURE(ACTION_TYPES.CREATE_RESULTADOSVISTOS):
    case FAILURE(ACTION_TYPES.UPDATE_RESULTADOSVISTOS):
    case FAILURE(ACTION_TYPES.DELETE_RESULTADOSVISTOS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_RESULTADOSVISTOS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_RESULTADOSVISTOS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_RESULTADOSVISTOS):
    case SUCCESS(ACTION_TYPES.UPDATE_RESULTADOSVISTOS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_RESULTADOSVISTOS):
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

const apiUrl = 'api/resultados-vistos';

// Actions

export const getEntities: ICrudGetAllAction<IResultadosVistos> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_RESULTADOSVISTOS_LIST,
  payload: axios.get<IResultadosVistos>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IResultadosVistos> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RESULTADOSVISTOS,
    payload: axios.get<IResultadosVistos>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IResultadosVistos> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RESULTADOSVISTOS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IResultadosVistos> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RESULTADOSVISTOS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IResultadosVistos> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RESULTADOSVISTOS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
