import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInstructor, defaultValue } from 'app/shared/model/instructor.model';

export const ACTION_TYPES = {
  FETCH_INSTRUCTOR_LIST: 'instructor/FETCH_INSTRUCTOR_LIST',
  FETCH_INSTRUCTOR: 'instructor/FETCH_INSTRUCTOR',
  CREATE_INSTRUCTOR: 'instructor/CREATE_INSTRUCTOR',
  UPDATE_INSTRUCTOR: 'instructor/UPDATE_INSTRUCTOR',
  DELETE_INSTRUCTOR: 'instructor/DELETE_INSTRUCTOR',
  RESET: 'instructor/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInstructor>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InstructorState = Readonly<typeof initialState>;

// Reducer

export default (state: InstructorState = initialState, action): InstructorState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INSTRUCTOR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INSTRUCTOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INSTRUCTOR):
    case REQUEST(ACTION_TYPES.UPDATE_INSTRUCTOR):
    case REQUEST(ACTION_TYPES.DELETE_INSTRUCTOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INSTRUCTOR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INSTRUCTOR):
    case FAILURE(ACTION_TYPES.CREATE_INSTRUCTOR):
    case FAILURE(ACTION_TYPES.UPDATE_INSTRUCTOR):
    case FAILURE(ACTION_TYPES.DELETE_INSTRUCTOR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INSTRUCTOR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INSTRUCTOR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INSTRUCTOR):
    case SUCCESS(ACTION_TYPES.UPDATE_INSTRUCTOR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INSTRUCTOR):
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

const apiUrl = 'api/instructors';

// Actions

export const getEntities: ICrudGetAllAction<IInstructor> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INSTRUCTOR_LIST,
  payload: axios.get<IInstructor>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IInstructor> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INSTRUCTOR,
    payload: axios.get<IInstructor>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInstructor> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INSTRUCTOR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInstructor> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INSTRUCTOR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInstructor> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INSTRUCTOR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
