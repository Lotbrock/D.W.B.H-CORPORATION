import { IHorario } from 'app/shared/model//horario.model';
import { IDisponibilidadHoraria } from 'app/shared/model//disponibilidad-horaria.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IDia {
  id?: number;
  nombreDia?: string;
  estado?: Estado;
  horarios?: IHorario[];
  disponibilidadHorarias?: IDisponibilidadHoraria[];
}

export const defaultValue: Readonly<IDia> = {};
