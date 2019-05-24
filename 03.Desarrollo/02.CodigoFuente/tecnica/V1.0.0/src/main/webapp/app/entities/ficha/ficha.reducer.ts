import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFicha, defaultValue } from 'app/shared/model/ficha.model';

export const ACTION_TYPES = {
  FETCH_FICHA_LIST: 'ficha/FETCH_FICHA_LIST',
  FETCH_FICHA: 'ficha/FETCH_FICHA',
  CREATE_FICHA: 'ficha/CREATE_FICHA',
  UPDATE_FICHA: 'ficha/UPDATE_FICHA',
  DELETE_FICHA: 'ficha/DELETE_FICHA',
  RESET: 'ficha/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFicha>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FichaState = Readonly<typeof initialState>;

// Reducer

export default (state: FichaState = initialState, action): FichaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FICHA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FICHA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FICHA):
    case REQUEST(ACTION_TYPES.UPDATE_FICHA):
    case REQUEST(ACTION_TYPES.DELETE_FICHA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FICHA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FICHA):
    case FAILURE(ACTION_TYPES.CREATE_FICHA):
    case FAILURE(ACTION_TYPES.UPDATE_FICHA):
    case FAILURE(ACTION_TYPES.DELETE_FICHA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FICHA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FICHA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FICHA):
    case SUCCESS(ACTION_TYPES.UPDATE_FICHA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FICHA):
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

const apiUrl = 'api/fichas';

// Actions

export const getEntities: ICrudGetAllAction<IFicha> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FICHA_LIST,
  payload: axios.get<IFicha>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFicha> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FICHA,
    payload: axios.get<IFicha>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFicha> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FICHA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFicha> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FICHA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFicha> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FICHA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
