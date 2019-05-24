import { Moment } from 'moment';

export interface IHorario {
  id?: number;
  horaInicio?: Moment;
  horaFin?: Moment;
  modalidadNombreModalidad?: string;
  modalidadId?: number;
  versionHorarioNumeroVersion?: string;
  versionHorarioId?: number;
  idAmbienteNumeroAmbiente?: string;
  idAmbienteId?: number;
  diaNombreDia?: string;
  diaId?: number;
  intructorId?: number;
  idFichaHasTrimestreId?: number;
}

export const defaultValue: Readonly<IHorario> = {};
