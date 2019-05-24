import { IDisponibilidadHoraria } from 'app/shared/model//disponibilidad-horaria.model';
import { ITrimestre } from 'app/shared/model//trimestre.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IJornada {
  id?: number;
  siglaJornada?: string;
  nombreJornada?: string;
  descripcion?: string;
  imagenUrl?: string;
  estado?: Estado;
  disponibilidadHorarias?: IDisponibilidadHoraria[];
  trimestres?: ITrimestre[];
}

export const defaultValue: Readonly<IJornada> = {};
