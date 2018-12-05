import { IInstructor } from 'app/shared/model//instructor.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IEspecialidad {
  id?: number;
  nombreEspecialidad?: string;
  estado?: Estado;
  logoUrl?: string;
  instructors?: IInstructor[];
}

export const defaultValue: Readonly<IEspecialidad> = {};
