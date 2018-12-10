import { IFicha } from 'app/shared/model//ficha.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IEstadoFicha {
  id?: number;
  nombreEstado?: string;
  estado?: Estado;
  fichas?: IFicha[];
}

export const defaultValue: Readonly<IEstadoFicha> = {};
