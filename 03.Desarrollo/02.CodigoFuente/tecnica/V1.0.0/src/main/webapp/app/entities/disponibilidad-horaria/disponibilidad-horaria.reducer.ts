import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDisponibilidadHoraria, defaultValue } from 'app/shared/model/disponibilidad-horaria.model';

export const ACTION_TYPES = {
  FETCH_DISPONIBILIDADHORARIA_LIST: 'disponibilidadHoraria/FETCH_DISPONIBILIDADHORARIA_LIST',
  FETCH_DISPONIBILIDADHORARIA: 'disponibilidadHoraria/FETCH_DISPONIBILIDADHORARIA',
  CREATE_DISPONIBILIDADHORARIA: 'disponibilidadHoraria/CREATE_DISPONIBILIDADHORARIA',
  UPDATE_DISPONIBILIDADHORARIA: 'disponibilidadHoraria/UPDATE_DISPONIBILIDADHORARIA',
  DELETE_DISPONIBILIDADHORARIA: 'disponibilidadHoraria/DELETE_DISPONIBILIDADHORARIA',
  RESET: 'disponibilidadHoraria/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDisponibilidadHoraria>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DisponibilidadHorariaState = Readonly<typeof initialState>;

// Reducer

export default (state: DisponibilidadHorariaState = initialState, action): DisponibilidadHorariaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DISPONIBILIDADHORARIA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DISPONIBILIDADHORARIA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DISPONIBILIDADHORARIA):
    case REQUEST(ACTION_TYPES.UPDATE_DISPONIBILIDADHORARIA):
    case REQUEST(ACTION_TYPES.DELETE_DISPONIBILIDADHORARIA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DISPONIBILIDADHORARIA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DISPONIBILIDADHORARIA):
    case FAILURE(ACTION_TYPES.CREATE_DISPONIBILIDADHORARIA):
    case FAILURE(ACTION_TYPES.UPDATE_DISPONIBILIDADHORARIA):
    case FAILURE(ACTION_TYPES.DELETE_DISPONIBILIDADHORARIA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DISPONIBILIDADHORARIA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DISPONIBILIDADHORARIA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DISPONIBILIDADHORARIA):
    case SUCCESS(ACTION_TYPES.UPDATE_DISPONIBILIDADHORARIA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DISPONIBILIDADHORARIA):
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

const apiUrl = 'api/disponibilidad-horarias';

// Actions

export const getEntities: ICrudGetAllAction<IDisponibilidadHoraria> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DISPONIBILIDADHORARIA_LIST,
  payload: axios.get<IDisponibilidadHoraria>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDisponibilidadHoraria> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DISPONIBILIDADHORARIA,
    payload: axios.get<IDisponibilidadHoraria>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDisponibilidadHoraria> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DISPONIBILIDADHORARIA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDisponibilidadHoraria> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DISPONIBILIDADHORARIA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDisponibilidadHoraria> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DISPONIBILIDADHORARIA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
