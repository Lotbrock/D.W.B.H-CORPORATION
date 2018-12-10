import { Moment } from 'moment';
import { IVersionHorario } from 'app/shared/model//version-horario.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface ITrimestreVigente {
  id?: number;
  anio?: Moment;
  trimestreProgramado?: number;
  fechaInicio?: Moment;
  fechaFin?: Moment;
  estado?: Estado;
  versionHorarios?: IVersionHorario[];
}

export const defaultValue: Readonly<ITrimestreVigente> = {};
