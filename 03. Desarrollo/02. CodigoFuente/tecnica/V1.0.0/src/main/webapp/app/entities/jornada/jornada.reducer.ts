import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IJornada, defaultValue } from 'app/shared/model/jornada.model';

export const ACTION_TYPES = {
  FETCH_JORNADA_LIST: 'jornada/FETCH_JORNADA_LIST',
  FETCH_JORNADA: 'jornada/FETCH_JORNADA',
  CREATE_JORNADA: 'jornada/CREATE_JORNADA',
  UPDATE_JORNADA: 'jornada/UPDATE_JORNADA',
  DELETE_JORNADA: 'jornada/DELETE_JORNADA',
  RESET: 'jornada/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IJornada>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type JornadaState = Readonly<typeof initialState>;

// Reducer

export default (state: JornadaState = initialState, action): JornadaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_JORNADA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_JORNADA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_JORNADA):
    case REQUEST(ACTION_TYPES.UPDATE_JORNADA):
    case REQUEST(ACTION_TYPES.DELETE_JORNADA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_JORNADA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_JORNADA):
    case FAILURE(ACTION_TYPES.CREATE_JORNADA):
    case FAILURE(ACTION_TYPES.UPDATE_JORNADA):
    case FAILURE(ACTION_TYPES.DELETE_JORNADA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_JORNADA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_JORNADA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_JORNADA):
    case SUCCESS(ACTION_TYPES.UPDATE_JORNADA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_JORNADA):
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

const apiUrl = 'api/jornadas';

// Actions

export const getEntities: ICrudGetAllAction<IJornada> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_JORNADA_LIST,
  payload: axios.get<IJornada>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IJornada> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_JORNADA,
    payload: axios.get<IJornada>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IJornada> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_JORNADA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IJornada> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_JORNADA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IJornada> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_JORNADA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
