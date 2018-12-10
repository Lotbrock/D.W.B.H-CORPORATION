import { IHorario } from 'app/shared/model//horario.model';
import { IDisponibilidadResultados } from 'app/shared/model//disponibilidad-resultados.model';
import { IDisponibilidadHoraria } from 'app/shared/model//disponibilidad-horaria.model';
import { IEspecialidad } from 'app/shared/model//especialidad.model';
import { IVinculacion } from 'app/shared/model//vinculacion.model';

export const enum Estado {
  ACTIVO = 'ACTIVO',
  INACTIVO = 'INACTIVO'
}

export interface IInstructor {
  id?: number;
  estado?: Estado;
  horarios?: IHorario[];
  disponibilidadResultados?: IDisponibilidadResultados[];
  disponibilidadHorarias?: IDisponibilidadHoraria[];
  documentoNumeroDocumento?: string;
  documentoId?: number;
  especialidads?: IEspecialidad[];
  vinculacions?: IVinculacion[];
}

export const defaultValue: Readonly<IInstructor> = {};
