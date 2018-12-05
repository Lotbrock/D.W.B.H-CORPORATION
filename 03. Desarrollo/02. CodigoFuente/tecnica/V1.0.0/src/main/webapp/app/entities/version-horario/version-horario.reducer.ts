import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IVersionHorario, defaultValue } from 'app/shared/model/version-horario.model';

export const ACTION_TYPES = {
  FETCH_VERSIONHORARIO_LIST: 'versionHorario/FETCH_VERSIONHORARIO_LIST',
  FETCH_VERSIONHORARIO: 'versionHorario/FETCH_VERSIONHORARIO',
  CREATE_VERSIONHORARIO: 'versionHorario/CREATE_VERSIONHORARIO',
  UPDATE_VERSIONHORARIO: 'versionHorario/UPDATE_VERSIONHORARIO',
  DELETE_VERSIONHORARIO: 'versionHorario/DELETE_VERSIONHORARIO',
  RESET: 'versionHorario/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IVersionHorario>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type VersionHorarioState = Readonly<typeof initialState>;

// Reducer

export default (state: VersionHorarioState = initialState, action): VersionHorarioState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_VERSIONHORARIO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_VERSIONHORARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_VERSIONHORARIO):
    case REQUEST(ACTION_TYPES.UPDATE_VERSIONHORARIO):
    case REQUEST(ACTION_TYPES.DELETE_VERSIONHORARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_VERSIONHORARIO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_VERSIONHORARIO):
    case FAILURE(ACTION_TYPES.CREATE_VERSIONHORARIO):
    case FAILURE(ACTION_TYPES.UPDATE_VERSIONHORARIO):
    case FAILURE(ACTION_TYPES.DELETE_VERSIONHORARIO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_VERSIONHORARIO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_VERSIONHORARIO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_VERSIONHORARIO):
    case SUCCESS(ACTION_TYPES.UPDATE_VERSIONHORARIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_VERSIONHORARIO):
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

const apiUrl = 'api/version-horarios';

// Actions

export const getEntities: ICrudGetAllAction<IVersionHorario> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_VERSIONHORARIO_LIST,
  payload: axios.get<IVersionHorario>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IVersionHorario> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_VERSIONHORARIO,
    payload: axios.get<IVersionHorario>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IVersionHorario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_VERSIONHORARIO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IVersionHorario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_VERSIONHORARIO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IVersionHorario> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_VERSIONHORARIO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
