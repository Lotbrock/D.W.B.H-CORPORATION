import { IResultadoAprendizaje } from 'app/shared/model//resultado-aprendizaje.model';

export interface ICompetencia {
  id?: number;
  codigoCompetencia?: string;
  descripcion?: string;
  resultadoAprendizajes?: IResultadoAprendizaje[];
  programaCodigo?: string;
  programaId?: number;
}

export const defaultValue: Readonly<ICompetencia> = {};
