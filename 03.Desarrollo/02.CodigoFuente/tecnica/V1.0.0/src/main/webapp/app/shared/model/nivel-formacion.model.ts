import { IPrograma } from 'app/shared/model//programa.model';
import { ITrimestre } from 'app/shared/model//trimestre.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface INivelFormacion {
  id?: number;
  nivel?: string;
  estado?: Estado;
  programas?: IPrograma[];
  trimestres?: ITrimestre[];
}

export const defaultValue: Readonly<INivelFormacion> = {};
