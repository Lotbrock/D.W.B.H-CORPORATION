import { Moment } from 'moment';
import { IFichaHasTrimestre } from 'app/shared/model//ficha-has-trimestre.model';
import { IAprendiz } from 'app/shared/model//aprendiz.model';

export interface IFicha {
  id?: number;
  numeroFicha?: string;
  fechaInicio?: Moment;
  fechaFin?: Moment;
  ruta?: string;
  fichaHasTrimestres?: IFichaHasTrimestre[];
  aprendizs?: IAprendiz[];
  estadoFichaNombreEstado?: string;
  estadoFichaId?: number;
}

export const defaultValue: Readonly<IFicha> = {};
