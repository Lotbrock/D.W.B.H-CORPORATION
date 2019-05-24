import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAmbiente, defaultValue } from 'app/shared/model/ambiente.model';

export const ACTION_TYPES = {
  FETCH_AMBIENTE_LIST: 'ambiente/FETCH_AMBIENTE_LIST',
  FETCH_AMBIENTE: 'ambiente/FETCH_AMBIENTE',
  CREATE_AMBIENTE: 'ambiente/CREATE_AMBIENTE',
  UPDATE_AMBIENTE: 'ambiente/UPDATE_AMBIENTE',
  DELETE_AMBIENTE: 'ambiente/DELETE_AMBIENTE',
  RESET: 'ambiente/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAmbiente>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AmbienteState = Readonly<typeof initialState>;

// Reducer

export default (state: AmbienteState = initialState, action): AmbienteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_AMBIENTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_AMBIENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_AMBIENTE):
    case REQUEST(ACTION_TYPES.UPDATE_AMBIENTE):
    case REQUEST(ACTION_TYPES.DELETE_AMBIENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_AMBIENTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_AMBIENTE):
    case FAILURE(ACTION_TYPES.CREATE_AMBIENTE):
    case FAILURE(ACTION_TYPES.UPDATE_AMBIENTE):
    case FAILURE(ACTION_TYPES.DELETE_AMBIENTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_AMBIENTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_AMBIENTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_AMBIENTE):
    case SUCCESS(ACTION_TYPES.UPDATE_AMBIENTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_AMBIENTE):
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

const apiUrl = 'api/ambientes';

// Actions

export const getEntities: ICrudGetAllAction<IAmbiente> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_AMBIENTE_LIST,
  payload: axios.get<IAmbiente>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAmbiente> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_AMBIENTE,
    payload: axios.get<IAmbiente>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAmbiente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_AMBIENTE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAmbiente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_AMBIENTE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAmbiente> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_AMBIENTE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
