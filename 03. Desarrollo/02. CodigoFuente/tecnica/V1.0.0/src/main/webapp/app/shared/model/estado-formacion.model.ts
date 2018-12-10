import { IAprendiz } from 'app/shared/model//aprendiz.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IEstadoFormacion {
  id?: number;
  nombreEstado?: string;
  estado?: Estado;
  aprendizs?: IAprendiz[];
}

export const defaultValue: Readonly<IEstadoFormacion> = {};
