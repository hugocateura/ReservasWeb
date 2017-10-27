CREATE DATABASE  IF NOT EXISTS `bd_reserva` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bd_reserva`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_reserva
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `elemento`
--

DROP TABLE IF EXISTS `elemento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `elemento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE big5_bin DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tipo_idx` (`tipo`),
  CONSTRAINT `tipo` FOREIGN KEY (`tipo`) REFERENCES `tipoelemento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=big5 COLLATE=big5_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elemento`
--

LOCK TABLES `elemento` WRITE;
/*!40000 ALTER TABLE `elemento` DISABLE KEYS */;
INSERT INTO `elemento` VALUES (7,'PC Box 766',1),(10,'PC 345',1),(11,'PC Note 765',1),(13,'PC Escritorio',1),(14,'PC Boxes 900',1),(25,'Samsung 17\"',2),(26,'Negro Mate 1.30x0.65',3),(27,'Lenovo 23\"',2),(34,'Pino 1.30x0.60',3);
/*!40000 ALTER TABLE `elemento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dni` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `contrasena` varchar(45) DEFAULT NULL,
  `habilitado` bit(1) DEFAULT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (11,'31222333','Hugo','Perez','1','1','','Online'),(12,'28111222','Lucia','Gonzalez','2','2','','Encargado'),(13,'06888777','Miguel','Sanchez','3','3','','Administrador'),(14,'43111222','Manuel','Rodriguez','4','4','','Administrador'),(53,'06555444','Ruben Carlos','Ramirez','6','6','','Online'),(55,'ffff','123','123','123','123','','Administrador');
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger insert_categoria before insert on personas 
  for each row 
   begin  
    if  new.categoria not in ("Administrador","Online","Encargado") then
        SIGNAL SQLSTATE '45000'   
        SET MESSAGE_TEXT = 'Cannot add or update row: categoria invalida';
      end if; 
      end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger update_categoria before update on personas 
  for each row 
   begin  
    if  new.categoria not in ("Administrador","Online","Encargado") then
        SIGNAL SQLSTATE '45000'   
        SET MESSAGE_TEXT = 'Cannot add or update row: categoria invalida';
      end if; 
      end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` int(11) DEFAULT NULL,
  `fechaHoraDesde` datetime DEFAULT NULL,
  `fechaHoraHasta` datetime DEFAULT NULL,
  `elemento` int(11) DEFAULT NULL,
  `observacion` tinytext,
  `persona` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tipo_idx` (`tipo`),
  KEY `elemento_idx` (`elemento`),
  KEY `persona_idx` (`persona`),
  CONSTRAINT `elemento` FOREIGN KEY (`elemento`) REFERENCES `elemento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `persona` FOREIGN KEY (`persona`) REFERENCES `personas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipoDeRe` FOREIGN KEY (`tipo`) REFERENCES `tipoelemento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 COMMENT='Tabla de Reservas';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (28,9,'2017-01-01 00:00:00','2017-12-31 00:00:00',34,'',13,'Anulada'),(67,2,'2017-10-26 00:00:00','2017-10-26 01:00:00',27,'',11,'Activa'),(68,2,'2017-10-27 00:00:00','2017-10-27 01:00:00',27,'',11,'Anulada'),(69,2,'2017-01-01 00:00:00','2017-01-01 02:00:00',27,'',13,'Activa'),(70,2,'2017-01-01 00:00:00','2017-01-01 02:00:00',27,'',13,'Activa'),(71,2,'2017-01-01 00:00:00','2017-01-01 00:00:00',25,'',13,'Activa'),(72,3,'2017-01-01 00:00:00','2017-01-01 00:00:00',26,'',11,'Activa'),(73,2,'2017-01-01 01:00:00','2017-01-01 00:00:00',25,'',11,'Activa'),(74,2,'2017-05-05 00:00:00','2017-05-05 01:00:00',25,'',11,'Activa'),(75,3,'2017-06-06 00:00:00','2017-06-06 01:00:00',34,'',14,'Activa'),(76,3,'2017-09-09 00:00:00','2017-09-09 03:00:00',26,'',11,'Activa'),(78,2,'2017-10-28 00:00:00','2017-10-28 05:00:00',27,'',11,'Activa'),(79,2,'2017-10-26 00:00:00','2017-10-26 05:00:00',25,'',53,'Anulada'),(80,2,'2017-11-30 00:00:00','2017-11-30 01:00:00',27,'',11,'Activa'),(81,3,'2017-10-27 00:00:00','2017-10-27 05:00:00',26,'',53,'Anulada');
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoelemento`
--

DROP TABLE IF EXISTS `tipoelemento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoelemento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `cant_max_reservas` varchar(45) DEFAULT NULL,
  `reservaEncargado` bit(1) DEFAULT NULL,
  `limiteMaxHorasReserva` int(11) DEFAULT NULL,
  `cantMaxDiasAnticipacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoelemento`
--

LOCK TABLES `tipoelemento` WRITE;
/*!40000 ALTER TABLE `tipoelemento` DISABLE KEYS */;
INSERT INTO `tipoelemento` VALUES (1,'PC de Sobremesa','12','',50,50),(2,'Monitor CRT','4','\0',50,50),(3,'Escritorio Melamina','2','\0',50,50),(9,'Monitor LCD','2','\0',50,50);
/*!40000 ALTER TABLE `tipoelemento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bd_reserva'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-26 23:14:45
