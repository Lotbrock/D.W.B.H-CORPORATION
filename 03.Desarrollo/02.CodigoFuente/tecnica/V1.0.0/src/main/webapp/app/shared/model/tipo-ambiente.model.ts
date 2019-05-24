import { IAmbiente } from 'app/shared/model//ambiente.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface ITipoAmbiente {
  id?: number;
  tipo?: string;
  descripcion?: string;
  estado?: Estado;
  ambientes?: IAmbiente[];
}

export const defaultValue: Readonly<ITipoAmbiente> = {};
