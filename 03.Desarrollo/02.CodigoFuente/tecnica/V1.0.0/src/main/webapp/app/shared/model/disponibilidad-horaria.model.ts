import { Moment } from 'moment';

export interface IDisponibilidadHoraria {
  id?: number;
  anio?: Moment;
  horaInicio?: Moment;
  horaFin?: Moment;
  instructorId?: number;
  jornadaNombreJornada?: string;
  jornadaId?: number;
  diaNombreDia?: string;
  diaId?: number;
}

export const defaultValue: Readonly<IDisponibilidadHoraria> = {};
