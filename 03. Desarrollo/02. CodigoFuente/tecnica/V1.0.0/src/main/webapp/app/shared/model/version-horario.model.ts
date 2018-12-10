import { IHorario } from 'app/shared/model//horario.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IVersionHorario {
  id?: number;
  numeroVersion?: string;
  estado?: Estado;
  horarios?: IHorario[];
  trimestreVigenteTrimestreProgramado?: string;
  trimestreVigenteId?: number;
}

export const defaultValue: Readonly<IVersionHorario> = {};
