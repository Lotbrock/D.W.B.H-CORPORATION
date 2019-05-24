import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITipoAmbiente, defaultValue } from 'app/shared/model/tipo-ambiente.model';

export const ACTION_TYPES = {
  FETCH_TIPOAMBIENTE_LIST: 'tipoAmbiente/FETCH_TIPOAMBIENTE_LIST',
  FETCH_TIPOAMBIENTE: 'tipoAmbiente/FETCH_TIPOAMBIENTE',
  CREATE_TIPOAMBIENTE: 'tipoAmbiente/CREATE_TIPOAMBIENTE',
  UPDATE_TIPOAMBIENTE: 'tipoAmbiente/UPDATE_TIPOAMBIENTE',
  DELETE_TIPOAMBIENTE: 'tipoAmbiente/DELETE_TIPOAMBIENTE',
  RESET: 'tipoAmbiente/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITipoAmbiente>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TipoAmbienteState = Readonly<typeof initialState>;

// Reducer

export default (state: TipoAmbienteState = initialState, action): TipoAmbienteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TIPOAMBIENTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TIPOAMBIENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TIPOAMBIENTE):
    case REQUEST(ACTION_TYPES.UPDATE_TIPOAMBIENTE):
    case REQUEST(ACTION_TYPES.DELETE_TIPOAMBIENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TIPOAMBIENTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TIPOAMBIENTE):
    case FAILURE(ACTION_TYPES.CREATE_TIPOAMBIENTE):
    case FAILURE(ACTION_TYPES.UPDATE_TIPOAMBIENTE):
    case FAILURE(ACTION_TYPES.DELETE_TIPOAMBIENTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TIPOAMBIENTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TIPOAMBIENTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TIPOAMBIENTE):
    case SUCCESS(ACTION_TYPES.UPDATE_TIPOAMBIENTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TIPOAMBIENTE):
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

const apiUrl = 'api/tipo-ambientes';

// Actions

export const getEntities: ICrudGetAllAction<ITipoAmbiente> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TIPOAMBIENTE_LIST,
  payload: axios.get<ITipoAmbiente>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITipoAmbiente> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TIPOAMBIENTE,
    payload: axios.get<ITipoAmbiente>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITipoAmbiente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TIPOAMBIENTE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITipoAmbiente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TIPOAMBIENTE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITipoAmbiente> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TIPOAMBIENTE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
