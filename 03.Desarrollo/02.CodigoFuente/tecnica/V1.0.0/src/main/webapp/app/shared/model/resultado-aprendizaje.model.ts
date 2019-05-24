import { IResultadosVistos } from 'app/shared/model//resultados-vistos.model';
import { ILimitacionAmbiente } from 'app/shared/model//limitacion-ambiente.model';
import { IDisponibilidadResultados } from 'app/shared/model//disponibilidad-resultados.model';

export interface IResultadoAprendizaje {
  id?: number;
  codigoResultado?: string;
  descripcion?: string;
  resultadosVistos?: IResultadosVistos[];
  limitacionAmbientes?: ILimitacionAmbiente[];
  disponibilidadResultados?: IDisponibilidadResultados[];
  competenciaCodigoCompetencia?: string;
  competenciaId?: number;
}

export const defaultValue: Readonly<IResultadoAprendizaje> = {};
