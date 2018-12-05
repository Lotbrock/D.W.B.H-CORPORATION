import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IModalidad, defaultValue } from 'app/shared/model/modalidad.model';

export const ACTION_TYPES = {
  FETCH_MODALIDAD_LIST: 'modalidad/FETCH_MODALIDAD_LIST',
  FETCH_MODALIDAD: 'modalidad/FETCH_MODALIDAD',
  CREATE_MODALIDAD: 'modalidad/CREATE_MODALIDAD',
  UPDATE_MODALIDAD: 'modalidad/UPDATE_MODALIDAD',
  DELETE_MODALIDAD: 'modalidad/DELETE_MODALIDAD',
  RESET: 'modalidad/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IModalidad>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ModalidadState = Readonly<typeof initialState>;

// Reducer

export default (state: ModalidadState = initialState, action): ModalidadState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MODALIDAD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MODALIDAD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_MODALIDAD):
    case REQUEST(ACTION_TYPES.UPDATE_MODALIDAD):
    case REQUEST(ACTION_TYPES.DELETE_MODALIDAD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_MODALIDAD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MODALIDAD):
    case FAILURE(ACTION_TYPES.CREATE_MODALIDAD):
    case FAILURE(ACTION_TYPES.UPDATE_MODALIDAD):
    case FAILURE(ACTION_TYPES.DELETE_MODALIDAD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_MODALIDAD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MODALIDAD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_MODALIDAD):
    case SUCCESS(ACTION_TYPES.UPDATE_MODALIDAD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_MODALIDAD):
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

const apiUrl = 'api/modalidads';

// Actions

export const getEntities: ICrudGetAllAction<IModalidad> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MODALIDAD_LIST,
  payload: axios.get<IModalidad>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IModalidad> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MODALIDAD,
    payload: axios.get<IModalidad>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IModalidad> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MODALIDAD,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IModalidad> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MODALIDAD,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IModalidad> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MODALIDAD,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
