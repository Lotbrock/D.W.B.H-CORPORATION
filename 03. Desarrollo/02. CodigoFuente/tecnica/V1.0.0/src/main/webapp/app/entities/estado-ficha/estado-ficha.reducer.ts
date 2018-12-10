import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEstadoFicha, defaultValue } from 'app/shared/model/estado-ficha.model';

export const ACTION_TYPES = {
  FETCH_ESTADOFICHA_LIST: 'estadoFicha/FETCH_ESTADOFICHA_LIST',
  FETCH_ESTADOFICHA: 'estadoFicha/FETCH_ESTADOFICHA',
  CREATE_ESTADOFICHA: 'estadoFicha/CREATE_ESTADOFICHA',
  UPDATE_ESTADOFICHA: 'estadoFicha/UPDATE_ESTADOFICHA',
  DELETE_ESTADOFICHA: 'estadoFicha/DELETE_ESTADOFICHA',
  RESET: 'estadoFicha/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEstadoFicha>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EstadoFichaState = Readonly<typeof initialState>;

// Reducer

export default (state: EstadoFichaState = initialState, action): EstadoFichaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ESTADOFICHA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ESTADOFICHA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ESTADOFICHA):
    case REQUEST(ACTION_TYPES.UPDATE_ESTADOFICHA):
    case REQUEST(ACTION_TYPES.DELETE_ESTADOFICHA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ESTADOFICHA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ESTADOFICHA):
    case FAILURE(ACTION_TYPES.CREATE_ESTADOFICHA):
    case FAILURE(ACTION_TYPES.UPDATE_ESTADOFICHA):
    case FAILURE(ACTION_TYPES.DELETE_ESTADOFICHA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ESTADOFICHA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ESTADOFICHA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ESTADOFICHA):
    case SUCCESS(ACTION_TYPES.UPDATE_ESTADOFICHA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ESTADOFICHA):
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

const apiUrl = 'api/estado-fichas';

// Actions

export const getEntities: ICrudGetAllAction<IEstadoFicha> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ESTADOFICHA_LIST,
  payload: axios.get<IEstadoFicha>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEstadoFicha> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ESTADOFICHA,
    payload: axios.get<IEstadoFicha>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEstadoFicha> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ESTADOFICHA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEstadoFicha> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ESTADOFICHA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEstadoFicha> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ESTADOFICHA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
