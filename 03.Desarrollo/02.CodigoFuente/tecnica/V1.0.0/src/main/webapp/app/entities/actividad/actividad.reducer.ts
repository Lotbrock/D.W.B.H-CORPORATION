import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IActividad, defaultValue } from 'app/shared/model/actividad.model';

export const ACTION_TYPES = {
  FETCH_ACTIVIDAD_LIST: 'actividad/FETCH_ACTIVIDAD_LIST',
  FETCH_ACTIVIDAD: 'actividad/FETCH_ACTIVIDAD',
  CREATE_ACTIVIDAD: 'actividad/CREATE_ACTIVIDAD',
  UPDATE_ACTIVIDAD: 'actividad/UPDATE_ACTIVIDAD',
  DELETE_ACTIVIDAD: 'actividad/DELETE_ACTIVIDAD',
  RESET: 'actividad/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IActividad>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ActividadState = Readonly<typeof initialState>;

// Reducer

export default (state: ActividadState = initialState, action): ActividadState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ACTIVIDAD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ACTIVIDAD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ACTIVIDAD):
    case REQUEST(ACTION_TYPES.UPDATE_ACTIVIDAD):
    case REQUEST(ACTION_TYPES.DELETE_ACTIVIDAD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ACTIVIDAD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ACTIVIDAD):
    case FAILURE(ACTION_TYPES.CREATE_ACTIVIDAD):
    case FAILURE(ACTION_TYPES.UPDATE_ACTIVIDAD):
    case FAILURE(ACTION_TYPES.DELETE_ACTIVIDAD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ACTIVIDAD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ACTIVIDAD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ACTIVIDAD):
    case SUCCESS(ACTION_TYPES.UPDATE_ACTIVIDAD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ACTIVIDAD):
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

const apiUrl = 'api/actividads';

// Actions

export const getEntities: ICrudGetAllAction<IActividad> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ACTIVIDAD_LIST,
  payload: axios.get<IActividad>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IActividad> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ACTIVIDAD,
    payload: axios.get<IActividad>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IActividad> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ACTIVIDAD,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IActividad> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ACTIVIDAD,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IActividad> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ACTIVIDAD,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
