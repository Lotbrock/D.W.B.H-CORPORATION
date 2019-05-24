import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPrograma, defaultValue } from 'app/shared/model/programa.model';

export const ACTION_TYPES = {
  FETCH_PROGRAMA_LIST: 'programa/FETCH_PROGRAMA_LIST',
  FETCH_PROGRAMA: 'programa/FETCH_PROGRAMA',
  CREATE_PROGRAMA: 'programa/CREATE_PROGRAMA',
  UPDATE_PROGRAMA: 'programa/UPDATE_PROGRAMA',
  DELETE_PROGRAMA: 'programa/DELETE_PROGRAMA',
  RESET: 'programa/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPrograma>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ProgramaState = Readonly<typeof initialState>;

// Reducer

export default (state: ProgramaState = initialState, action): ProgramaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROGRAMA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROGRAMA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PROGRAMA):
    case REQUEST(ACTION_TYPES.UPDATE_PROGRAMA):
    case REQUEST(ACTION_TYPES.DELETE_PROGRAMA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PROGRAMA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROGRAMA):
    case FAILURE(ACTION_TYPES.CREATE_PROGRAMA):
    case FAILURE(ACTION_TYPES.UPDATE_PROGRAMA):
    case FAILURE(ACTION_TYPES.DELETE_PROGRAMA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROGRAMA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROGRAMA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROGRAMA):
    case SUCCESS(ACTION_TYPES.UPDATE_PROGRAMA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROGRAMA):
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

const apiUrl = 'api/programas';

// Actions

export const getEntities: ICrudGetAllAction<IPrograma> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PROGRAMA_LIST,
  payload: axios.get<IPrograma>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPrograma> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROGRAMA,
    payload: axios.get<IPrograma>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPrograma> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROGRAMA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPrograma> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROGRAMA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPrograma> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROGRAMA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
