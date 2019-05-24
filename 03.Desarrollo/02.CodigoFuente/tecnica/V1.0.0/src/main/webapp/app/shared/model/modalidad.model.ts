import { IHorario } from 'app/shared/model//horario.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IModalidad {
  id?: number;
  nombreModalidad?: string;
  color?: string;
  estado?: Estado;
  horarios?: IHorario[];
}

export const defaultValue: Readonly<IModalidad> = {};
