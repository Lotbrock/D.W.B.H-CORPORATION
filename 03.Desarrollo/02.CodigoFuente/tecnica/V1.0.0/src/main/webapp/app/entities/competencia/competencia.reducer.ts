import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICompetencia, defaultValue } from 'app/shared/model/competencia.model';

export const ACTION_TYPES = {
  FETCH_COMPETENCIA_LIST: 'competencia/FETCH_COMPETENCIA_LIST',
  FETCH_COMPETENCIA: 'competencia/FETCH_COMPETENCIA',
  CREATE_COMPETENCIA: 'competencia/CREATE_COMPETENCIA',
  UPDATE_COMPETENCIA: 'competencia/UPDATE_COMPETENCIA',
  DELETE_COMPETENCIA: 'competencia/DELETE_COMPETENCIA',
  RESET: 'competencia/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICompetencia>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CompetenciaState = Readonly<typeof initialState>;

// Reducer

export default (state: CompetenciaState = initialState, action): CompetenciaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_COMPETENCIA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COMPETENCIA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_COMPETENCIA):
    case REQUEST(ACTION_TYPES.UPDATE_COMPETENCIA):
    case REQUEST(ACTION_TYPES.DELETE_COMPETENCIA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_COMPETENCIA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COMPETENCIA):
    case FAILURE(ACTION_TYPES.CREATE_COMPETENCIA):
    case FAILURE(ACTION_TYPES.UPDATE_COMPETENCIA):
    case FAILURE(ACTION_TYPES.DELETE_COMPETENCIA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_COMPETENCIA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_COMPETENCIA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_COMPETENCIA):
    case SUCCESS(ACTION_TYPES.UPDATE_COMPETENCIA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_COMPETENCIA):
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

const apiUrl = 'api/competencias';

// Actions

export const getEntities: ICrudGetAllAction<ICompetencia> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_COMPETENCIA_LIST,
  payload: axios.get<ICompetencia>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICompetencia> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COMPETENCIA,
    payload: axios.get<ICompetencia>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICompetencia> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COMPETENCIA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICompetencia> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COMPETENCIA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICompetencia> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COMPETENCIA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
