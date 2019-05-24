import { IHorario } from 'app/shared/model//horario.model';
import { IResultadosVistos } from 'app/shared/model//resultados-vistos.model';

export interface IFichaHasTrimestre {
  id?: number;
  horarios?: IHorario[];
  resultadosVistos?: IResultadosVistos[];
  trimestreNombreTrimestre?: string;
  trimestreId?: number;
  fichaNumeroFicha?: string;
  fichaId?: number;
}

export const defaultValue: Readonly<IFichaHasTrimestre> = {};
