import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPlaneacion, defaultValue } from 'app/shared/model/planeacion.model';

export const ACTION_TYPES = {
  FETCH_PLANEACION_LIST: 'planeacion/FETCH_PLANEACION_LIST',
  FETCH_PLANEACION: 'planeacion/FETCH_PLANEACION',
  CREATE_PLANEACION: 'planeacion/CREATE_PLANEACION',
  UPDATE_PLANEACION: 'planeacion/UPDATE_PLANEACION',
  DELETE_PLANEACION: 'planeacion/DELETE_PLANEACION',
  RESET: 'planeacion/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPlaneacion>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PlaneacionState = Readonly<typeof initialState>;

// Reducer

export default (state: PlaneacionState = initialState, action): PlaneacionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PLANEACION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PLANEACION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PLANEACION):
    case REQUEST(ACTION_TYPES.UPDATE_PLANEACION):
    case REQUEST(ACTION_TYPES.DELETE_PLANEACION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PLANEACION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PLANEACION):
    case FAILURE(ACTION_TYPES.CREATE_PLANEACION):
    case FAILURE(ACTION_TYPES.UPDATE_PLANEACION):
    case FAILURE(ACTION_TYPES.DELETE_PLANEACION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PLANEACION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PLANEACION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PLANEACION):
    case SUCCESS(ACTION_TYPES.UPDATE_PLANEACION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PLANEACION):
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

const apiUrl = 'api/planeacions';

// Actions

export const getEntities: ICrudGetAllAction<IPlaneacion> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PLANEACION_LIST,
  payload: axios.get<IPlaneacion>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPlaneacion> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PLANEACION,
    payload: axios.get<IPlaneacion>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPlaneacion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PLANEACION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPlaneacion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PLANEACION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPlaneacion> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PLANEACION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
