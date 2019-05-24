import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDisponibilidadResultados, defaultValue } from 'app/shared/model/disponibilidad-resultados.model';

export const ACTION_TYPES = {
  FETCH_DISPONIBILIDADRESULTADOS_LIST: 'disponibilidadResultados/FETCH_DISPONIBILIDADRESULTADOS_LIST',
  FETCH_DISPONIBILIDADRESULTADOS: 'disponibilidadResultados/FETCH_DISPONIBILIDADRESULTADOS',
  CREATE_DISPONIBILIDADRESULTADOS: 'disponibilidadResultados/CREATE_DISPONIBILIDADRESULTADOS',
  UPDATE_DISPONIBILIDADRESULTADOS: 'disponibilidadResultados/UPDATE_DISPONIBILIDADRESULTADOS',
  DELETE_DISPONIBILIDADRESULTADOS: 'disponibilidadResultados/DELETE_DISPONIBILIDADRESULTADOS',
  RESET: 'disponibilidadResultados/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDisponibilidadResultados>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DisponibilidadResultadosState = Readonly<typeof initialState>;

// Reducer

export default (state: DisponibilidadResultadosState = initialState, action): DisponibilidadResultadosState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DISPONIBILIDADRESULTADOS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DISPONIBILIDADRESULTADOS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DISPONIBILIDADRESULTADOS):
    case REQUEST(ACTION_TYPES.UPDATE_DISPONIBILIDADRESULTADOS):
    case REQUEST(ACTION_TYPES.DELETE_DISPONIBILIDADRESULTADOS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DISPONIBILIDADRESULTADOS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DISPONIBILIDADRESULTADOS):
    case FAILURE(ACTION_TYPES.CREATE_DISPONIBILIDADRESULTADOS):
    case FAILURE(ACTION_TYPES.UPDATE_DISPONIBILIDADRESULTADOS):
    case FAILURE(ACTION_TYPES.DELETE_DISPONIBILIDADRESULTADOS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DISPONIBILIDADRESULTADOS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DISPONIBILIDADRESULTADOS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DISPONIBILIDADRESULTADOS):
    case SUCCESS(ACTION_TYPES.UPDATE_DISPONIBILIDADRESULTADOS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DISPONIBILIDADRESULTADOS):
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

const apiUrl = 'api/disponibilidad-resultados';

// Actions

export const getEntities: ICrudGetAllAction<IDisponibilidadResultados> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DISPONIBILIDADRESULTADOS_LIST,
  payload: axios.get<IDisponibilidadResultados>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDisponibilidadResultados> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DISPONIBILIDADRESULTADOS,
    payload: axios.get<IDisponibilidadResultados>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDisponibilidadResultados> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DISPONIBILIDADRESULTADOS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDisponibilidadResultados> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DISPONIBILIDADRESULTADOS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDisponibilidadResultados> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DISPONIBILIDADRESULTADOS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
