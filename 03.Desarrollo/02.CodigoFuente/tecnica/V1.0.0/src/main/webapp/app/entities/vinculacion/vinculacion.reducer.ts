import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IVinculacion, defaultValue } from 'app/shared/model/vinculacion.model';

export const ACTION_TYPES = {
  FETCH_VINCULACION_LIST: 'vinculacion/FETCH_VINCULACION_LIST',
  FETCH_VINCULACION: 'vinculacion/FETCH_VINCULACION',
  CREATE_VINCULACION: 'vinculacion/CREATE_VINCULACION',
  UPDATE_VINCULACION: 'vinculacion/UPDATE_VINCULACION',
  DELETE_VINCULACION: 'vinculacion/DELETE_VINCULACION',
  RESET: 'vinculacion/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IVinculacion>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type VinculacionState = Readonly<typeof initialState>;

// Reducer

export default (state: VinculacionState = initialState, action): VinculacionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_VINCULACION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_VINCULACION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_VINCULACION):
    case REQUEST(ACTION_TYPES.UPDATE_VINCULACION):
    case REQUEST(ACTION_TYPES.DELETE_VINCULACION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_VINCULACION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_VINCULACION):
    case FAILURE(ACTION_TYPES.CREATE_VINCULACION):
    case FAILURE(ACTION_TYPES.UPDATE_VINCULACION):
    case FAILURE(ACTION_TYPES.DELETE_VINCULACION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_VINCULACION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_VINCULACION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_VINCULACION):
    case SUCCESS(ACTION_TYPES.UPDATE_VINCULACION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_VINCULACION):
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

const apiUrl = 'api/vinculacions';

// Actions

export const getEntities: ICrudGetAllAction<IVinculacion> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_VINCULACION_LIST,
  payload: axios.get<IVinculacion>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IVinculacion> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_VINCULACION,
    payload: axios.get<IVinculacion>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IVinculacion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_VINCULACION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IVinculacion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_VINCULACION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IVinculacion> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_VINCULACION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
