import { IHorario } from 'app/shared/model//horario.model';
import { ILimitacionAmbiente } from 'app/shared/model//limitacion-ambiente.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IAmbiente {
  id?: number;
  numeroAmbiente?: string;
  descripcion?: string;
  estado?: Estado;
  horarios?: IHorario[];
  limitacionAmbientes?: ILimitacionAmbiente[];
  tipoAmbienteTipo?: string;
  tipoAmbienteId?: number;
  sedeNombreSede?: string;
  sedeId?: number;
}

export const defaultValue: Readonly<IAmbiente> = {};
