import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import cliente, {
  ClienteState
} from 'app/entities/cliente/cliente.reducer';
// prettier-ignore
import proyecto, {
  ProyectoState
} from 'app/entities/proyecto/proyecto.reducer';
// prettier-ignore
import fase, {
  FaseState
} from 'app/entities/fase/fase.reducer';
// prettier-ignore
import actividad, {
  ActividadState
} from 'app/entities/actividad/actividad.reducer';
// prettier-ignore
import planeacion, {
  PlaneacionState
} from 'app/entities/planeacion/planeacion.reducer';
// prettier-ignore
import trimestre, {
  TrimestreState
} from 'app/entities/trimestre/trimestre.reducer';
// prettier-ignore
import nivelFormacion, {
  NivelFormacionState
} from 'app/entities/nivel-formacion/nivel-formacion.reducer';
// prettier-ignore
import jornada, {
  JornadaState
} from 'app/entities/jornada/jornada.reducer';
// prettier-ignore
import programa, {
  ProgramaState
} from 'app/entities/programa/programa.reducer';
// prettier-ignore
import competencia, {
  CompetenciaState
} from 'app/entities/competencia/competencia.reducer';
// prettier-ignore
import ficha, {
  FichaState
} from 'app/entities/ficha/ficha.reducer';
// prettier-ignore
import estadoFicha, {
  EstadoFichaState
} from 'app/entities/estado-ficha/estado-ficha.reducer';
// prettier-ignore
import aprendiz, {
  AprendizState
} from 'app/entities/aprendiz/aprendiz.reducer';
// prettier-ignore
import estadoFormacion, {
  EstadoFormacionState
} from 'app/entities/estado-formacion/estado-formacion.reducer';
// prettier-ignore
import disponibilidadHoraria, {
  DisponibilidadHorariaState
} from 'app/entities/disponibilidad-horaria/disponibilidad-horaria.reducer';
// prettier-ignore
import dia, {
  DiaState
} from 'app/entities/dia/dia.reducer';
// prettier-ignore
import instructor, {
  InstructorState
} from 'app/entities/instructor/instructor.reducer';
// prettier-ignore
import especialidad, {
  EspecialidadState
} from 'app/entities/especialidad/especialidad.reducer';
// prettier-ignore
import vinculacion, {
  VinculacionState
} from 'app/entities/vinculacion/vinculacion.reducer';
// prettier-ignore
import tipoDocumento, {
  TipoDocumentoState
} from 'app/entities/tipo-documento/tipo-documento.reducer';
// prettier-ignore
import disponibilidadResultados, {
  DisponibilidadResultadosState
} from 'app/entities/disponibilidad-resultados/disponibilidad-resultados.reducer';
// prettier-ignore
import resultadoAprendizaje, {
  ResultadoAprendizajeState
} from 'app/entities/resultado-aprendizaje/resultado-aprendizaje.reducer';
// prettier-ignore
import limitacionAmbiente, {
  LimitacionAmbienteState
} from 'app/entities/limitacion-ambiente/limitacion-ambiente.reducer';
// prettier-ignore
import ambiente, {
  AmbienteState
} from 'app/entities/ambiente/ambiente.reducer';
// prettier-ignore
import sede, {
  SedeState
} from 'app/entities/sede/sede.reducer';
// prettier-ignore
import tipoAmbiente, {
  TipoAmbienteState
} from 'app/entities/tipo-ambiente/tipo-ambiente.reducer';
// prettier-ignore
import trimestreVigente, {
  TrimestreVigenteState
} from 'app/entities/trimestre-vigente/trimestre-vigente.reducer';
// prettier-ignore
import versionHorario, {
  VersionHorarioState
} from 'app/entities/version-horario/version-horario.reducer';
// prettier-ignore
import modalidad, {
  ModalidadState
} from 'app/entities/modalidad/modalidad.reducer';
// prettier-ignore
import resultadosVistos, {
  ResultadosVistosState
} from 'app/entities/resultados-vistos/resultados-vistos.reducer';
// prettier-ignore
import fichaHasTrimestre, {
  FichaHasTrimestreState
} from 'app/entities/ficha-has-trimestre/ficha-has-trimestre.reducer';
// prettier-ignore
import horario, {
  HorarioState
} from 'app/entities/horario/horario.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly cliente: ClienteState;
  readonly proyecto: ProyectoState;
  readonly fase: FaseState;
  readonly actividad: ActividadState;
  readonly planeacion: PlaneacionState;
  readonly trimestre: TrimestreState;
  readonly nivelFormacion: NivelFormacionState;
  readonly jornada: JornadaState;
  readonly programa: ProgramaState;
  readonly competencia: CompetenciaState;
  readonly ficha: FichaState;
  readonly estadoFicha: EstadoFichaState;
  readonly aprendiz: AprendizState;
  readonly estadoFormacion: EstadoFormacionState;
  readonly disponibilidadHoraria: DisponibilidadHorariaState;
  readonly dia: DiaState;
  readonly instructor: InstructorState;
  readonly especialidad: EspecialidadState;
  readonly vinculacion: VinculacionState;
  readonly tipoDocumento: TipoDocumentoState;
  readonly disponibilidadResultados: DisponibilidadResultadosState;
  readonly resultadoAprendizaje: ResultadoAprendizajeState;
  readonly limitacionAmbiente: LimitacionAmbienteState;
  readonly ambiente: AmbienteState;
  readonly sede: SedeState;
  readonly tipoAmbiente: TipoAmbienteState;
  readonly trimestreVigente: TrimestreVigenteState;
  readonly versionHorario: VersionHorarioState;
  readonly modalidad: ModalidadState;
  readonly resultadosVistos: ResultadosVistosState;
  readonly fichaHasTrimestre: FichaHasTrimestreState;
  readonly horario: HorarioState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  cliente,
  proyecto,
  fase,
  actividad,
  planeacion,
  trimestre,
  nivelFormacion,
  jornada,
  programa,
  competencia,
  ficha,
  estadoFicha,
  aprendiz,
  estadoFormacion,
  disponibilidadHoraria,
  dia,
  instructor,
  especialidad,
  vinculacion,
  tipoDocumento,
  disponibilidadResultados,
  resultadoAprendizaje,
  limitacionAmbiente,
  ambiente,
  sede,
  tipoAmbiente,
  trimestreVigente,
  versionHorario,
  modalidad,
  resultadosVistos,
  fichaHasTrimestre,
  horario,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
