import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITrimestre, defaultValue } from 'app/shared/model/trimestre.model';

export const ACTION_TYPES = {
  FETCH_TRIMESTRE_LIST: 'trimestre/FETCH_TRIMESTRE_LIST',
  FETCH_TRIMESTRE: 'trimestre/FETCH_TRIMESTRE',
  CREATE_TRIMESTRE: 'trimestre/CREATE_TRIMESTRE',
  UPDATE_TRIMESTRE: 'trimestre/UPDATE_TRIMESTRE',
  DELETE_TRIMESTRE: 'trimestre/DELETE_TRIMESTRE',
  RESET: 'trimestre/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITrimestre>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TrimestreState = Readonly<typeof initialState>;

// Reducer

export default (state: TrimestreState = initialState, action): TrimestreState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TRIMESTRE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TRIMESTRE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TRIMESTRE):
    case REQUEST(ACTION_TYPES.UPDATE_TRIMESTRE):
    case REQUEST(ACTION_TYPES.DELETE_TRIMESTRE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TRIMESTRE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TRIMESTRE):
    case FAILURE(ACTION_TYPES.CREATE_TRIMESTRE):
    case FAILURE(ACTION_TYPES.UPDATE_TRIMESTRE):
    case FAILURE(ACTION_TYPES.DELETE_TRIMESTRE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRIMESTRE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRIMESTRE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TRIMESTRE):
    case SUCCESS(ACTION_TYPES.UPDATE_TRIMESTRE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TRIMESTRE):
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

const apiUrl = 'api/trimestres';

// Actions

export const getEntities: ICrudGetAllAction<ITrimestre> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TRIMESTRE_LIST,
  payload: axios.get<ITrimestre>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITrimestre> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TRIMESTRE,
    payload: axios.get<ITrimestre>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITrimestre> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TRIMESTRE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITrimestre> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TRIMESTRE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITrimestre> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TRIMESTRE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
