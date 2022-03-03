-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: facturador_mas_mas
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `avatar_usuario`
--

DROP TABLE IF EXISTS `avatar_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avatar_usuario` (
  `id_avatar` int unsigned NOT NULL AUTO_INCREMENT,
  `avatar` mediumtext NOT NULL,
  `id_usuario` int unsigned NOT NULL,
  PRIMARY KEY (`id_avatar`),
  UNIQUE KEY `id_usuario_UNIQUE` (`id_usuario`),
  CONSTRAINT `id_usuario_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avatar_usuario`
--

LOCK TABLES `avatar_usuario` WRITE;
/*!40000 ALTER TABLE `avatar_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `avatar_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cheque`
--

DROP TABLE IF EXISTS `cheque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cheque` (
  `id_cheque` int unsigned NOT NULL AUTO_INCREMENT,
  `num_cheque` int unsigned NOT NULL,
  `id_punto_de_venta_emisor` int unsigned NOT NULL,
  `fecha_emision` datetime NOT NULL,
  `cantidad` decimal(10,2) NOT NULL,
  `banco` varchar(20) NOT NULL,
  `cruzado` bit(1) NOT NULL,
  `serie` varchar(1) NOT NULL,
  PRIMARY KEY (`id_cheque`),
  KEY `id_punto_de_venta_emisor_cheque_fk_idx` (`id_punto_de_venta_emisor`),
  KEY `fecha_creacion_cheque_fk_idx` (`fecha_emision`),
  KEY `reference_operacion_cheque_idx` (`id_punto_de_venta_emisor`,`fecha_emision`),
  CONSTRAINT `reference_operacion_cheque` FOREIGN KEY (`id_punto_de_venta_emisor`, `fecha_emision`) REFERENCES `operacion` (`id_punto_de_venta_emisor`, `fecha_creacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cheque`
--

LOCK TABLES `cheque` WRITE;
/*!40000 ALTER TABLE `cheque` DISABLE KEYS */;
/*!40000 ALTER TABLE `cheque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comerciante`
--

DROP TABLE IF EXISTS `comerciante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comerciante` (
  `id_comerciante` int unsigned NOT NULL AUTO_INCREMENT,
  `clave_unica` varchar(15) NOT NULL,
  `iva` enum('RESPONSABLE_INSCRIPTO','MONOTRIBUTISTA','SUJETO_EXENTO') NOT NULL,
  `ingresos_brutos` varchar(15) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `activo` int NOT NULL,
  `pasivo` int NOT NULL,
  PRIMARY KEY (`id_comerciante`),
  UNIQUE KEY `clave_unica_UNIQUE` (`clave_unica`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comerciante`
--

LOCK TABLES `comerciante` WRITE;
/*!40000 ALTER TABLE `comerciante` DISABLE KEYS */;
/*!40000 ALTER TABLE `comerciante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_principal`
--

DROP TABLE IF EXISTS `cuenta_principal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta_principal` (
  `id_cuenta_principal` int unsigned NOT NULL AUTO_INCREMENT,
  `id_comerciante` int unsigned NOT NULL,
  `id_usuario` int unsigned NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  PRIMARY KEY (`id_cuenta_principal`),
  UNIQUE KEY `id_usuario_UNIQUE` (`id_usuario`),
  UNIQUE KEY `id_comerciante_UNIQUE` (`id_comerciante`),
  KEY `id_comerciante_fk_idx` (`id_comerciante`),
  KEY `id_usuario_main_fk_idx` (`id_usuario`),
  CONSTRAINT `id_comerciante_main_fk` FOREIGN KEY (`id_comerciante`) REFERENCES `comerciante` (`id_comerciante`),
  CONSTRAINT `id_usuario_main_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_principal`
--

LOCK TABLES `cuenta_principal` WRITE;
/*!40000 ALTER TABLE `cuenta_principal` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_principal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_sucursal`
--

DROP TABLE IF EXISTS `cuenta_sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta_sucursal` (
  `id_cuenta_sucursal int unsigned NOT NULL AUTO_INCREMENT,
  `id_punto_de_venta` int unsigned NOT NULL,
  `id_usuario` int unsigned NOT NULL,
  `id_cuenta_principal` int unsigned NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  PRIMARY KEY (`id_cuenta_sucursal`),
  UNIQUE KEY `id_usuario_UNIQUE` (`id_usuario`),
  KEY `id_punto_de_venta_secondary_fk_idx` (`id_punto_de_venta`),
  KEY `fada_idx` (`id_cuenta_principal`),
  KEY `id_usuario_fk_idx` (`id_usuario`),
  CONSTRAINT `id_cuenta_main_fk` FOREIGN KEY (`id_cuenta_principal`) REFERENCES `cuenta_principal` (`id_cuenta_principal`),
  CONSTRAINT `id_punto_de_venta_secondary_fk` FOREIGN KEY (`id_punto_de_venta`) REFERENCES `punto_de_venta` (`id_punto_de_venta`),
  CONSTRAINT `id_usuario_secondary_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_sucursal`
--

LOCK TABLES `cuenta_sucursal` WRITE;
/*!40000 ALTER TABLE `cuenta_sucursal` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalles_punto_de_venta`
--

DROP TABLE IF EXISTS `detalles_punto_de_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalles_punto_de_venta` (
  `id_detalles_punto_de_venta` int unsigned NOT NULL AUTO_INCREMENT,
  `preferencia_color` varchar(6) NOT NULL,                                //Es posible tener preferencia de color sin tener logo...
  `logo` mediumtext NOT NULL,
  `id_punto_de_venta` int unsigned NOT NULL,
  PRIMARY KEY (`id_detalles_punto_de_venta`),
  UNIQUE KEY `id_punto_de_venta_UNIQUE` (`id_punto_de_venta`),
  CONSTRAINT `id_detalles_punto_de_venta_fk` FOREIGN KEY (`id_punto_de_venta`) REFERENCES `punto_de_venta` (`id_punto_de_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles_punto_de_venta`
--

LOCK TABLES `detalles_punto_de_venta` WRITE;
/*!40000 ALTER TABLE `detalles_punto_de_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalles_punto_de_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalles_recibo`
--

DROP TABLE IF EXISTS `detalles_recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalles_recibo` (
  `id_detalles_recibo` int unsigned NOT NULL AUTO_INCREMENT,
  `num` int NOT NULL,
  `fecha_deposito` date NOT NULL,
  `importe` decimal(10,2) NOT NULL,
  `tipo_valores` varchar(255) NOT NULL,
  `id_recibo_x` int NOT NULL,
  PRIMARY KEY (`id_detalles_recibo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles_recibo`
--

LOCK TABLES `detalles_recibo` WRITE;
/*!40000 ALTER TABLE `detalles_recibo` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalles_recibo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operacion`
--

DROP TABLE IF EXISTS `operacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operacion` (
  `id_punto_de_venta_emisor` int unsigned NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `flujo` enum('I','O') NOT NULL,
  `descripcion` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id_punto_de_venta_emisor`,`fecha_creacion`),
  CONSTRAINT `id_punto_de_venta_emisor_fk` FOREIGN KEY (`id_punto_de_venta_emisor`) REFERENCES `punto_de_venta` (`id_punto_de_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacion`
--

LOCK TABLES `operacion` WRITE;
/*!40000 ALTER TABLE `operacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `operacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operacion_no_registrada`
--

DROP TABLE IF EXISTS `operacion_no_registrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operacion_no_registrada` (
  `id_socio_inv_receptor` int unsigned NOT NULL,
  `punto_de_venta_emisor_doc_inv` int unsigned NOT NULL,
  `fecha_creacion_doc_inv` datetime NOT NULL,
  PRIMARY KEY (`id_socio_inv_receptor`,`punto_de_venta_emisor_doc_inv`,`fecha_creacion_doc_inv`),
  KEY `id_socio_inventado_fk_idx` (`id_socio_inv_receptor`),
  KEY `fecha_creacion_inv_fk_idx` (`fecha_creacion_doc_inv`),
  KEY `id_punto_de_venta_emisor_inv_fk_idx` (`punto_de_venta_emisor_doc_inv`),
  KEY `reference_operacion_fk_idx` (`punto_de_venta_emisor_doc_inv`,`fecha_creacion_doc_inv`),
  CONSTRAINT `id_socio_inv_fk` FOREIGN KEY (`id_socio_inv_receptor`) REFERENCES `socio_inventado` (`id_socio_inventado`),
  CONSTRAINT `reference_operacion_fk` FOREIGN KEY (`punto_de_venta_emisor_doc_inv`, `fecha_creacion_doc_inv`) REFERENCES `operacion` (`id_punto_de_venta_emisor`, `fecha_creacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacion_no_registrada`
--

LOCK TABLES `operacion_no_registrada` WRITE;
/*!40000 ALTER TABLE `operacion_no_registrada` DISABLE KEYS */;
/*!40000 ALTER TABLE `operacion_no_registrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `id_factura` int unsigned NOT NULL AUTO_INCREMENT,
  `num_factura` int unsigned NOT NULL,
  `id_punto_de_venta_emisor` int unsigned NOT NULL,
  `fecha_emision` datetime NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `impuesto` varchar(2) NOT NULL,
  `iva` varchar(1) NOT NULL,
  `forma_pago` varchar(255) NOT NULL,
  PRIMARY KEY (`id_factura`),
  KEY `reference_operacion_factura_idx` (`id_punto_de_venta_emisor`,`fecha_emision`),
  CONSTRAINT `reference_operacion_factura` FOREIGN KEY (`id_punto_de_venta_emisor`, `fecha_emision`) REFERENCES `operacion` (`id_punto_de_venta_emisor`, `fecha_creacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socio`
--

DROP TABLE IF EXISTS `socio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socio` (
  `id_comerciante_solicitante` int unsigned NOT NULL,
  `id_punto_de_venta_solicitante` int unsigned NOT NULL,
  `id_comerciante_solicitado` int unsigned NOT NULL,
  `id_punto_de_venta_solicitado` int unsigned NOT NULL,
  `fecha_solicitud` datetime NOT NULL,
  `num_usos` int NOT NULL,
  `estado_solicitud` enum('E','A','B') NOT NULL,
  PRIMARY KEY (`id_comerciante_solicitante`,`id_punto_de_venta_solicitante`,`id_comerciante_solicitado`,`id_punto_de_venta_solicitado`),
  KEY `id_comerciante_solicitado_list_fk_idx` (`id_comerciante_solicitado`),
  KEY `id_punto_de_venta_soliciante_list_fk_idx` (`id_punto_de_venta_solicitante`),
  KEY `id_punto_de_venta_solicitado_list_fk_idx` (`id_punto_de_venta_solicitado`),
  CONSTRAINT `id_comerciante_solicitado_list_fk` FOREIGN KEY (`id_comerciante_solicitado`) REFERENCES `comerciante` (`id_comerciante`),
  CONSTRAINT `id_comerciante_solicitante_list_fk` FOREIGN KEY (`id_comerciante_solicitante`) REFERENCES `comerciante` (`id_comerciante`),
  CONSTRAINT `id_punto_de_venta_soliciante_list_fk` FOREIGN KEY (`id_punto_de_venta_solicitante`) REFERENCES `punto_de_venta` (`id_punto_de_venta`),
  CONSTRAINT `id_punto_de_venta_solicitado_list_fk` FOREIGN KEY (`id_punto_de_venta_solicitado`) REFERENCES `punto_de_venta` (`id_punto_de_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socio`
--

LOCK TABLES `socio` WRITE;
/*!40000 ALTER TABLE `socio` DISABLE KEYS */;
/*!40000 ALTER TABLE `socio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operacion_registrada`
--

DROP TABLE IF EXISTS `operacion_registrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operacion_registrada` (
  `id_comerciante_dueño_uno` int unsigned NOT NULL,
  `id_punto_de_venta_socio_uno` int unsigned NOT NULL,
  `id_comerciante_dueño_dos` int unsigned NOT NULL,
  `id_punto_de_venta_socio_dos` int unsigned NOT NULL,
  `id_punto_de_venta_emisor_doc` int unsigned NOT NULL,
  `fecha_creacion_doc` datetime NOT NULL,
  PRIMARY KEY (`id_comerciante_dueño_uno`,`id_punto_de_venta_socio_uno`,`id_comerciante_dueño_dos`,`id_punto_de_venta_socio_dos`,`id_punto_de_venta_emisor_doc`,`fecha_creacion_doc`),
  KEY `reference_operacion_registrada_fk_idx` (`id_punto_de_venta_emisor_doc`,`fecha_creacion_doc`),
  KEY `reference_socio_fk_idx` (`id_comerciante_dueño_uno`,`id_punto_de_venta_socio_uno`,`id_comerciante_dueño_dos`,`id_punto_de_venta_socio_dos`),
  CONSTRAINT `reference_operacion_registrada_fk` FOREIGN KEY (`id_punto_de_venta_emisor_doc`, `fecha_creacion_doc`) REFERENCES `operacion` (`id_punto_de_venta_emisor`, `fecha_creacion`),
  CONSTRAINT `reference_socio_fk` FOREIGN KEY (`id_comerciante_dueño_uno`, `id_punto_de_venta_socio_uno`, `id_comerciante_dueño_dos`, `id_punto_de_venta_socio_dos`) REFERENCES `socio` (`id_comerciante_solicitante`, `id_punto_de_venta_solicitante`, `id_comerciante_solicitado`, `id_punto_de_venta_solicitado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci KEY_BLOCK_SIZE=2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacion_registrada`
--

LOCK TABLES `operacion_registrada` WRITE;
/*!40000 ALTER TABLE `operacion_registrada` DISABLE KEYS */;
/*!40000 ALTER TABLE `operacion_registrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden_de_compra`
--

DROP TABLE IF EXISTS `orden_de_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden_de_compra` (
  `id_orden_de_compra` int unsigned NOT NULL AUTO_INCREMENT,
  `num_orden_de_compra` int unsigned NOT NULL,
  `id_punto_de_venta_emisor` int unsigned NOT NULL,
  `fecha_emision` datetime NOT NULL,
  `fecha_limite` date NOT NULL,
  `lugar_entrega` varchar(20) NOT NULL,
  `nombre_transportista` varchar(25) NOT NULL,
  `condiciones` varchar(20) NOT NULL,
  PRIMARY KEY (`id_orden_de_compra`),
  KEY `reference_operacion_orden_de_compra_idx` (`id_punto_de_venta_emisor`,`fecha_emision`),
  CONSTRAINT `reference_operacion_orden_de_compra` FOREIGN KEY (`id_punto_de_venta_emisor`, `fecha_emision`) REFERENCES `operacion` (`id_punto_de_venta_emisor`, `fecha_creacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_de_compra`
--

LOCK TABLES `orden_de_compra` WRITE;
/*!40000 ALTER TABLE `orden_de_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `orden_de_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagare`
--

DROP TABLE IF EXISTS `pagare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagare` (
  `id_pagare` int unsigned NOT NULL AUTO_INCREMENT,
  `num_pagare` int unsigned NOT NULL,
  `id_punto_de_venta_emisor` int unsigned NOT NULL,
  `fecha_emision` datetime NOT NULL,
  `cantidad` decimal(10,2) NOT NULL,
  `beneficiario` varchar(255) NOT NULL,
  `contacto` varchar(255) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `protesto` bit(1) NOT NULL,
  `sellado` bit(1) NOT NULL,
  PRIMARY KEY (`id_pagare`),
  KEY `reference_operacion_pagare_idx` (`id_punto_de:venta_emisor`,`fecha_emision`),
  CONSTRAINT `reference_operacion_pagare` FOREIGN KEY (`id_punto_de_venta_emisor`, `fecha_emision`) REFERENCES `operacion` (`id_punto_de_venta_emisor`, `fecha_creacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagare`
--

LOCK TABLES `pagare` WRITE;
/*!40000 ALTER TABLE `pagare` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id_productos` int unsigned NOT NULL AUTO_INCREMENT,
  `cantidad` int unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `id_punto_de_venta_dueño` int unsigned NOT NULL,
  PRIMARY KEY (`id_productos`),
  KEY `id_punto_de_venta_productos_fk_idx` (`id_punto_de_venta_dueño`),
  CONSTRAINT `id_punto_de_venta_productos_fk` FOREIGN KEY (`id_punto_de_venta_dueño`) REFERENCES `punto_de_venta` (`id_punto_de_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `punto_de_venta`
--

DROP TABLE IF EXISTS `punto_de_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `punto_de_venta` (
  `id_punto_de_venta` int unsigned NOT NULL AUTO_INCREMENT,
  `direccion` varchar(50) NOT NULL,
  `email` varchar(128) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `id_comerciante` int unsigned NOT NULL,
  PRIMARY KEY (`id_punto_de_venta`),
  KEY `id_comerciante_puntoventa_fk_idx` (`id_comerciante`),
  CONSTRAINT `id_comerciante_puntoventa_fk` FOREIGN KEY (`id_comerciante`) REFERENCES `comerciante` (`id_comerciante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `punto_de_venta`
--

LOCK TABLES `punto_de_venta` WRITE;
/*!40000 ALTER TABLE `punto_de_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `punto_de_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo`
--

DROP TABLE IF EXISTS `recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recibo` (
  `id_recibo` int unsigned NOT NULL AUTO_INCREMENT,
  `num_recibo` int unsigned NOT NULL,
  `id_punto_de_venta_emisor` int unsigned NOT NULL,
  `fecha_emision` datetime NOT NULL,
  `cantidad` int NOT NULL,
  `domicilio` varchar(40) NOT NULL,
  `pagador` varchar(40) NOT NULL,
  `tipo` varchar(10) NOT NULL,
  PRIMARY KEY (`id_recibo`),
  KEY `reference_operacion_recibo_idx` (`id_punto_de_enta_emisor`,`fecha_emision`),
  CONSTRAINT `reference_operacion_recibo` FOREIGN KEY (`id_punto_de_venta_emisor`, `fecha_emision`) REFERENCES `operacion` (`id_punto_de_venta_emisor`, `fecha_creacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo`
--

LOCK TABLES `recibo` WRITE;
/*!40000 ALTER TABLE `recibo` DISABLE KEYS */;
/*!40000 ALTER TABLE `recibo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo_x`
--

DROP TABLE IF EXISTS `recibo_x`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recibo_x` (
  `id_recibo_x` int unsigned NOT NULL AUTO_INCREMENT,
  `num_recibo_x` int unsigned NOT NULL,
  `id_punto_de_venta_emisor` int unsigned NOT NULL,
  `fecha_emision` datetime NOT NULL,
  `efectivo` decimal(10,2) NOT NULL,
  `documentos` decimal(10,2) NOT NULL,
  `cheque` decimal(10,2) NOT NULL,
  `horario` time NOT NULL,
  `pagador` varchar(40) NOT NULL,
  `domicilio_pago` varchar(20) NOT NULL,
  PRIMARY KEY (`id_recibo_x`),
  KEY `reference_operacion_recibo_x_idx` (`id_punto_de_venta_emisor`,`fecha_emision`),
  CONSTRAINT `reference_operacion_recibo_x` FOREIGN KEY (`id_punto_de_venta_emisor`, `fecha_emision`) REFERENCES `operacion` (`id_punto_de_venta_emisor`, `fecha_creacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo_x`
--

LOCK TABLES `recibo_x` WRITE;
/*!40000 ALTER TABLE `recibo_x` DISABLE KEYS */;
/*!40000 ALTER TABLE `recibo_x` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remito`
--

DROP TABLE IF EXISTS `remito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `remito` (
  `id_remito` int unsigned NOT NULL AUTO_INCREMENT,
  `num_remito` int unsigned NOT NULL,
  `id_punto_de_venta_emisor` int unsigned NOT NULL,
  `fecha_emision` datetime NOT NULL,
  PRIMARY KEY (`id_remito`),
  KEY `reference_operacion_remito_idx` (`id_punto_de_venta_emisor`,`fecha_emision`),
  CONSTRAINT `reference_operacion_remito` FOREIGN KEY (`id_punto_de_venta_emisor`, `fecha_emision`) REFERENCES `operacion` (`id_punto_de_venta_emisor`, `fecha_creacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remito`
--

LOCK TABLES `remito` WRITE;
/*!40000 ALTER TABLE `remito` DISABLE KEYS */;
/*!40000 ALTER TABLE `remito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socio_no_registrado`
--

DROP TABLE IF EXISTS `socio_no_registrado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socio_no_registrado` (
  `id_socio_no_registrado` int unsigned NOT NULL AUTO_INCREMENT,
  `clave_unica` varchar(15) NOT NULL,
  `nombre_punto_de_venta` varchar(20) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `localidad` varchar(20) NOT NULL,
  `cp` varchar(10) NOT NULL,
  `iva` enum('RESPONSABLE_INSCRIPTO','MONOTRIBUTISTA','SUJETO_EXENTO') NOT NULL,
  `numero_usos` int unsigned NOT NULL,
  PRIMARY KEY (`id_socio_no_registrado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socio_no_registrado`
--

LOCK TABLES `socio_no_registrado` WRITE;
/*!40000 ALTER TABLE `socio_no_registrado` DISABLE KEYS */;
/*!40000 ALTER TABLE `socio_no_registrado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(128) NOT NULL,
  `contraseña` varchar(128) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
  UNIQUE KEY `UK_m2dvbwfge291euvmk6vkkocao` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-02  1:17:25
