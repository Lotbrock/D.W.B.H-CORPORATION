import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHorario, defaultValue } from 'app/shared/model/horario.model';

export const ACTION_TYPES = {
  FETCH_HORARIO_LIST: 'horario/FETCH_HORARIO_LIST',
  FETCH_HORARIO: 'horario/FETCH_HORARIO',
  CREATE_HORARIO: 'horario/CREATE_HORARIO',
  UPDATE_HORARIO: 'horario/UPDATE_HORARIO',
  DELETE_HORARIO: 'horario/DELETE_HORARIO',
  RESET: 'horario/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHorario>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type HorarioState = Readonly<typeof initialState>;

// Reducer

export default (state: HorarioState = initialState, action): HorarioState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_HORARIO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HORARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HORARIO):
    case REQUEST(ACTION_TYPES.UPDATE_HORARIO):
    case REQUEST(ACTION_TYPES.DELETE_HORARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_HORARIO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HORARIO):
    case FAILURE(ACTION_TYPES.CREATE_HORARIO):
    case FAILURE(ACTION_TYPES.UPDATE_HORARIO):
    case FAILURE(ACTION_TYPES.DELETE_HORARIO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_HORARIO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HORARIO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HORARIO):
    case SUCCESS(ACTION_TYPES.UPDATE_HORARIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HORARIO):
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

const apiUrl = 'api/horarios';

// Actions

export const getEntities: ICrudGetAllAction<IHorario> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_HORARIO_LIST,
  payload: axios.get<IHorario>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IHorario> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HORARIO,
    payload: axios.get<IHorario>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHorario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HORARIO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHorario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HORARIO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHorario> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HORARIO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
