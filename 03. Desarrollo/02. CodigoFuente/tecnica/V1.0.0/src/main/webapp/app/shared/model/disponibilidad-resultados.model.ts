import { Moment } from 'moment';

export interface IDisponibilidadResultados {
  id?: number;
  anio?: Moment;
  resultadoAprendizajeId?: number;
  intructorId?: number;
}

export const defaultValue: Readonly<IDisponibilidadResultados> = {};
