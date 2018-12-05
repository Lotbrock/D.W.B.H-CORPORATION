import { IInstructor } from 'app/shared/model//instructor.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IVinculacion {
  id?: number;
  tipoVinculacion?: string;
  horas?: number;
  estado?: Estado;
  instructors?: IInstructor[];
}

export const defaultValue: Readonly<IVinculacion> = {};
