import { ICompetencia } from 'app/shared/model//competencia.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IPrograma {
  id?: number;
  codigo?: string;
  version?: string;
  nombre?: string;
  sigla?: string;
  estado?: Estado;
  competencias?: ICompetencia[];
  nivelFormacionNivel?: string;
  nivelFormacionId?: number;
}

export const defaultValue: Readonly<IPrograma> = {};
