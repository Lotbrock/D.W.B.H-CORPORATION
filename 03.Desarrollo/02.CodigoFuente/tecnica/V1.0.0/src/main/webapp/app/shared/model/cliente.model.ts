import { IInstructor } from 'app/shared/model//instructor.model';
import { IAprendiz } from 'app/shared/model//aprendiz.model';

export interface ICliente {
  id?: number;
  numeroDocumento?: string;
  primerNombre?: string;
  segundoNombre?: string;
  primerApellido?: string;
  segundoApellido?: string;
  instructors?: IInstructor[];
  aprendizs?: IAprendiz[];
  tipoDocumentoSigla?: string;
  tipoDocumentoId?: number;
  userLogin?: string;
  userId?: number;
}

export const defaultValue: Readonly<ICliente> = {};
