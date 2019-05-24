import { IActividad } from 'app/shared/model//actividad.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IFase {
  id?: number;
  nombre?: string;
  estado?: Estado;
  actividads?: IActividad[];
  proyectoCodigo?: string;
  proyectoId?: number;
}

export const defaultValue: Readonly<IFase> = {};
