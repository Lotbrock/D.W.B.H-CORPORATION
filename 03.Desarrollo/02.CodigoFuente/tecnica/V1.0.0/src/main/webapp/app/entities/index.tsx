import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Cliente from './cliente';
import Proyecto from './proyecto';
import Fase from './fase';
import Actividad from './actividad';
import Planeacion from './planeacion';
import Trimestre from './trimestre';
import NivelFormacion from './nivel-formacion';
import Jornada from './jornada';
import Programa from './programa';
import Competencia from './competencia';
import Ficha from './ficha';
import EstadoFicha from './estado-ficha';
import Aprendiz from './aprendiz';
import EstadoFormacion from './estado-formacion';
import DisponibilidadHoraria from './disponibilidad-horaria';
import Dia from './dia';
import Instructor from './instructor';
import Especialidad from './especialidad';
import Vinculacion from './vinculacion';
import TipoDocumento from './tipo-documento';
import DisponibilidadResultados from './disponibilidad-resultados';
import ResultadoAprendizaje from './resultado-aprendizaje';
import LimitacionAmbiente from './limitacion-ambiente';
import Ambiente from './ambiente';
import Sede from './sede';
import TipoAmbiente from './tipo-ambiente';
import TrimestreVigente from './trimestre-vigente';
import VersionHorario from './version-horario';
import Modalidad from './modalidad';
import ResultadosVistos from './resultados-vistos';
import FichaHasTrimestre from './ficha-has-trimestre';
import Horario from './horario';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/cliente`} component={Cliente} />
      <ErrorBoundaryRoute path={`${match.url}/proyecto`} component={Proyecto} />
      <ErrorBoundaryRoute path={`${match.url}/fase`} component={Fase} />
      <ErrorBoundaryRoute path={`${match.url}/actividad`} component={Actividad} />
      <ErrorBoundaryRoute path={`${match.url}/planeacion`} component={Planeacion} />
      <ErrorBoundaryRoute path={`${match.url}/trimestre`} component={Trimestre} />
      <ErrorBoundaryRoute path={`${match.url}/nivel-formacion`} component={NivelFormacion} />
      <ErrorBoundaryRoute path={`${match.url}/jornada`} component={Jornada} />
      <ErrorBoundaryRoute path={`${match.url}/programa`} component={Programa} />
      <ErrorBoundaryRoute path={`${match.url}/competencia`} component={Competencia} />
      <ErrorBoundaryRoute path={`${match.url}/ficha`} component={Ficha} />
      <ErrorBoundaryRoute path={`${match.url}/estado-ficha`} component={EstadoFicha} />
      <ErrorBoundaryRoute path={`${match.url}/aprendiz`} component={Aprendiz} />
      <ErrorBoundaryRoute path={`${match.url}/estado-formacion`} component={EstadoFormacion} />
      <ErrorBoundaryRoute path={`${match.url}/disponibilidad-horaria`} component={DisponibilidadHoraria} />
      <ErrorBoundaryRoute path={`${match.url}/dia`} component={Dia} />
      <ErrorBoundaryRoute path={`${match.url}/instructor`} component={Instructor} />
      <ErrorBoundaryRoute path={`${match.url}/especialidad`} component={Especialidad} />
      <ErrorBoundaryRoute path={`${match.url}/vinculacion`} component={Vinculacion} />
      <ErrorBoundaryRoute path={`${match.url}/tipo-documento`} component={TipoDocumento} />
      <ErrorBoundaryRoute path={`${match.url}/disponibilidad-resultados`} component={DisponibilidadResultados} />
      <ErrorBoundaryRoute path={`${match.url}/resultado-aprendizaje`} component={ResultadoAprendizaje} />
      <ErrorBoundaryRoute path={`${match.url}/limitacion-ambiente`} component={LimitacionAmbiente} />
      <ErrorBoundaryRoute path={`${match.url}/ambiente`} component={Ambiente} />
      <ErrorBoundaryRoute path={`${match.url}/sede`} component={Sede} />
      <ErrorBoundaryRoute path={`${match.url}/tipo-ambiente`} component={TipoAmbiente} />
      <ErrorBoundaryRoute path={`${match.url}/trimestre-vigente`} component={TrimestreVigente} />
      <ErrorBoundaryRoute path={`${match.url}/version-horario`} component={VersionHorario} />
      <ErrorBoundaryRoute path={`${match.url}/modalidad`} component={Modalidad} />
      <ErrorBoundaryRoute path={`${match.url}/resultados-vistos`} component={ResultadosVistos} />
      <ErrorBoundaryRoute path={`${match.url}/ficha-has-trimestre`} component={FichaHasTrimestre} />
      <ErrorBoundaryRoute path={`${match.url}/horario`} component={Horario} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
