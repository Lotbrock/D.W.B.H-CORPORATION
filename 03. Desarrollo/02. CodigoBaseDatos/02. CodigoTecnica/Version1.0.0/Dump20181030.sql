-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sena_horarios
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actividad`
--

DROP TABLE IF EXISTS `actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `actividad` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id de las activdades por fase',
  `id_fase` int(11) NOT NULL COMMENT 'id fase en la que se da la actividad',
  `numero_actividad` int(11) NOT NULL COMMENT 'numero de identificacion de la actividad',
  `nombre_actividad` varchar(400) NOT NULL COMMENT 'Nombre e informacion de la actividad',
  PRIMARY KEY (`id`),
  UNIQUE KEY `actividad_idx` (`id_fase`,`numero_actividad`),
  CONSTRAINT `fk_fase_acti` FOREIGN KEY (`id_fase`) REFERENCES `fase` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad`
--

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actividad_planeacion`
--

DROP TABLE IF EXISTS `actividad_planeacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `actividad_planeacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id de actividad por planeacion',
  `id_actividad` int(11) NOT NULL COMMENT 'Id de la actividad',
  `id_planeacion_trimestre` int(11) NOT NULL COMMENT 'Id de la planeacion por trimestre',
  PRIMARY KEY (`id`),
  UNIQUE KEY `actividad_planeacion_idx` (`id_actividad`,`id_planeacion_trimestre`),
  KEY `fk_pltr_acpl` (`id_planeacion_trimestre`),
  CONSTRAINT `fk_acti_acpl` FOREIGN KEY (`id_actividad`) REFERENCES `actividad` (`id`),
  CONSTRAINT `fk_pltr_acpl` FOREIGN KEY (`id_planeacion_trimestre`) REFERENCES `planeacion_trimestre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad_planeacion`
--

LOCK TABLES `actividad_planeacion` WRITE;
/*!40000 ALTER TABLE `actividad_planeacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividad_planeacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ambiente`
--

DROP TABLE IF EXISTS `ambiente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ambiente` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id ambiente',
  `id_sede` int(11) NOT NULL COMMENT 'Id de la sede donde esta',
  `numero_ambiente` varchar(50) NOT NULL COMMENT 'Numero de indetificacion del ambiente dentro de la sede, ejemp: Ambiente 301',
  `descripcion` varchar(1000) NOT NULL COMMENT 'Descripcion del ambiente',
  `estado` varchar(40) NOT NULL COMMENT 'Estado del ambiente, ejemp: activo, inactivo',
  `limitacion` varchar(40) NOT NULL COMMENT 'Limite de personas por ambiente u otra limitacion',
  `id_tipo_ambiente` int(11) NOT NULL COMMENT 'Id del tipo de ambiente',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ambiente_idx` (`id_sede`,`numero_ambiente`),
  KEY `fk_tiam_ambi` (`id_tipo_ambiente`),
  CONSTRAINT `fk_sede_ambi` FOREIGN KEY (`id_sede`) REFERENCES `sede` (`id`),
  CONSTRAINT `fk_tiam_ambi` FOREIGN KEY (`id_tipo_ambiente`) REFERENCES `tipo_ambiente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ambiente`
--

LOCK TABLES `ambiente` WRITE;
/*!40000 ALTER TABLE `ambiente` DISABLE KEYS */;
/*!40000 ALTER TABLE `ambiente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aprendiz`
--

DROP TABLE IF EXISTS `aprendiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `aprendiz` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id del aprendiz',
  `id_ficha` int(11) NOT NULL COMMENT 'id de la ficha',
  `id_estado_formacion` int(11) NOT NULL COMMENT 'id del estado de formacion del aprendiz',
  PRIMARY KEY (`id`),
  UNIQUE KEY `aprendiz_idx` (`id_ficha`,`id_estado_formacion`),
  KEY `fk_esta_apre` (`id_estado_formacion`),
  CONSTRAINT `fk_esta_apre` FOREIGN KEY (`id_estado_formacion`) REFERENCES `estado_formacion` (`id`),
  CONSTRAINT `fk_fich_apre` FOREIGN KEY (`id_ficha`) REFERENCES `ficha` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aprendiz`
--

LOCK TABLES `aprendiz` WRITE;
/*!40000 ALTER TABLE `aprendiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `aprendiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competencia`
--

DROP TABLE IF EXISTS `competencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `competencia` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id de la competencia',
  `id_programa` int(11) NOT NULL COMMENT 'Id del programa de formacion',
  `codigo_competencia` varchar(50) NOT NULL COMMENT 'Codigo unico de la competencia',
  `descripcion` varchar(1000) NOT NULL COMMENT 'Descripcion de la competencia',
  PRIMARY KEY (`id`),
  UNIQUE KEY `competencia_idx` (`codigo_competencia`,`id_programa`),
  KEY `fk_prog_comp` (`id_programa`),
  CONSTRAINT `fk_prog_comp` FOREIGN KEY (`id_programa`) REFERENCES `programa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competencia`
--

LOCK TABLES `competencia` WRITE;
/*!40000 ALTER TABLE `competencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `competencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dia`
--

DROP TABLE IF EXISTS `dia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dia` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id del dia',
  `nombre_dia` varchar(40) NOT NULL COMMENT 'Nombre del dia, ejemp: lunes',
  `estado` varchar(40) NOT NULL COMMENT 'Estado del dia de formacion, ejemp: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_dia` (`nombre_dia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dia`
--

LOCK TABLES `dia` WRITE;
/*!40000 ALTER TABLE `dia` DISABLE KEYS */;
/*!40000 ALTER TABLE `dia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disponibilidad_horaria`
--

DROP TABLE IF EXISTS `disponibilidad_horaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `disponibilidad_horaria` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id de la disponibilidad horaria',
  `anio` date NOT NULL COMMENT 'Anio en el que tendra esa disponibilidad',
  `hora_inicio` time NOT NULL COMMENT 'Hora en la que empezara a enseñar',
  `id_jornada` int(11) NOT NULL COMMENT 'Id de la jornada en la que enseñara',
  `id_instructor` int(11) NOT NULL COMMENT 'Id del instructor',
  `id_dia` int(11) NOT NULL COMMENT 'Dias en los que dicartara clases',
  `hora_fin` time NOT NULL COMMENT 'Hora en la que finaliza su turno',
  PRIMARY KEY (`id`),
  UNIQUE KEY `disponibilidad_horaria_idx` (`anio`,`hora_inicio`,`id_jornada`,`id_instructor`,`id_dia`),
  KEY `fk_inst_diho` (`id_instructor`),
  KEY `fk_dia_diho` (`id_dia`),
  KEY `fk_jorn_diho` (`id_jornada`),
  CONSTRAINT `fk_dia_diho` FOREIGN KEY (`id_dia`) REFERENCES `dia` (`id`),
  CONSTRAINT `fk_inst_diho` FOREIGN KEY (`id_instructor`) REFERENCES `instructor` (`id`),
  CONSTRAINT `fk_jorn_diho` FOREIGN KEY (`id_jornada`) REFERENCES `jornada` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disponibilidad_horaria`
--

LOCK TABLES `disponibilidad_horaria` WRITE;
/*!40000 ALTER TABLE `disponibilidad_horaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `disponibilidad_horaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disponibilidad_resultados`
--

DROP TABLE IF EXISTS `disponibilidad_resultados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `disponibilidad_resultados` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id disponibilidad de resultados para enseñar',
  `anio` date NOT NULL COMMENT 'Anio en el que dictara esos resultados de aprendizaje',
  `id_resultado_aprendizaje` int(11) NOT NULL COMMENT 'Id resultados de aprendizaje que dictara',
  `id_instructor` int(11) NOT NULL COMMENT 'Id del instructor',
  PRIMARY KEY (`id`),
  UNIQUE KEY `disponibilidad_resultados_idx` (`anio`,`id_instructor`,`id_resultado_aprendizaje`),
  KEY `fk_reap_dire` (`id_resultado_aprendizaje`),
  KEY `fk_intr_dire` (`id_instructor`),
  CONSTRAINT `fk_intr_dire` FOREIGN KEY (`id_instructor`) REFERENCES `instructor` (`id`),
  CONSTRAINT `fk_reap_dire` FOREIGN KEY (`id_resultado_aprendizaje`) REFERENCES `resultado_aprendizaje` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disponibilidad_resultados`
--

LOCK TABLES `disponibilidad_resultados` WRITE;
/*!40000 ALTER TABLE `disponibilidad_resultados` DISABLE KEYS */;
/*!40000 ALTER TABLE `disponibilidad_resultados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidad`
--

DROP TABLE IF EXISTS `especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `especialidad` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id de la especialidad',
  `nombre_especialidad` varchar(40) NOT NULL COMMENT 'Nombre de la especialidad',
  `estado` varchar(40) NOT NULL COMMENT 'Estado de la especialidad, ejemp: activo, inactivo',
  `url_logo` varchar(400) DEFAULT NULL COMMENT 'Url del logo de la especialidad',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_especialidad` (`nombre_especialidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidad`
--

LOCK TABLES `especialidad` WRITE;
/*!40000 ALTER TABLE `especialidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `especialidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidad_instructor`
--

DROP TABLE IF EXISTS `especialidad_instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `especialidad_instructor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id de la especialidad(es) del instructor',
  `id_especialidad` int(11) NOT NULL COMMENT 'Id especialidad',
  `id_instructor` int(11) NOT NULL COMMENT 'Id del instructor',
  PRIMARY KEY (`id`),
  UNIQUE KEY `especialidad_instructor_idx` (`id_especialidad`,`id_instructor`),
  KEY `fk_intr_esin` (`id_instructor`),
  CONSTRAINT `fk_esp_esin` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidad` (`id`),
  CONSTRAINT `fk_intr_esin` FOREIGN KEY (`id_instructor`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidad_instructor`
--

LOCK TABLES `especialidad_instructor` WRITE;
/*!40000 ALTER TABLE `especialidad_instructor` DISABLE KEYS */;
/*!40000 ALTER TABLE `especialidad_instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_ficha`
--

DROP TABLE IF EXISTS `estado_ficha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `estado_ficha` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id del estado de ficha',
  `nombre_estado` int(11) NOT NULL COMMENT 'Nombre del estado de de la ficha, ejem: activa, en Formacion, suspendida',
  `estado` smallint(6) NOT NULL COMMENT 'estado del estadp de ña ficha, (activo, inactivo)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_estado` (`nombre_estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_ficha`
--

LOCK TABLES `estado_ficha` WRITE;
/*!40000 ALTER TABLE `estado_ficha` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_ficha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_formacion`
--

DROP TABLE IF EXISTS `estado_formacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `estado_formacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id del estado de formacion',
  `nombre_estado` varchar(40) NOT NULL COMMENT 'nombre del estado, ejem: en formacion, suspendido, aplazamiento',
  `estado` varchar(40) NOT NULL COMMENT 'estado del estado, ejem: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_estado` (`nombre_estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_formacion`
--

LOCK TABLES `estado_formacion` WRITE;
/*!40000 ALTER TABLE `estado_formacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_formacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fase`
--

DROP TABLE IF EXISTS `fase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fase` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id de fase del proyecto',
  `id_proyecto` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL COMMENT 'nombre de la fase, ejem: Analisis, desarrollo',
  `estado` varchar(40) NOT NULL COMMENT 'estado, ejem: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fase_idx` (`id_proyecto`,`nombre`),
  CONSTRAINT `fk_proy_fase` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fase`
--

LOCK TABLES `fase` WRITE;
/*!40000 ALTER TABLE `fase` DISABLE KEYS */;
/*!40000 ALTER TABLE `fase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ficha`
--

DROP TABLE IF EXISTS `ficha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ficha` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id de la ficha',
  `id_programa` int(11) NOT NULL COMMENT 'id del programa de formacion',
  `numero_ficha` varchar(100) NOT NULL COMMENT 'Numero de identificacion de la ficha, ejem: 1566614 G1-G2',
  `fecha_inicio` date NOT NULL COMMENT 'Fecha de inicio de formacion',
  `fecha_fin` date NOT NULL COMMENT 'Fecha de fin de formacion',
  `ruta` varchar(40) NOT NULL COMMENT 'Codigo ruta de aprendizaje',
  `id_estado_ficha` int(11) NOT NULL COMMENT 'Id del estado de ficha',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ficha_idx` (`id_programa`,`numero_ficha`),
  KEY `fk_exfi_fich` (`id_estado_ficha`),
  CONSTRAINT `fk_exfi_fich` FOREIGN KEY (`id_estado_ficha`) REFERENCES `estado_ficha` (`id`),
  CONSTRAINT `fk_prog_fich` FOREIGN KEY (`id_programa`) REFERENCES `programa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ficha`
--

LOCK TABLES `ficha` WRITE;
/*!40000 ALTER TABLE `ficha` DISABLE KEYS */;
/*!40000 ALTER TABLE `ficha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ficha_has_trimestre`
--

DROP TABLE IF EXISTS `ficha_has_trimestre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ficha_has_trimestre` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id many to many de fichas tiene trimestre',
  `id_ficha` int(11) NOT NULL COMMENT 'id de la ficha',
  `id_trimestre` int(11) NOT NULL COMMENT 'Trimestre que cursa la ficha',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ficha_has_trimestre_idx` (`id_ficha`,`id_trimestre`),
  KEY `fk_trim_fitr` (`id_trimestre`),
  CONSTRAINT `FKficha_has_560817` FOREIGN KEY (`id_ficha`) REFERENCES `ficha` (`id`),
  CONSTRAINT `fk_trim_fitr` FOREIGN KEY (`id_trimestre`) REFERENCES `trimestre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ficha_has_trimestre`
--

LOCK TABLES `ficha_has_trimestre` WRITE;
/*!40000 ALTER TABLE `ficha_has_trimestre` DISABLE KEYS */;
/*!40000 ALTER TABLE `ficha_has_trimestre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario`
--

DROP TABLE IF EXISTS `horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `horario` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id del horario',
  `hora_inicio` time NOT NULL COMMENT 'Hora de inicio de clases',
  `id_ficha_has_trimestre` int(11) NOT NULL COMMENT 'Id de ficha y su respect6ivo trimestre',
  `id_instructor` int(11) NOT NULL COMMENT 'Id del instructor',
  `id_dia` int(11) NOT NULL COMMENT 'Id del dia',
  `id_ambiente` int(11) NOT NULL COMMENT 'Id del ambiente',
  `id_version_horario` int(11) NOT NULL COMMENT 'Id de la version del horario',
  `hora_fin` time NOT NULL COMMENT 'Hora fin de clases',
  `id_modalidad` int(11) NOT NULL COMMENT 'Id de modalidad',
  PRIMARY KEY (`id`),
  UNIQUE KEY `horario_idx` (`hora_inicio`,`id_ficha_has_trimestre`,`id_instructor`,`id_dia`,`id_ambiente`,`id_version_horario`),
  KEY `fk_moda_hora` (`id_modalidad`),
  KEY `fk_dia_hora` (`id_dia`),
  KEY `fk_ambi_hora` (`id_ambiente`),
  KEY `fk_veho_hora` (`id_version_horario`),
  KEY `fk_fitr_hora` (`id_ficha_has_trimestre`),
  KEY `fk_inst_hora` (`id_instructor`),
  CONSTRAINT `fk_ambi_hora` FOREIGN KEY (`id_ambiente`) REFERENCES `ambiente` (`id`),
  CONSTRAINT `fk_dia_hora` FOREIGN KEY (`id_dia`) REFERENCES `dia` (`id`),
  CONSTRAINT `fk_fitr_hora` FOREIGN KEY (`id_ficha_has_trimestre`) REFERENCES `ficha_has_trimestre` (`id`),
  CONSTRAINT `fk_inst_hora` FOREIGN KEY (`id_instructor`) REFERENCES `instructor` (`id`),
  CONSTRAINT `fk_moda_hora` FOREIGN KEY (`id_modalidad`) REFERENCES `modalidad` (`id`),
  CONSTRAINT `fk_veho_hora` FOREIGN KEY (`id_version_horario`) REFERENCES `version_horario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario`
--

LOCK TABLES `horario` WRITE;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `instructor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id del instructor',
  `id_vinculacion` int(11) NOT NULL COMMENT 'Id vinculacion(tipo de vinculacion)',
  `id_usuario` int(11) NOT NULL COMMENT 'Id de ususario',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_usuario` (`id_usuario`),
  KEY `fk_vinc_inst` (`id_vinculacion`),
  CONSTRAINT `fk_clie_inst` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_vinc_inst` FOREIGN KEY (`id_vinculacion`) REFERENCES `vinculacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jornada`
--

DROP TABLE IF EXISTS `jornada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `jornada` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id de jornada',
  `sigla_jornada` varchar(20) NOT NULL COMMENT 'sigla jornada, ejem: D(diurna)',
  `nombre_jornada` varchar(40) NOT NULL COMMENT 'Nombre de la jornada, Ejem: Nocturna, Diurna',
  `descripcion` varchar(40) NOT NULL COMMENT 'Descripcion de la jornada, horas y dias de formacion',
  `imagen_url` varchar(400) DEFAULT NULL COMMENT 'imagen que identifica a la jornada',
  `estado` varchar(40) NOT NULL COMMENT 'estado de la jornada, ejemp: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sigla_jornada` (`sigla_jornada`),
  UNIQUE KEY `nombre_jornada` (`nombre_jornada`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornada`
--

LOCK TABLES `jornada` WRITE;
/*!40000 ALTER TABLE `jornada` DISABLE KEYS */;
/*!40000 ALTER TABLE `jornada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `limitacion_ambiente`
--

DROP TABLE IF EXISTS `limitacion_ambiente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `limitacion_ambiente` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id limitacion del ambiente',
  `id_resultado_aprendizaje` int(11) NOT NULL COMMENT 'Id de resultados de aprendizaje que se pueden ver en este ambiente',
  `id_ambiente` int(11) NOT NULL COMMENT 'Id del ambiente',
  PRIMARY KEY (`id`),
  UNIQUE KEY `limitacion_ambiente_idx` (`id_resultado_aprendizaje`,`id_ambiente`),
  KEY `fk_ambi_liam` (`id_ambiente`),
  CONSTRAINT `fk_ambi_liam` FOREIGN KEY (`id_ambiente`) REFERENCES `ambiente` (`id`),
  CONSTRAINT `fk_reap_liam` FOREIGN KEY (`id_resultado_aprendizaje`) REFERENCES `resultado_aprendizaje` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `limitacion_ambiente`
--

LOCK TABLES `limitacion_ambiente` WRITE;
/*!40000 ALTER TABLE `limitacion_ambiente` DISABLE KEYS */;
/*!40000 ALTER TABLE `limitacion_ambiente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modalidad`
--

DROP TABLE IF EXISTS `modalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `modalidad` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id de modalidad de formacion',
  `nombre_modalidad` varchar(40) NOT NULL COMMENT 'Nombre de modalidad, ejemp: Presencial, virtual',
  `color` varchar(50) NOT NULL COMMENT 'Color que identifica la modalidad',
  `estado` varchar(40) NOT NULL COMMENT 'Estado de la modalidad, ejemp: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_modalidad` (`nombre_modalidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modalidad`
--

LOCK TABLES `modalidad` WRITE;
/*!40000 ALTER TABLE `modalidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `modalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nivel_formacion`
--

DROP TABLE IF EXISTS `nivel_formacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `nivel_formacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id del nivel de formacion',
  `nivel` varchar(20) NOT NULL COMMENT 'Nivel de formacion, ejem: Tecnico, Tecnologo',
  `estado` varchar(40) NOT NULL COMMENT 'Estado del nivel de formacion, ejemp: Activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nivel` (`nivel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nivel_formacion`
--

LOCK TABLES `nivel_formacion` WRITE;
/*!40000 ALTER TABLE `nivel_formacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `nivel_formacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planeacion`
--

DROP TABLE IF EXISTS `planeacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `planeacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id de planeacion',
  `codigo_planeacion` varchar(50) NOT NULL COMMENT 'codigo de planeacion',
  `estado` varchar(40) NOT NULL COMMENT 'estado, ejem: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_planeacion` (`codigo_planeacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planeacion`
--

LOCK TABLES `planeacion` WRITE;
/*!40000 ALTER TABLE `planeacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `planeacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planeacion_trimestre`
--

DROP TABLE IF EXISTS `planeacion_trimestre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `planeacion_trimestre` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id de la planeacion para el trimestre',
  `id_resultado_aprendizaje` int(11) NOT NULL COMMENT 'id del resultado de aprendizaje para el trimestre',
  `id_trimestre` int(11) NOT NULL COMMENT 'id del trimestre sobre el que se planea',
  `id_planeacion` int(11) NOT NULL COMMENT 'id de planeacion',
  PRIMARY KEY (`id`),
  UNIQUE KEY `planeacion_trimestre_idx` (`id_resultado_aprendizaje`,`id_trimestre`,`id_planeacion`),
  KEY `fk_trim_pltr` (`id_trimestre`),
  KEY `fk_plan_pltr` (`id_planeacion`),
  CONSTRAINT `fk_plan_pltr` FOREIGN KEY (`id_planeacion`) REFERENCES `planeacion` (`id`),
  CONSTRAINT `fk_reap_pltr` FOREIGN KEY (`id_resultado_aprendizaje`) REFERENCES `resultado_aprendizaje` (`id`),
  CONSTRAINT `fk_trim_pltr` FOREIGN KEY (`id_trimestre`) REFERENCES `trimestre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planeacion_trimestre`
--

LOCK TABLES `planeacion_trimestre` WRITE;
/*!40000 ALTER TABLE `planeacion_trimestre` DISABLE KEYS */;
/*!40000 ALTER TABLE `planeacion_trimestre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programa`
--

DROP TABLE IF EXISTS `programa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `programa` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id del programa',
  `codigo` varchar(50) NOT NULL COMMENT 'Codigo del programa',
  `version` varchar(40) NOT NULL COMMENT 'Version del programa, ejem: Programa01 version 2018',
  `nombre` varchar(500) NOT NULL COMMENT 'Nombre del programa',
  `sigla` varchar(40) NOT NULL COMMENT 'Sigla del programa, ejemp: Adsi',
  `estado` varchar(40) NOT NULL COMMENT 'Estado del programa, ejemp: activo, inactivo',
  `id_nivel_formacion` int(11) NOT NULL COMMENT 'id del nivel de formacion',
  PRIMARY KEY (`id`),
  UNIQUE KEY `programa_idx` (`codigo`,`version`),
  KEY `fk_nifo_prog` (`id_nivel_formacion`),
  CONSTRAINT `fk_nifo_prog` FOREIGN KEY (`id_nivel_formacion`) REFERENCES `nivel_formacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programa`
--

LOCK TABLES `programa` WRITE;
/*!40000 ALTER TABLE `programa` DISABLE KEYS */;
/*!40000 ALTER TABLE `programa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyecto`
--

DROP TABLE IF EXISTS `proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proyecto` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id proyecto',
  `codigo` varchar(40) NOT NULL COMMENT 'codigo identificatorio unico del proyecto',
  `nombre` varchar(500) NOT NULL COMMENT 'Nombre del proyecto ejem: Adsi(Analisis y desarrollo de sistemas de informacion)',
  `estado` varchar(40) NOT NULL COMMENT 'Estado ejem: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyecto`
--

LOCK TABLES `proyecto` WRITE;
/*!40000 ALTER TABLE `proyecto` DISABLE KEYS */;
/*!40000 ALTER TABLE `proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resultado_aprendizaje`
--

DROP TABLE IF EXISTS `resultado_aprendizaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `resultado_aprendizaje` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id del resultado de aprendizaje',
  `codigo_resultado` varchar(40) NOT NULL COMMENT 'Codigo del resultado de aprendizaje',
  `id_competencia` int(11) NOT NULL COMMENT 'Id de la competencia',
  `descripcion` varchar(1000) NOT NULL COMMENT 'Descripcion del resultado de aprendizaje',
  PRIMARY KEY (`id`),
  UNIQUE KEY `resultado_aprendizaje_idx` (`codigo_resultado`,`id_competencia`),
  KEY `fk_comp_reap` (`id_competencia`),
  CONSTRAINT `fk_comp_reap` FOREIGN KEY (`id_competencia`) REFERENCES `competencia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resultado_aprendizaje`
--

LOCK TABLES `resultado_aprendizaje` WRITE;
/*!40000 ALTER TABLE `resultado_aprendizaje` DISABLE KEYS */;
/*!40000 ALTER TABLE `resultado_aprendizaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resultados_vistos`
--

DROP TABLE IF EXISTS `resultados_vistos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `resultados_vistos` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id de resultados vistos',
  `id_resultado_aprendizaje` int(11) NOT NULL COMMENT 'id, resultado de aprendizaje',
  `id_ficha_has_trimestre` int(11) NOT NULL COMMENT 'id de ficha con trimestre',
  PRIMARY KEY (`id`),
  UNIQUE KEY `resultados_vistos_idx` (`id_resultado_aprendizaje`,`id_ficha_has_trimestre`),
  KEY `fk_fitr_revi` (`id_ficha_has_trimestre`),
  CONSTRAINT `fk_fitr_revi` FOREIGN KEY (`id_ficha_has_trimestre`) REFERENCES `ficha_has_trimestre` (`id`),
  CONSTRAINT `fk_reap_revi` FOREIGN KEY (`id_resultado_aprendizaje`) REFERENCES `resultado_aprendizaje` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resultados_vistos`
--

LOCK TABLES `resultados_vistos` WRITE;
/*!40000 ALTER TABLE `resultados_vistos` DISABLE KEYS */;
/*!40000 ALTER TABLE `resultados_vistos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id del rol.',
  `nombre_rol` varchar(40) NOT NULL COMMENT 'Nombre del rol ejem: Instructror, lider',
  `descripcion` varchar(40) NOT NULL COMMENT 'Descripcion del rol, su nivel y sus tareas',
  `estado` varchar(40) NOT NULL COMMENT 'Estado del rol, ejem: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_rol` (`nombre_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sede`
--

DROP TABLE IF EXISTS `sede`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sede` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id de la sede',
  `nombre_sede` varchar(50) NOT NULL COMMENT 'Nombre de la sede',
  `direccion` varchar(400) NOT NULL COMMENT 'Direccion de la sede',
  `estado` varchar(40) NOT NULL COMMENT 'Estado de actividad de la sede, ejemp: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_sede` (`nombre_sede`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sede`
--

LOCK TABLES `sede` WRITE;
/*!40000 ALTER TABLE `sede` DISABLE KEYS */;
/*!40000 ALTER TABLE `sede` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servidor_correo_electronico`
--

DROP TABLE IF EXISTS `servidor_correo_electronico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `servidor_correo_electronico` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id de configuracion de correo electronico',
  `correo` varchar(40) NOT NULL COMMENT 'correo electronico desde el cual se envia',
  `password` varchar(300) NOT NULL COMMENT 'contraseña del correo',
  `smtp_host` varchar(40) NOT NULL,
  `smtp_port` int(10) NOT NULL,
  `smtop_start_tls_enable` int(10) NOT NULL,
  `smtp_authentication` int(10) NOT NULL,
  `asunto_mensaje` varchar(100) NOT NULL COMMENT 'asunto del mensaje de recuperacion',
  `mensaje` varchar(1000) NOT NULL COMMENT 'mensaje que se envia cuando se manda el correo de recuperacion',
  PRIMARY KEY (`id`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servidor_correo_electronico`
--

LOCK TABLES `servidor_correo_electronico` WRITE;
/*!40000 ALTER TABLE `servidor_correo_electronico` DISABLE KEYS */;
/*!40000 ALTER TABLE `servidor_correo_electronico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_ambiente`
--

DROP TABLE IF EXISTS `tipo_ambiente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_ambiente` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id del Tipo ambiente',
  `tipo` varchar(40) NOT NULL COMMENT 'Tipo de ambiente dependiendo de clases que se dicten en este, ejemp: Matematicas, ingles, Tecnica',
  `descripcion` varchar(100) NOT NULL COMMENT 'Descripcion del ambiente, ejemp: Capacitado para dar clases a adsi,tps',
  `estado` varchar(40) NOT NULL COMMENT 'Estado del tipo ambiente, ejemp: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tipo` (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_ambiente`
--

LOCK TABLES `tipo_ambiente` WRITE;
/*!40000 ALTER TABLE `tipo_ambiente` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_ambiente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_documento`
--

DROP TABLE IF EXISTS `tipo_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_documento` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id del tipo de documento',
  `sigla` varchar(10) NOT NULL COMMENT 'Sigla del documento, ejem: CC, TI, CE',
  `nombre_documento` varchar(100) NOT NULL COMMENT 'Nombre del documento, ejem: Cedula de ciudadania, tarjeta identidad',
  `estado` varchar(40) NOT NULL COMMENT 'Estado del tipo de documento, (activo, inactivo)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sigla` (`sigla`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_documento`
--

LOCK TABLES `tipo_documento` WRITE;
/*!40000 ALTER TABLE `tipo_documento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trimestre`
--

DROP TABLE IF EXISTS `trimestre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `trimestre` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id trimestre',
  `nombre_trimestre` varchar(40) NOT NULL COMMENT 'Nombre trimestre, ejem: trimestre 3',
  `id_jornada` int(11) NOT NULL COMMENT 'id de la jornada',
  `id_nivel_formacion` int(11) NOT NULL COMMENT 'id del nivel de formacion',
  PRIMARY KEY (`id`),
  UNIQUE KEY `trimestre_idx` (`nombre_trimestre`,`id_jornada`,`id_nivel_formacion`),
  KEY `fk_nifo_trim` (`id_nivel_formacion`),
  KEY `fk_jorn_trim` (`id_jornada`),
  CONSTRAINT `fk_jorn_trim` FOREIGN KEY (`id_jornada`) REFERENCES `jornada` (`id`),
  CONSTRAINT `fk_nifo_trim` FOREIGN KEY (`id_nivel_formacion`) REFERENCES `nivel_formacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trimestre`
--

LOCK TABLES `trimestre` WRITE;
/*!40000 ALTER TABLE `trimestre` DISABLE KEYS */;
/*!40000 ALTER TABLE `trimestre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trimestre_vigente`
--

DROP TABLE IF EXISTS `trimestre_vigente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `trimestre_vigente` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID trimestre vigente',
  `anio` date NOT NULL COMMENT 'Anio del trimestre',
  `trimestre_programado` int(11) NOT NULL COMMENT 'numero del trimestre en el año, ejemp: trimestre 4(oct-dic)',
  `fecha_inicio` date NOT NULL COMMENT 'Fecha de inicio del trimestre',
  `fecha_fin` date NOT NULL COMMENT 'Fecha de fin del trimestre',
  `estado` varchar(40) NOT NULL COMMENT 'Estado del trimestre, ejemp: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `trimestre_vigente_idx` (`anio`,`trimestre_programado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trimestre_vigente`
--

LOCK TABLES `trimestre_vigente` WRITE;
/*!40000 ALTER TABLE `trimestre_vigente` DISABLE KEYS */;
/*!40000 ALTER TABLE `trimestre_vigente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id del usuario',
  `id_tipo_documento` int(11) NOT NULL COMMENT 'Tipo de documento, ejem: CC, TI, CE',
  `numero_documento` varchar(50) NOT NULL COMMENT 'Numero del documento del usuario',
  `correo` varchar(100) NOT NULL COMMENT 'Correo del usuario',
  `password` varchar(255) NOT NULL COMMENT 'Contraseña del usuario',
  `fecha_terminacion` date NOT NULL COMMENT 'fecha de terminacion de contrato',
  `url_foto` varchar(255) DEFAULT NULL COMMENT 'URL de la Foto del usuario',
  `primer_nombre` varchar(50) NOT NULL COMMENT 'Primer nombre del Usuario',
  `segundo_nombre` varchar(50) DEFAULT NULL COMMENT 'Segundo nombre del usuario(si tiene)',
  `primer_apellido` varchar(50) NOT NULL COMMENT 'Primer apellido del usuario',
  `segundo_apellido` varchar(50) DEFAULT NULL COMMENT 'Segundo apellido del usuario(Si tiene)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `correo` (`correo`),
  UNIQUE KEY `documento_idx` (`id_tipo_documento`,`numero_documento`),
  CONSTRAINT `fk_tido_clie` FOREIGN KEY (`id_tipo_documento`) REFERENCES `tipo_documento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_has_rol`
--

DROP TABLE IF EXISTS `usuario_has_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario_has_rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id de la union many to many entre rol y usuario',
  `id_rol` int(11) NOT NULL COMMENT 'Id del rol',
  `id_usuario` int(11) NOT NULL COMMENT 'Id del usuario',
  `estado` varchar(40) NOT NULL COMMENT 'estado ejem: activo, inactivo',
  `fecha_terminacion` date NOT NULL COMMENT 'terminacion de contrato del usuario',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cliente_has_rol_idx` (`id_rol`,`id_usuario`),
  KEY `fk_clie_chr` (`id_usuario`),
  CONSTRAINT `fk_clie_chr` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_rol_clie` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_has_rol`
--

LOCK TABLES `usuario_has_rol` WRITE;
/*!40000 ALTER TABLE `usuario_has_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_has_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `version_horario`
--

DROP TABLE IF EXISTS `version_horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `version_horario` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id de version del horario',
  `numero_version` varchar(40) NOT NULL COMMENT 'Numero de version en la que va el horario',
  `id_trimestre_vigente` int(11) NOT NULL COMMENT 'Id del trimestre del horario actual',
  `estado` varchar(40) NOT NULL COMMENT 'Estado de la version del horario, ejemp: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `version_horario_idx` (`numero_version`,`id_trimestre_vigente`),
  KEY `fk_trvi_veho` (`id_trimestre_vigente`),
  CONSTRAINT `fk_trvi_veho` FOREIGN KEY (`id_trimestre_vigente`) REFERENCES `trimestre_vigente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `version_horario`
--

LOCK TABLES `version_horario` WRITE;
/*!40000 ALTER TABLE `version_horario` DISABLE KEYS */;
/*!40000 ALTER TABLE `version_horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vinculacion`
--

DROP TABLE IF EXISTS `vinculacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vinculacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id vinculacion',
  `tipo_vinculacion` varchar(40) NOT NULL COMMENT 'Tipo de vinculacion, ejem: contratista, planta',
  `horas` int(11) NOT NULL COMMENT 'Horas que dictara clase segun su tipo de vinculacion',
  `estado` varchar(40) NOT NULL COMMENT 'Estado de la vinculacion, ejem: activo, inactivo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tipo_vinculacion` (`tipo_vinculacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vinculacion`
--

LOCK TABLES `vinculacion` WRITE;
/*!40000 ALTER TABLE `vinculacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `vinculacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-30 22:26:36
