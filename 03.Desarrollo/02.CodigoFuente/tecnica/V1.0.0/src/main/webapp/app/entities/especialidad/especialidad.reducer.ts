import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEspecialidad, defaultValue } from 'app/shared/model/especialidad.model';

export const ACTION_TYPES = {
  FETCH_ESPECIALIDAD_LIST: 'especialidad/FETCH_ESPECIALIDAD_LIST',
  FETCH_ESPECIALIDAD: 'especialidad/FETCH_ESPECIALIDAD',
  CREATE_ESPECIALIDAD: 'especialidad/CREATE_ESPECIALIDAD',
  UPDATE_ESPECIALIDAD: 'especialidad/UPDATE_ESPECIALIDAD',
  DELETE_ESPECIALIDAD: 'especialidad/DELETE_ESPECIALIDAD',
  RESET: 'especialidad/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEspecialidad>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EspecialidadState = Readonly<typeof initialState>;

// Reducer

export default (state: EspecialidadState = initialState, action): EspecialidadState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ESPECIALIDAD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ESPECIALIDAD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ESPECIALIDAD):
    case REQUEST(ACTION_TYPES.UPDATE_ESPECIALIDAD):
    case REQUEST(ACTION_TYPES.DELETE_ESPECIALIDAD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ESPECIALIDAD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ESPECIALIDAD):
    case FAILURE(ACTION_TYPES.CREATE_ESPECIALIDAD):
    case FAILURE(ACTION_TYPES.UPDATE_ESPECIALIDAD):
    case FAILURE(ACTION_TYPES.DELETE_ESPECIALIDAD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ESPECIALIDAD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ESPECIALIDAD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ESPECIALIDAD):
    case SUCCESS(ACTION_TYPES.UPDATE_ESPECIALIDAD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ESPECIALIDAD):
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

const apiUrl = 'api/especialidads';

// Actions

export const getEntities: ICrudGetAllAction<IEspecialidad> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ESPECIALIDAD_LIST,
  payload: axios.get<IEspecialidad>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEspecialidad> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ESPECIALIDAD,
    payload: axios.get<IEspecialidad>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEspecialidad> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ESPECIALIDAD,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEspecialidad> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ESPECIALIDAD,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEspecialidad> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ESPECIALIDAD,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
