import { ITrimestre } from 'app/shared/model//trimestre.model';
import { IActividad } from 'app/shared/model//actividad.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IPlaneacion {
  id?: number;
  codigoPlaneacfion?: string;
  estado?: Estado;
  trimestres?: ITrimestre[];
  actividads?: IActividad[];
}

export const defaultValue: Readonly<IPlaneacion> = {};
