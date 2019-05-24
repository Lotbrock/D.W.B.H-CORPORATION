import { IPlaneacion } from 'app/shared/model//planeacion.model';

export interface IActividad {
  id?: number;
  numeroActividad?: number;
  nombreActividad?: string;
  planeacions?: IPlaneacion[];
  faseNombre?: string;
  faseId?: number;
}

export const defaultValue: Readonly<IActividad> = {};
