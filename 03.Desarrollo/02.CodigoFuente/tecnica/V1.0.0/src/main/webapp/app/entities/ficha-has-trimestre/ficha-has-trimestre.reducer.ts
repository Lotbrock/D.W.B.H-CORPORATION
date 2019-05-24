import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFichaHasTrimestre, defaultValue } from 'app/shared/model/ficha-has-trimestre.model';

export const ACTION_TYPES = {
  FETCH_FICHAHASTRIMESTRE_LIST: 'fichaHasTrimestre/FETCH_FICHAHASTRIMESTRE_LIST',
  FETCH_FICHAHASTRIMESTRE: 'fichaHasTrimestre/FETCH_FICHAHASTRIMESTRE',
  CREATE_FICHAHASTRIMESTRE: 'fichaHasTrimestre/CREATE_FICHAHASTRIMESTRE',
  UPDATE_FICHAHASTRIMESTRE: 'fichaHasTrimestre/UPDATE_FICHAHASTRIMESTRE',
  DELETE_FICHAHASTRIMESTRE: 'fichaHasTrimestre/DELETE_FICHAHASTRIMESTRE',
  RESET: 'fichaHasTrimestre/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFichaHasTrimestre>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FichaHasTrimestreState = Readonly<typeof initialState>;

// Reducer

export default (state: FichaHasTrimestreState = initialState, action): FichaHasTrimestreState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FICHAHASTRIMESTRE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FICHAHASTRIMESTRE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FICHAHASTRIMESTRE):
    case REQUEST(ACTION_TYPES.UPDATE_FICHAHASTRIMESTRE):
    case REQUEST(ACTION_TYPES.DELETE_FICHAHASTRIMESTRE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FICHAHASTRIMESTRE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FICHAHASTRIMESTRE):
    case FAILURE(ACTION_TYPES.CREATE_FICHAHASTRIMESTRE):
    case FAILURE(ACTION_TYPES.UPDATE_FICHAHASTRIMESTRE):
    case FAILURE(ACTION_TYPES.DELETE_FICHAHASTRIMESTRE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FICHAHASTRIMESTRE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FICHAHASTRIMESTRE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FICHAHASTRIMESTRE):
    case SUCCESS(ACTION_TYPES.UPDATE_FICHAHASTRIMESTRE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FICHAHASTRIMESTRE):
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

const apiUrl = 'api/ficha-has-trimestres';

// Actions

export const getEntities: ICrudGetAllAction<IFichaHasTrimestre> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FICHAHASTRIMESTRE_LIST,
  payload: axios.get<IFichaHasTrimestre>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFichaHasTrimestre> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FICHAHASTRIMESTRE,
    payload: axios.get<IFichaHasTrimestre>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFichaHasTrimestre> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FICHAHASTRIMESTRE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFichaHasTrimestre> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FICHAHASTRIMESTRE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFichaHasTrimestre> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FICHAHASTRIMESTRE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
