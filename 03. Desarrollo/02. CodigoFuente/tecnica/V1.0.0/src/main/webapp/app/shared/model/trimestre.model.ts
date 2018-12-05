import { IFichaHasTrimestre } from 'app/shared/model//ficha-has-trimestre.model';
import { IPlaneacion } from 'app/shared/model//planeacion.model';

export interface ITrimestre {
  id?: number;
  nombreTrimestre?: string;
  fichaHasTrimestres?: IFichaHasTrimestre[];
  jornadaNombreJornada?: string;
  jornadaId?: number;
  nivelformacionNivel?: string;
  nivelformacionId?: number;
  planeacions?: IPlaneacion[];
}

export const defaultValue: Readonly<ITrimestre> = {};
