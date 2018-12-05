import { IAmbiente } from 'app/shared/model//ambiente.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface ISede {
  id?: number;
  nombreSede?: string;
  direccion?: string;
  estado?: Estado;
  ambientes?: IAmbiente[];
}

export const defaultValue: Readonly<ISede> = {};
