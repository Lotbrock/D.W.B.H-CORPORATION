import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILimitacionAmbiente, defaultValue } from 'app/shared/model/limitacion-ambiente.model';

export const ACTION_TYPES = {
  FETCH_LIMITACIONAMBIENTE_LIST: 'limitacionAmbiente/FETCH_LIMITACIONAMBIENTE_LIST',
  FETCH_LIMITACIONAMBIENTE: 'limitacionAmbiente/FETCH_LIMITACIONAMBIENTE',
  CREATE_LIMITACIONAMBIENTE: 'limitacionAmbiente/CREATE_LIMITACIONAMBIENTE',
  UPDATE_LIMITACIONAMBIENTE: 'limitacionAmbiente/UPDATE_LIMITACIONAMBIENTE',
  DELETE_LIMITACIONAMBIENTE: 'limitacionAmbiente/DELETE_LIMITACIONAMBIENTE',
  RESET: 'limitacionAmbiente/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILimitacionAmbiente>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type LimitacionAmbienteState = Readonly<typeof initialState>;

// Reducer

export default (state: LimitacionAmbienteState = initialState, action): LimitacionAmbienteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LIMITACIONAMBIENTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LIMITACIONAMBIENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LIMITACIONAMBIENTE):
    case REQUEST(ACTION_TYPES.UPDATE_LIMITACIONAMBIENTE):
    case REQUEST(ACTION_TYPES.DELETE_LIMITACIONAMBIENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LIMITACIONAMBIENTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LIMITACIONAMBIENTE):
    case FAILURE(ACTION_TYPES.CREATE_LIMITACIONAMBIENTE):
    case FAILURE(ACTION_TYPES.UPDATE_LIMITACIONAMBIENTE):
    case FAILURE(ACTION_TYPES.DELETE_LIMITACIONAMBIENTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LIMITACIONAMBIENTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LIMITACIONAMBIENTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LIMITACIONAMBIENTE):
    case SUCCESS(ACTION_TYPES.UPDATE_LIMITACIONAMBIENTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LIMITACIONAMBIENTE):
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

const apiUrl = 'api/limitacion-ambientes';

// Actions

export const getEntities: ICrudGetAllAction<ILimitacionAmbiente> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LIMITACIONAMBIENTE_LIST,
  payload: axios.get<ILimitacionAmbiente>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ILimitacionAmbiente> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LIMITACIONAMBIENTE,
    payload: axios.get<ILimitacionAmbiente>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILimitacionAmbiente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LIMITACIONAMBIENTE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILimitacionAmbiente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LIMITACIONAMBIENTE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILimitacionAmbiente> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LIMITACIONAMBIENTE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
