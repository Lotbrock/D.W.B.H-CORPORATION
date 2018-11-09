CREATE TABLE rol_usuario (
  rol         varchar(40) NOT NULL comment 'Rol que desempeña el usuario, ejemplo: Instructor', 
  descripcion varchar(200) NOT NULL comment 'Descripcion del tipo de rol', 
  estado      varchar(50) NOT NULL comment 'Estado del rol, ejemplo; activo, inactivo', 
  PRIMARY KEY (rol));
CREATE TABLE usuario (
  tipo_documento    varchar(10) NOT NULL comment 'LLave primaria, tipo de documento', 
  numero_documento  varchar(50) NOT NULL comment 'Numero de documento', 
  primer_nombre     varchar(50) NOT NULL comment 'Primer nombre del usuario', 
  segundo_nombre    varchar(50) comment 'Segundo nombre del usuario', 
  primer_apellido   varchar(50) NOT NULL comment 'Primer apellido del usuario', 
  segundo_apellido  varchar(50) comment 'Segundo apellido del usuario(si tiene)', 
  correo            varchar(170) NOT NULL UNIQUE comment 'Correo electronico del usuario', 
  fecha_terminacion date NOT NULL comment 'Fecha de terminacion de su contrato o de su programa', 
  password          varchar(255) NOT NULL comment 'Contraseña del usuario', 
  url_foto          varchar(400) comment 'Url de la foto del usuario', 
  PRIMARY KEY (tipo_documento, 
  numero_documento));
CREATE TABLE tipo_de_documento (
  tipo_documento   varchar(10) NOT NULL comment 'Tipo de documento del usuario', 
  nombre_documento varchar(100) NOT NULL comment 'Nombre del tipo de documento, ejemplo: Cedula de ciudadania', 
  estado           varchar(50) NOT NULL comment 'Estado, ejemplo: activo o inactivo', 
  PRIMARY KEY (tipo_documento));
CREATE TABLE estado_ficha (
  nombre_estado varchar(40) NOT NULL comment 'Nombre del estado de la ficha, ejemplo: En ejecucion, suspendida, fusionada', 
  estado        varchar(50) NOT NULL comment 'Estado del estado de la ficha para permitir cambiar el estado de una ficha ya creada, ejemplo: Deshabilitada(para no permitir creacion de fichas con ese estado)', 
  PRIMARY KEY (nombre_estado));
CREATE TABLE ficha (
  numero_ficha     varchar(100) NOT NULL comment 'Numero unico de identificacion de cada ficha', 
  fecha_inicio     date comment 'Fecha de inicio de formacion', 
  fecha_fin        date comment 'Fecha de finalizacion de formacion', 
  ruta             varchar(40) comment 'Codigo ruta de aprendizaje', 
  programa_codigo  varchar(50) NOT NULL comment 'Codigo unico de identificacion del programa de formacion', 
  programa_version varchar(40) NOT NULL comment 'Version del programa de formacion', 
  estado_ficha     varchar(40) NOT NULL comment 'Estado de la ficha, ejemplo: en formacion, suspendida, Fusionada', 
  PRIMARY KEY (numero_ficha));
CREATE TABLE jornada (
  sigla_jornada  varchar(20) NOT NULL comment 'Sigla de la jornada de formacion', 
  nombre_jornada varchar(40) NOT NULL comment 'Nombre de la jornada, ejemplo: Diurna, Nocturna', 
  descripcion    varchar(100) NOT NULL comment 'Descripcion de la jornada', 
  imagen         varchar(255) comment 'Url de la imagen donde se represente la jornada', 
  estado         varchar(50) NOT NULL comment 'Estado de la jornada, ejemplo: activo e inactivo', 
  PRIMARY KEY (sigla_jornada));
CREATE TABLE trimestre (
  nombre_trimestre varchar(40) NOT NULL comment 'Nombre del trimestre, ejemplo: Trimestre 4', 
  nivel_formacion  varchar(20) NOT NULL comment 'Nivel de formacion academica', 
  jornada          varchar(20) NOT NULL comment 'Jornada en la que se dicta este trimestre', 
  PRIMARY KEY (nombre_trimestre, 
  nivel_formacion, 
  jornada));
CREATE TABLE planeacion_trimestre (
  planeacion         varchar(50) NOT NULL comment 'Nombre o codigo de la planeacion para el trimestre seleccionado', 
  nombre_trimestre   varchar(40) NOT NULL comment 'Nombre del trimestre al que pertenece', 
  nivel_formacion    varchar(20) NOT NULL comment 'Nivel de formacion para esta planeacion', 
  jornada            varchar(20) NOT NULL comment 'Jornada en la que se dara esta planeacion', 
  codigo_resultado   varchar(40) NOT NULL comment 'Codigo del resultado de aprendizaje', 
  codigo_competencia varchar(50) NOT NULL comment 'Codigo ubnico de la competencia', 
  progama_codigo     varchar(50) NOT NULL comment 'Codigo del programa al que pertenece', 
  programa_version   varchar(40) NOT NULL comment 'Version del programa al que pertenece', 
  PRIMARY KEY (planeacion, 
  nombre_trimestre, 
  nivel_formacion, 
  jornada, 
  codigo_resultado, 
  codigo_competencia, 
  progama_codigo, 
  programa_version));
CREATE TABLE nivel_formacion_tecnica (
  nivel_formacion varchar(20) NOT NULL comment 'Nivel de formacion, ejemplo: Tecnico, tecnologo e.t.c', 
  estado          varchar(50) comment 'Estado del nivel de formacion, ejemplo: activo e inactivo', 
  PRIMARY KEY (nivel_formacion));
CREATE TABLE programa (
  codigo          varchar(50) NOT NULL comment 'Codigo del programa', 
  version         varchar(40) NOT NULL comment 'Version del programa, ejemplo: Version 1 2019', 
  nombre          varchar(500) NOT NULL comment 'Nombre del programa de formacion', 
  sigla           varchar(40) NOT NULL comment 'Siglas de identificacion del proyecto', 
  estado          varchar(50) NOT NULL comment 'Estado del proyecto, ejemplo: activo e inactivo', 
  nivel_formacion varchar(20) NOT NULL comment 'nombre del nivel de formacion', 
  PRIMARY KEY (codigo, 
  version));
CREATE TABLE competencia (
  codigo_competencia varchar(50) NOT NULL comment 'Codigo de la competncia', 
  descripcion        varchar(1000) NOT NULL comment 'Descripcion de la competencia', 
  progama_codigo     varchar(50) NOT NULL comment 'Codigo unico del programa', 
  programa_version   varchar(40) NOT NULL comment 'Version del programa', 
  PRIMARY KEY (codigo_competencia, 
  progama_codigo, 
  programa_version));
CREATE TABLE resultado_aprendizaje (
  codigo_resultado   varchar(40) NOT NULL comment 'Codigo unico de identificacion del resultado de aprendizaje', 
  descripcion        varchar(1000) NOT NULL comment 'Descripcion del resultado de aprendizaje', 
  codigo_competencia varchar(50) NOT NULL comment 'Codigo unico de identificacion de la competencia a la que pertenece', 
  progama_codigo     varchar(50) NOT NULL comment 'Codigo del programa al que pertenece', 
  programa_version   varchar(40) NOT NULL comment 'Version del programa al que pertenece', 
  PRIMARY KEY (codigo_resultado, 
  codigo_competencia, 
  progama_codigo, 
  programa_version));
CREATE TABLE proyecto (
  codigo varchar(40) NOT NULL comment 'Codigo del proyecto', 
  nombre varchar(500) NOT NULL comment 'Nombre del proyecto, ejemplo: Analisis y desarrollo de sistemas de informacion', 
  estado varchar(50) NOT NULL comment 'Estado del proyecto, ejemplo: activo, inactivo', 
  PRIMARY KEY (codigo));
CREATE TABLE fase (
  nombre          varchar(40) NOT NULL comment 'Nombre de la fase', 
  estado          varchar(50) NOT NULL comment 'Estado de la fase del proyecto', 
  codigo_proyecto varchar(40) NOT NULL comment 'Codigo del proyecto', 
  PRIMARY KEY (nombre, 
  codigo_proyecto));
CREATE TABLE actividad (
  numero_actividad int(10) NOT NULL comment 'Numero de identificacion de la actividad', 
  nombre_actividad varchar(500) NOT NULL comment 'Nombre de actividad a llevar a cabo', 
  fase             varchar(40) NOT NULL comment 'Nombre de la fase', 
  codigo_proyecto  varchar(40) NOT NULL comment 'Codigo del proyecto', 
  PRIMARY KEY (numero_actividad, 
  fase, 
  codigo_proyecto));
CREATE TABLE servidor_correo_electronico (
  correo                 varchar(40) NOT NULL comment 'Correo desde el que se enviara el correo de renovacion de contraseña', 
  password               varchar(300) comment 'Password del correo', 
  smtp_host              varchar(40) comment 'Host al que se conectara para entrar al correo', 
  smtp_port              int(10), 
  smtop_start_tls_enable int(10), 
  smtp_authentication    int(10), 
  asunto_mensaje         varchar(100) comment 'Asunto del mail', 
  mensaje                varchar(1000) comment 'Lo que llevara escrito el mensaje de recuperacion', 
  PRIMARY KEY (correo));
CREATE TABLE actividad_planeacion (
  numero_actividad   int(10) NOT NULL comment 'Numero de identificacion de la actividad', 
  fase               varchar(40) NOT NULL comment 'Fase del proyecto a la que pertenece', 
  codigo_proyecto    varchar(40) NOT NULL comment 'Codigo del proyecto al que pertenece', 
  planeacion         varchar(50) NOT NULL comment 'Codigo de la planeacion a la que pertenece', 
  nombre_trimestre   varchar(40) NOT NULL comment 'Nombre del trimestre al que pertenece', 
  nivel_formacion    varchar(20) NOT NULL comment 'Nivel de formacion del proyecto', 
  jornada            varchar(20) NOT NULL comment 'Jornada en la que se daran estas actividades', 
  codigo_resultado   varchar(40) NOT NULL comment 'Codigo de identificacion del resultado de aprendizaje', 
  codigo_competencia varchar(50) NOT NULL comment 'Codigo de identificacion de la competencia', 
  progama_codigo     varchar(50) NOT NULL comment 'Codigo unico del programa de formacion', 
  programa_version   varchar(40) NOT NULL comment 'Version del programa de formacion', 
  PRIMARY KEY (numero_actividad, 
  fase, 
  codigo_proyecto, 
  planeacion, 
  nombre_trimestre, 
  nivel_formacion, 
  jornada, 
  codigo_resultado, 
  codigo_competencia, 
  progama_codigo, 
  programa_version));
CREATE TABLE especialidad (
  nombre_especialidad  varchar(40) NOT NULL comment 'Nombre de la especialidad', 
  estado               varchar(50) NOT NULL comment 'Estado de la especialidad, ejemplo: activo e inactivo', 
  nombre_especialidad2 varchar(40) NOT NULL, 
  PRIMARY KEY (nombre_especialidad));
CREATE TABLE vinculacion (
  tipo_vinculacion varchar(40) NOT NULL comment 'Tipo de vinculacion, ejemplo: Contratista, de planta', 
  horas            int(10) NOT NULL comment 'Horas que debe realizar', 
  estado           varchar(50) NOT NULL comment 'Estado de las especializaciones, ejemplo: activo o inactivo', 
  PRIMARY KEY (tipo_vinculacion));
CREATE TABLE instructor (
  tipo_documento   varchar(10) NOT NULL comment 'Tipo de documento, ejemplo: CC', 
  numero_documento varchar(50) NOT NULL comment 'Numero de documento del instructor', 
  tipo_vinculacion varchar(40) NOT NULL comment 'Tipo de vinculacion del instructor', 
  PRIMARY KEY (tipo_documento, 
  numero_documento));
CREATE TABLE disponibilidad_horaria (
  anio             date NOT NULL comment 'Anio en que tendra esa disponibilidad horaria', 
  hora_inicio      time NOT NULL comment 'hora en la que iniciara a laborar', 
  hora_fin         time comment 'Hora en la que terminara su labor', 
  tipo_documento   varchar(10) NOT NULL comment 'Tipo de documento', 
  numero_documento varchar(50) NOT NULL comment 'Numero de documento', 
  nombre_dia       varchar(40) NOT NULL comment 'Dia en el que tendra esa disponibilidad horaria', 
  sigla_jornada    varchar(20) NOT NULL comment 'Sigla de la jornada en la que laborara, ejemplo: D(diurna)', 
  PRIMARY KEY (anio, 
  hora_inicio, 
  tipo_documento, 
  numero_documento, 
  nombre_dia, 
  sigla_jornada));
CREATE TABLE disponibilidad_resultados (
  anio               date NOT NULL comment 'Anio en el que dictara esos resultados de aprendizaje', 
  codigo_resultado   varchar(40) NOT NULL comment 'Codigo de identificacion de los resultados de aprendizaje', 
  codigo_competencia varchar(50) NOT NULL comment 'Codigo de identificacion de la competencia', 
  progama_codigo     varchar(50) NOT NULL comment 'codigo unico del programa de formacion', 
  programa_version   varchar(40) NOT NULL comment 'Version del ptrograma de formacion', 
  tipo_documento     varchar(10) NOT NULL comment 'Tipo de documento', 
  numero_documento   varchar(50) NOT NULL comment 'Numero de documento', 
  PRIMARY KEY (anio, 
  codigo_resultado, 
  codigo_competencia, 
  progama_codigo, 
  programa_version, 
  tipo_documento, 
  numero_documento));
CREATE TABLE especialidad_instructor (
  tipo_documento      varchar(10) NOT NULL comment 'Tipo de documento del instructor', 
  numero_documento    varchar(50) NOT NULL comment 'Numero de documento del instructor', 
  nombre_especialidad varchar(40) NOT NULL comment 'Nombre de la especialidad', 
  PRIMARY KEY (tipo_documento, 
  numero_documento, 
  nombre_especialidad));
CREATE TABLE sede (
  nombre_sede varchar(50) NOT NULL comment 'Nombre de la sede', 
  direccion   varchar(400) NOT NULL comment 'Direccion de la sede', 
  estado      varchar(50) NOT NULL comment 'Estado de la sede, ejemplo: activo e inactivo', 
  PRIMARY KEY (nombre_sede));
CREATE TABLE ambiente (
  nombre_sede     varchar(50) NOT NULL comment 'Nombre de la sede donde esta el ambiente', 
  numero_ambiente varchar(50) NOT NULL comment 'Numero de identificacion del ambiente', 
  descripcion     varchar(500) NOT NULL comment 'Descripcion del habiente', 
  estado          varchar(50) NOT NULL comment 'Estado del ambiente, ejemplo: activo e inactivo', 
  tipo            varchar(40) NOT NULL comment 'Tipo de ambiente', 
  limitacion      varchar(40) NOT NULL comment 'Limite de personas por ambiente u otra limitacion', 
  PRIMARY KEY (nombre_sede, 
  numero_ambiente));
CREATE TABLE tipo_ambiente (
  tipo        varchar(40) NOT NULL comment 'Tipo de ambiente dependiendo de las clases que se dicten en este, ejemplo: matematicas, ingles o tecnica', 
  descripcion varchar(100) NOT NULL comment 'Descripcion del ambiente', 
  estado      varchar(50) NOT NULL comment 'Estado del ambiente, ejemplo: habilitado, inhabilitado', 
  PRIMARY KEY (tipo));
CREATE TABLE limitacion_ambiente (
  codigo_resultado   varchar(40) NOT NULL comment 'Codigo unico del resiltado de aprendizaje', 
  codigo_competencia varchar(50) NOT NULL comment 'Codigo de identificacion de la competencia', 
  progama_codigo     varchar(50) NOT NULL comment 'Codigo de identificacion del programa de formcaion', 
  programa_version   varchar(40) NOT NULL comment 'Version del programa de formacion', 
  numero_ambiente    varchar(50) NOT NULL comment 'Numero del ambiente en la sede', 
  nombre_sede        varchar(50) NOT NULL comment 'Nombre de la sede donde esta el ambiente', 
  PRIMARY KEY (codigo_resultado, 
  codigo_competencia, 
  progama_codigo, 
  programa_version, 
  numero_ambiente, 
  nombre_sede));
CREATE TABLE modalidad (
  nombre_modalidad varchar(40) NOT NULL comment 'Nombre de la modalidad, ejemplo: Presencial, Virtual', 
  color            varchar(40) NOT NULL comment 'Color de identificacion para esa modalidad', 
  estado           varchar(50) NOT NULL comment 'Estado de la modalidad para creacion con esta, ejemplo: Activo e inactivo', 
  PRIMARY KEY (nombre_modalidad));
CREATE TABLE dia (
  nombre_dia varchar(40) NOT NULL comment 'Nombre del dia de formacion, ejemplo: Lunes, Martes...', 
  estado     varchar(50) comment 'Estado del dia para creacion de horarios con ese dia, ejemplo: Activo e inactivo', 
  PRIMARY KEY (nombre_dia));
CREATE TABLE usuario_has_rol (
  tipo_documento    varchar(10) NOT NULL comment 'Tipo de documento del usuario', 
  numero_documento  varchar(50) NOT NULL comment 'Numero de documento del usuario', 
  rol               varchar(40) NOT NULL comment 'Rol del usuario', 
  estado            varchar(50) NOT NULL comment 'Estado del rol que desempeña', 
  fecha_terminacion date NOT NULL comment 'Fecha de terminacion del contrato para saber cuando deja de desempeñar ese rol', 
  PRIMARY KEY (tipo_documento, 
  numero_documento, 
  rol));
CREATE TABLE ficha_has_trimestre (
  numero_ficha     varchar(100) NOT NULL comment 'Numero de identificacion de cada ficha', 
  nombre_trimestre varchar(40) NOT NULL comment 'Nombre del trimestre', 
  nivel_formacion  varchar(20) NOT NULL comment 'Nivel de formacion academico', 
  jornada          varchar(20) NOT NULL comment 'Jornada en la que se dictara', 
  PRIMARY KEY (numero_ficha, 
  nombre_trimestre, 
  nivel_formacion, 
  jornada));
CREATE TABLE trimestre_vigente (
  anio                 date NOT NULL comment 'Anio en el que se esta cursando el trimestre', 
  trimestre_programado int(10) NOT NULL comment ' Numero del trimestre del anio, ejemplo:Trimestre 4( oct - dic)', 
  fecha_inicio         date NOT NULL comment 'Fecha de inicio de trimestre', 
  fecha_fin            date NOT NULL comment 'Fecha de final de trimestre', 
  estado               varchar(50) comment 'Estado del trimestre vigente, ejemplo: en ejecucion eihabilitado', 
  PRIMARY KEY (anio, 
  trimestre_programado));
CREATE TABLE version_horario (
  numero_version       varchar(40) NOT NULL comment 'Numero de version en la que va el horario, ejemplo: Version 1.1.1', 
  estado               varchar(50) NOT NULL comment 'Estado en el que encuentra la version del horario, ejemplo: Activo e inactivo', 
  anio                 date NOT NULL comment 'Anio del trimestre vigente', 
  trimestre_programado int(10) NOT NULL comment 'Numero del trimestre en el anio, ejemplo: Trimestre 4 (oct - dic )', 
  PRIMARY KEY (numero_version, 
  anio, 
  trimestre_programado));
CREATE TABLE horario (
  hora_inicio          time NOT NULL comment 'Hora de inicio de las clases', 
  hora_fin             time comment 'Hora final de las clases', 
  nombre_modalidad     varchar(40) NOT NULL comment 'Nombre de la modalidad, ejemplo: presencial', 
  nombre_dia           varchar(40) NOT NULL comment 'Nombre del dia donde se programa el horario', 
  nombre_sede          varchar(50) NOT NULL comment 'Nombre de la sede donde se programa el horario', 
  numero_ambiente      varchar(50) NOT NULL comment 'Ambiente donde se programa la clase', 
  numero_version       varchar(40) NOT NULL comment 'Version del horario vigente', 
  anio                 date NOT NULL comment 'Anio del horario', 
  trimestre_programado int(10) NOT NULL comment 'Trimestre programado, ejemplo: Trimestre 4 ( oct - dic )', 
  numero_ficha         varchar(100) NOT NULL comment 'Numero de identificacion de ficha', 
  trimestre_ficha      varchar(40) NOT NULL comment 'Trimestre que cursa la ficha', 
  nivel_formacion      varchar(20) NOT NULL comment 'Nivel de formacion academica, ejemplo: Tecnologo', 
  jornada              varchar(20) NOT NULL comment 'Jornada del horario', 
  tipo_documento       varchar(10) NOT NULL comment 'Tipo de documento del instructor', 
  numero_documento     varchar(50) NOT NULL comment 'Numero de documento del instructor', 
  PRIMARY KEY (hora_inicio, 
  nombre_dia, 
  nombre_sede, 
  numero_ambiente, 
  numero_version, 
  anio, 
  trimestre_programado, 
  numero_ficha, 
  trimestre_ficha, 
  nivel_formacion, 
  jornada, 
  tipo_documento, 
  numero_documento));
CREATE TABLE aprendiz (
  tipo_documento   varchar(10) NOT NULL comment 'Tipo de documento de Usuario', 
  numero_documento varchar(50) NOT NULL comment 'Numero de documento del usuario', 
  estado_formacion varchar(40) NOT NULL comment 'Estado de formacion del aprendiz, ejemplo: desertado, en ejecucion', 
  numero_ficha     varchar(100) NOT NULL comment 'Numero de indentificacion unico de la ficha', 
  PRIMARY KEY (tipo_documento, 
  numero_documento, 
  numero_ficha));
CREATE TABLE estado_formacion_ficha (
  estado_formacion varchar(40) NOT NULL comment 'Nombre del estado de formcaion, ejemplo: Suspendido, Desertado, en ejecucion', 
  estado           varchar(50) comment 'Estado del estado de formacion para permitir o denegar la creacion de estos, ejemplo: Activo e inactivo', 
  PRIMARY KEY (estado_formacion));
CREATE TABLE planeacion_para_trimestres (
  planeacion varchar(50) NOT NULL comment 'Codigo de la planeacion', 
  estado     varchar(50) NOT NULL comment 'Estado de la planeacion, ejemplo: activo e inactivo', 
  PRIMARY KEY (planeacion));
CREATE TABLE resultados_vistos (
  numero_ficha       varchar(100) NOT NULL comment 'Numero de identificacion de cada ficha', 
  nombre_trimestre   varchar(40) NOT NULL comment 'Nombre del trimestre donde se ven esos resultados', 
  nivel_formacion    varchar(20) NOT NULL comment 'Nivel de formacion academica de esos resultados', 
  jornada            varchar(20) NOT NULL comment 'Jornada en la que se dictaron', 
  codigo_resultado   varchar(40) NOT NULL comment 'Codigo unico del resultado de aprendizaje', 
  codigo_competencia varchar(50) NOT NULL comment 'Cofigo unico de la competencia', 
  progama_codigo     varchar(50) NOT NULL comment 'Codigo unico del programa de formacion', 
  programa_version   varchar(40) NOT NULL comment 'Version del programa de formacion', 
  PRIMARY KEY (numero_ficha, 
  nombre_trimestre, 
  nivel_formacion, 
  jornada, 
  codigo_resultado, 
  codigo_competencia, 
  progama_codigo, 
  programa_version), 
  INDEX (nivel_formacion));
ALTER TABLE usuario_has_rol ADD CONSTRAINT FKusuario_ha256663 FOREIGN KEY (rol) REFERENCES rol_usuario (rol);
ALTER TABLE usuario ADD CONSTRAINT FKusuario614376 FOREIGN KEY (tipo_documento) REFERENCES tipo_de_documento (tipo_documento);
ALTER TABLE usuario_has_rol ADD CONSTRAINT FKusuario_ha757373 FOREIGN KEY (tipo_documento, numero_documento) REFERENCES usuario (tipo_documento, numero_documento);
ALTER TABLE programa ADD CONSTRAINT FKprograma862285 FOREIGN KEY (nivel_formacion) REFERENCES nivel_formacion_tecnica (nivel_formacion);
ALTER TABLE competencia ADD CONSTRAINT FKcompetenci133437 FOREIGN KEY (progama_codigo, programa_version) REFERENCES programa (codigo, version);
ALTER TABLE trimestre ADD CONSTRAINT FKtrimestre540306 FOREIGN KEY (nivel_formacion) REFERENCES nivel_formacion_tecnica (nivel_formacion);
ALTER TABLE trimestre ADD CONSTRAINT FKtrimestre547723 FOREIGN KEY (jornada) REFERENCES jornada (sigla_jornada);
ALTER TABLE ficha ADD CONSTRAINT FKficha254075 FOREIGN KEY (programa_codigo, programa_version) REFERENCES programa (codigo, version);
ALTER TABLE resultado_aprendizaje ADD CONSTRAINT FKresultado_816070 FOREIGN KEY (codigo_competencia, progama_codigo, programa_version) REFERENCES competencia (codigo_competencia, progama_codigo, programa_version);
ALTER TABLE ficha_has_trimestre ADD CONSTRAINT FKficha_has_892259 FOREIGN KEY (numero_ficha) REFERENCES ficha (numero_ficha);
ALTER TABLE ficha_has_trimestre ADD CONSTRAINT FKficha_has_297675 FOREIGN KEY (nombre_trimestre, nivel_formacion, jornada) REFERENCES trimestre (nombre_trimestre, nivel_formacion, jornada);
ALTER TABLE fase ADD CONSTRAINT FKfase882171 FOREIGN KEY (codigo_proyecto) REFERENCES proyecto (codigo);
ALTER TABLE actividad ADD CONSTRAINT FKactividad369983 FOREIGN KEY (fase, codigo_proyecto) REFERENCES fase (nombre, codigo_proyecto);
ALTER TABLE ficha ADD CONSTRAINT FKficha206261 FOREIGN KEY (estado_ficha) REFERENCES estado_ficha (nombre_estado);
ALTER TABLE actividad_planeacion ADD CONSTRAINT FKactividad_13110 FOREIGN KEY (numero_actividad, fase, codigo_proyecto) REFERENCES actividad (numero_actividad, fase, codigo_proyecto);
ALTER TABLE instructor ADD CONSTRAINT FKinstructor568576 FOREIGN KEY (tipo_documento, numero_documento) REFERENCES usuario (tipo_documento, numero_documento);
ALTER TABLE especialidad_instructor ADD CONSTRAINT FKespecialid934830 FOREIGN KEY (tipo_documento, numero_documento) REFERENCES instructor (tipo_documento, numero_documento);
ALTER TABLE disponibilidad_horaria ADD CONSTRAINT FKdisponibil263896 FOREIGN KEY (tipo_documento, numero_documento) REFERENCES instructor (tipo_documento, numero_documento);
ALTER TABLE ambiente ADD CONSTRAINT FKambiente419998 FOREIGN KEY (nombre_sede) REFERENCES sede (nombre_sede);
ALTER TABLE planeacion_trimestre ADD CONSTRAINT FKplaneacion112995 FOREIGN KEY (nombre_trimestre, nivel_formacion, jornada) REFERENCES trimestre (nombre_trimestre, nivel_formacion, jornada);
ALTER TABLE planeacion_trimestre ADD CONSTRAINT FKplaneacion531452 FOREIGN KEY (planeacion) REFERENCES planeacion_para_trimestres (planeacion);
ALTER TABLE planeacion_trimestre ADD CONSTRAINT FKplaneacion595119 FOREIGN KEY (codigo_resultado, codigo_competencia, progama_codigo, programa_version) REFERENCES resultado_aprendizaje (codigo_resultado, codigo_competencia, progama_codigo, programa_version);
ALTER TABLE actividad_planeacion ADD CONSTRAINT FKactividad_875256 FOREIGN KEY (planeacion, nombre_trimestre, nivel_formacion, jornada, codigo_resultado, codigo_competencia, progama_codigo, programa_version) REFERENCES planeacion_trimestre (planeacion, nombre_trimestre, nivel_formacion, jornada, codigo_resultado, codigo_competencia, progama_codigo, programa_version);
ALTER TABLE ambiente ADD CONSTRAINT FKambiente746564 FOREIGN KEY (tipo) REFERENCES tipo_ambiente (tipo);
ALTER TABLE limitacion_ambiente ADD CONSTRAINT FKlimitacion749503 FOREIGN KEY (codigo_resultado, codigo_competencia, progama_codigo, programa_version) REFERENCES resultado_aprendizaje (codigo_resultado, codigo_competencia, progama_codigo, programa_version);
ALTER TABLE limitacion_ambiente ADD CONSTRAINT FKlimitacion1905 FOREIGN KEY (nombre_sede, numero_ambiente) REFERENCES ambiente (nombre_sede, numero_ambiente);
ALTER TABLE instructor ADD CONSTRAINT FKinstructor136532 FOREIGN KEY (tipo_vinculacion) REFERENCES vinculacion (tipo_vinculacion);
ALTER TABLE disponibilidad_horaria ADD CONSTRAINT FKdisponibil646326 FOREIGN KEY (nombre_dia) REFERENCES dia (nombre_dia);
ALTER TABLE disponibilidad_horaria ADD CONSTRAINT FKdisponibil143100 FOREIGN KEY (sigla_jornada) REFERENCES jornada (sigla_jornada);
ALTER TABLE disponibilidad_resultados ADD CONSTRAINT FKdisponibil453750 FOREIGN KEY (codigo_resultado, codigo_competencia, progama_codigo, programa_version) REFERENCES resultado_aprendizaje (codigo_resultado, codigo_competencia, progama_codigo, programa_version);
ALTER TABLE disponibilidad_resultados ADD CONSTRAINT FKdisponibil470762 FOREIGN KEY (tipo_documento, numero_documento) REFERENCES instructor (tipo_documento, numero_documento);
ALTER TABLE version_horario ADD CONSTRAINT FKversion_ho448043 FOREIGN KEY (anio, trimestre_programado) REFERENCES trimestre_vigente (anio, trimestre_programado);
ALTER TABLE horario ADD CONSTRAINT FKhorario484746 FOREIGN KEY (nombre_modalidad) REFERENCES modalidad (nombre_modalidad);
ALTER TABLE horario ADD CONSTRAINT FKhorario399480 FOREIGN KEY (nombre_sede, numero_ambiente) REFERENCES ambiente (nombre_sede, numero_ambiente);
ALTER TABLE resultados_vistos ADD CONSTRAINT FKresultados918827 FOREIGN KEY (numero_ficha, nombre_trimestre, nivel_formacion, jornada) REFERENCES ficha_has_trimestre (numero_ficha, nombre_trimestre, nivel_formacion, jornada);
ALTER TABLE resultados_vistos ADD CONSTRAINT FKresultados349462 FOREIGN KEY (codigo_resultado, codigo_competencia, progama_codigo, programa_version) REFERENCES resultado_aprendizaje (codigo_resultado, codigo_competencia, progama_codigo, programa_version);
ALTER TABLE horario ADD CONSTRAINT FKhorario384297 FOREIGN KEY (numero_ficha, trimestre_ficha, nivel_formacion, jornada) REFERENCES ficha_has_trimestre (numero_ficha, nombre_trimestre, nivel_formacion, jornada);
ALTER TABLE horario ADD CONSTRAINT FKhorario334916 FOREIGN KEY (tipo_documento, numero_documento) REFERENCES instructor (tipo_documento, numero_documento);
ALTER TABLE aprendiz ADD CONSTRAINT FKaprendiz818695 FOREIGN KEY (tipo_documento, numero_documento) REFERENCES usuario (tipo_documento, numero_documento);
ALTER TABLE aprendiz ADD CONSTRAINT FKaprendiz343705 FOREIGN KEY (estado_formacion) REFERENCES estado_formacion_ficha (estado_formacion);
ALTER TABLE aprendiz ADD CONSTRAINT FKaprendiz831278 FOREIGN KEY (numero_ficha) REFERENCES ficha (numero_ficha);
ALTER TABLE horario ADD CONSTRAINT FKhorario575306 FOREIGN KEY (nombre_dia) REFERENCES dia (nombre_dia);
ALTER TABLE horario ADD CONSTRAINT FKhorario405721 FOREIGN KEY (numero_version, anio, trimestre_programado) REFERENCES version_horario (numero_version, anio, trimestre_programado);
ALTER TABLE especialidad_instructor ADD CONSTRAINT FKespecialid389624 FOREIGN KEY (nombre_especialidad) REFERENCES especialidad (nombre_especialidad);
