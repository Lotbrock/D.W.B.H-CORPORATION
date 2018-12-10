export interface IAprendiz {
  id?: number;
  documentoNumeroDocumento?: string;
  documentoId?: number;
  fichaNumeroFicha?: string;
  fichaId?: number;
  estadoFormacionNombreEstado?: string;
  estadoFormacionId?: number;
}

export const defaultValue: Readonly<IAprendiz> = {};
