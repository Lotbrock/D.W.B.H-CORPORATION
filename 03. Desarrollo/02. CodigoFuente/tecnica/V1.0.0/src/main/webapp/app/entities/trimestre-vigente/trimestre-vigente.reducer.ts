import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITrimestreVigente, defaultValue } from 'app/shared/model/trimestre-vigente.model';

export const ACTION_TYPES = {
  FETCH_TRIMESTREVIGENTE_LIST: 'trimestreVigente/FETCH_TRIMESTREVIGENTE_LIST',
  FETCH_TRIMESTREVIGENTE: 'trimestreVigente/FETCH_TRIMESTREVIGENTE',
  CREATE_TRIMESTREVIGENTE: 'trimestreVigente/CREATE_TRIMESTREVIGENTE',
  UPDATE_TRIMESTREVIGENTE: 'trimestreVigente/UPDATE_TRIMESTREVIGENTE',
  DELETE_TRIMESTREVIGENTE: 'trimestreVigente/DELETE_TRIMESTREVIGENTE',
  RESET: 'trimestreVigente/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITrimestreVigente>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TrimestreVigenteState = Readonly<typeof initialState>;

// Reducer

export default (state: TrimestreVigenteState = initialState, action): TrimestreVigenteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TRIMESTREVIGENTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TRIMESTREVIGENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TRIMESTREVIGENTE):
    case REQUEST(ACTION_TYPES.UPDATE_TRIMESTREVIGENTE):
    case REQUEST(ACTION_TYPES.DELETE_TRIMESTREVIGENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TRIMESTREVIGENTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TRIMESTREVIGENTE):
    case FAILURE(ACTION_TYPES.CREATE_TRIMESTREVIGENTE):
    case FAILURE(ACTION_TYPES.UPDATE_TRIMESTREVIGENTE):
    case FAILURE(ACTION_TYPES.DELETE_TRIMESTREVIGENTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRIMESTREVIGENTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRIMESTREVIGENTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TRIMESTREVIGENTE):
    case SUCCESS(ACTION_TYPES.UPDATE_TRIMESTREVIGENTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TRIMESTREVIGENTE):
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

const apiUrl = 'api/trimestre-vigentes';

// Actions

export const getEntities: ICrudGetAllAction<ITrimestreVigente> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TRIMESTREVIGENTE_LIST,
  payload: axios.get<ITrimestreVigente>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITrimestreVigente> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TRIMESTREVIGENTE,
    payload: axios.get<ITrimestreVigente>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITrimestreVigente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TRIMESTREVIGENTE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITrimestreVigente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TRIMESTREVIGENTE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITrimestreVigente> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TRIMESTREVIGENTE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
