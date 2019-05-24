import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INivelFormacion, defaultValue } from 'app/shared/model/nivel-formacion.model';

export const ACTION_TYPES = {
  FETCH_NIVELFORMACION_LIST: 'nivelFormacion/FETCH_NIVELFORMACION_LIST',
  FETCH_NIVELFORMACION: 'nivelFormacion/FETCH_NIVELFORMACION',
  CREATE_NIVELFORMACION: 'nivelFormacion/CREATE_NIVELFORMACION',
  UPDATE_NIVELFORMACION: 'nivelFormacion/UPDATE_NIVELFORMACION',
  DELETE_NIVELFORMACION: 'nivelFormacion/DELETE_NIVELFORMACION',
  RESET: 'nivelFormacion/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INivelFormacion>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type NivelFormacionState = Readonly<typeof initialState>;

// Reducer

export default (state: NivelFormacionState = initialState, action): NivelFormacionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NIVELFORMACION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NIVELFORMACION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_NIVELFORMACION):
    case REQUEST(ACTION_TYPES.UPDATE_NIVELFORMACION):
    case REQUEST(ACTION_TYPES.DELETE_NIVELFORMACION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_NIVELFORMACION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NIVELFORMACION):
    case FAILURE(ACTION_TYPES.CREATE_NIVELFORMACION):
    case FAILURE(ACTION_TYPES.UPDATE_NIVELFORMACION):
    case FAILURE(ACTION_TYPES.DELETE_NIVELFORMACION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_NIVELFORMACION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NIVELFORMACION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_NIVELFORMACION):
    case SUCCESS(ACTION_TYPES.UPDATE_NIVELFORMACION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_NIVELFORMACION):
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

const apiUrl = 'api/nivel-formacions';

// Actions

export const getEntities: ICrudGetAllAction<INivelFormacion> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NIVELFORMACION_LIST,
  payload: axios.get<INivelFormacion>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<INivelFormacion> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NIVELFORMACION,
    payload: axios.get<INivelFormacion>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<INivelFormacion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NIVELFORMACION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INivelFormacion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NIVELFORMACION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<INivelFormacion> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NIVELFORMACION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
