import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEstadoFormacion, defaultValue } from 'app/shared/model/estado-formacion.model';

export const ACTION_TYPES = {
  FETCH_ESTADOFORMACION_LIST: 'estadoFormacion/FETCH_ESTADOFORMACION_LIST',
  FETCH_ESTADOFORMACION: 'estadoFormacion/FETCH_ESTADOFORMACION',
  CREATE_ESTADOFORMACION: 'estadoFormacion/CREATE_ESTADOFORMACION',
  UPDATE_ESTADOFORMACION: 'estadoFormacion/UPDATE_ESTADOFORMACION',
  DELETE_ESTADOFORMACION: 'estadoFormacion/DELETE_ESTADOFORMACION',
  RESET: 'estadoFormacion/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEstadoFormacion>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EstadoFormacionState = Readonly<typeof initialState>;

// Reducer

export default (state: EstadoFormacionState = initialState, action): EstadoFormacionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ESTADOFORMACION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ESTADOFORMACION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ESTADOFORMACION):
    case REQUEST(ACTION_TYPES.UPDATE_ESTADOFORMACION):
    case REQUEST(ACTION_TYPES.DELETE_ESTADOFORMACION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ESTADOFORMACION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ESTADOFORMACION):
    case FAILURE(ACTION_TYPES.CREATE_ESTADOFORMACION):
    case FAILURE(ACTION_TYPES.UPDATE_ESTADOFORMACION):
    case FAILURE(ACTION_TYPES.DELETE_ESTADOFORMACION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ESTADOFORMACION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ESTADOFORMACION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ESTADOFORMACION):
    case SUCCESS(ACTION_TYPES.UPDATE_ESTADOFORMACION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ESTADOFORMACION):
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

const apiUrl = 'api/estado-formacions';

// Actions

export const getEntities: ICrudGetAllAction<IEstadoFormacion> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ESTADOFORMACION_LIST,
  payload: axios.get<IEstadoFormacion>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEstadoFormacion> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ESTADOFORMACION,
    payload: axios.get<IEstadoFormacion>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEstadoFormacion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ESTADOFORMACION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEstadoFormacion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ESTADOFORMACION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEstadoFormacion> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ESTADOFORMACION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
