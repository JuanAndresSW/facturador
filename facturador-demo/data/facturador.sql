-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: facturador-mas
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
  `id_avatar` bigint NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) NOT NULL,
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id_avatar`),
  UNIQUE KEY `UK_9r98vdxdtt1tw76ecsrxpv2io` (`id_usuario`),
  CONSTRAINT `FKpehspugjt260x6kp1agohxuxe` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avatar_usuario`
--

LOCK TABLES `avatar_usuario` WRITE;
/*!40000 ALTER TABLE `avatar_usuario` DISABLE KEYS */;
INSERT INTO `avatar_usuario` VALUES (6,'gfdgdfgdfgreyrthtyujuytjyujkyukuykuykyjh6',6);
/*!40000 ALTER TABLE `avatar_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cheque`
--

DROP TABLE IF EXISTS `cheque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cheque` (
  `id_cheque` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` double NOT NULL,
  `banco` varchar(20) NOT NULL,
  `cruzado` bit(1) NOT NULL,
  `fecha_emision` date NOT NULL,
  `num_cheque` int NOT NULL,
  `serie` varchar(1) NOT NULL,
  PRIMARY KEY (`id_cheque`)
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
  `id_comerciante` int NOT NULL AUTO_INCREMENT,
  `clave_unica` varchar(15) NOT NULL,
  `iva` varchar(25) NOT NULL,
  `ingresos_brutos` varchar(15) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`id_comerciante`),
  UNIQUE KEY `clave_unica_UNIQUE` (`clave_unica`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comerciante`
--

LOCK TABLES `comerciante` WRITE;
/*!40000 ALTER TABLE `comerciante` DISABLE KEYS */;
INSERT INTO `comerciante` VALUES (16,'23453217245','Mono tributista','14-49.543.762-4','Jesus LC');
/*!40000 ALTER TABLE `comerciante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_principal`
--

DROP TABLE IF EXISTS `cuenta_principal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta_principal` (
  `id_cuenta_principal` bigint NOT NULL AUTO_INCREMENT,
  `id_comerciante` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id_cuenta_principal`),
  UNIQUE KEY `UK_rq9x9vmktqxah89uo0ny84jh` (`id_comerciante`),
  UNIQUE KEY `UK_drv6ehphiqu2668h3k4ca7yuc` (`id_usuario`),
  CONSTRAINT `FKrwt5bprt0aws75irf7jacqpkd` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_principal`
--

LOCK TABLES `cuenta_principal` WRITE;
/*!40000 ALTER TABLE `cuenta_principal` DISABLE KEYS */;
INSERT INTO `cuenta_principal` VALUES (6,16,6);
/*!40000 ALTER TABLE `cuenta_principal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_secundaria`
--

DROP TABLE IF EXISTS `cuenta_secundaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta_secundaria` (
  `id_cuenta_secundaria` bigint NOT NULL AUTO_INCREMENT,
  `id_punto_venta` bigint NOT NULL,
  `id_cuenta_principal` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id_cuenta_secundaria`),
  UNIQUE KEY `UK_qi2xah2g9r4v5jqg4thp014gc` (`id_usuario`),
  KEY `FK748ng7j16f46lmp9q0ln6eg2d` (`id_punto_venta`),
  KEY `FKsf3orqqhm5reimf5lrotmnr5i` (`id_cuenta_principal`),
  CONSTRAINT `FK748ng7j16f46lmp9q0ln6eg2d` FOREIGN KEY (`id_punto_venta`) REFERENCES `punto_venta` (`id_punto_venta`),
  CONSTRAINT `FKaeb4qexwjrt1gsh7m82lu1b4t` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `FKsf3orqqhm5reimf5lrotmnr5i` FOREIGN KEY (`id_cuenta_principal`) REFERENCES `cuenta_principal` (`id_cuenta_principal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_secundaria`
--

LOCK TABLES `cuenta_secundaria` WRITE;
/*!40000 ALTER TABLE `cuenta_secundaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_secundaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalles_punto_venta`
--

DROP TABLE IF EXISTS `detalles_punto_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalles_punto_venta` (
  `preferencia_color` varchar(6) NOT NULL,
  `logo` bigint NOT NULL,
  `id_punto_venta` bigint NOT NULL,
  PRIMARY KEY (`id_punto_venta`),
  CONSTRAINT `FKa9ctu1ng6fp26k8o2dlekuklr` FOREIGN KEY (`id_punto_venta`) REFERENCES `punto_venta` (`id_punto_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles_punto_venta`
--

LOCK TABLES `detalles_punto_venta` WRITE;
/*!40000 ALTER TABLE `detalles_punto_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalles_punto_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalles_recibo`
--

DROP TABLE IF EXISTS `detalles_recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalles_recibo` (
  `id_detalles_recibo` bigint NOT NULL AUTO_INCREMENT,
  `importe` double NOT NULL,
  `fecha_deposito` date NOT NULL,
  `num` int NOT NULL,
  `tipo_valores` varchar(255) NOT NULL,
  `id_recibo_x` bigint NOT NULL,
  PRIMARY KEY (`id_detalles_recibo`),
  KEY `FKrvgxq8q1p34hp2rxww59sace8` (`id_recibo_x`),
  CONSTRAINT `FKrvgxq8q1p34hp2rxww59sace8` FOREIGN KEY (`id_recibo_x`) REFERENCES `recibo_x` (`id_recibo_x`)
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
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `id_factura` bigint NOT NULL AUTO_INCREMENT,
  `flujo` varchar(1) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `fecha_emision` date NOT NULL,
  `num_factura` int NOT NULL,
  `observaciones` varchar(60) NOT NULL,
  `impuesto` varchar(2) NOT NULL,
  `iva` varchar(1) NOT NULL,
  `forma_pago` varchar(255) NOT NULL,
  PRIMARY KEY (`id_factura`)
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
-- Table structure for table `orden_compra`
--

DROP TABLE IF EXISTS `orden_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden_compra` (
  `id_orden_compra` bigint NOT NULL AUTO_INCREMENT,
  `nombre_transportista` varchar(25) NOT NULL,
  `fecha_limite` date NOT NULL,
  `lugar_entrega` varchar(20) NOT NULL,
  `flujo` varchar(1) NOT NULL,
  `fecha_emision` date NOT NULL,
  `num_orden_compra` int NOT NULL,
  `observaciones` varchar(60) NOT NULL,
  `condiciones` varchar(20) NOT NULL,
  PRIMARY KEY (`id_orden_compra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_compra`
--

LOCK TABLES `orden_compra` WRITE;
/*!40000 ALTER TABLE `orden_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `orden_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagare`
--

DROP TABLE IF EXISTS `pagare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagare` (
  `id_pagare` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` double NOT NULL,
  `beneficiario` varchar(255) NOT NULL,
  `contacto` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `fecha_emision` date NOT NULL,
  `num_pagare` int NOT NULL,
  `protesto` bit(1) NOT NULL,
  `sellado` bit(1) NOT NULL,
  PRIMARY KEY (`id_pagare`)
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
  `id_productos` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `precio` double NOT NULL,
  `id_transaccion` bigint NOT NULL,
  PRIMARY KEY (`id_productos`),
  KEY `FKro7bsmtbipam3o5vsp6ttdv8l` (`id_transaccion`),
  CONSTRAINT `FKro7bsmtbipam3o5vsp6ttdv8l` FOREIGN KEY (`id_transaccion`) REFERENCES `transaccion` (`id_transaccion`)
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
-- Table structure for table `punto_venta`
--

DROP TABLE IF EXISTS `punto_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `punto_venta` (
  `id_punto_venta` bigint NOT NULL AUTO_INCREMENT,
  `direccion` varchar(50) NOT NULL,
  `email` varchar(320) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `id_comerciante` bigint NOT NULL,
  PRIMARY KEY (`id_punto_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `punto_venta`
--

LOCK TABLES `punto_venta` WRITE;
/*!40000 ALTER TABLE `punto_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `punto_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo`
--

DROP TABLE IF EXISTS `recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recibo` (
  `id_recibo` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `descipcion` varchar(20) NOT NULL,
  `flujo` varchar(1) NOT NULL,
  `domicilio` varchar(40) NOT NULL,
  `fecha_emision` date NOT NULL,
  `num_recibo` int NOT NULL,
  `pagador` varchar(40) NOT NULL,
  `tipo` varchar(10) NOT NULL,
  PRIMARY KEY (`id_recibo`)
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
  `id_recibo_x` bigint NOT NULL AUTO_INCREMENT,
  `efectivo` double NOT NULL,
  `cheque` double NOT NULL,
  `documentos` double NOT NULL,
  `flujo` varchar(1) NOT NULL,
  `horario` datetime(6) NOT NULL,
  `fecha_emision` date NOT NULL,
  `num_recibo_x` int NOT NULL,
  `pagador` varchar(40) NOT NULL,
  `domicilio_pago` varchar(20) NOT NULL,
  PRIMARY KEY (`id_recibo_x`)
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
  `id_remito` bigint NOT NULL AUTO_INCREMENT,
  `flujo` varchar(1) NOT NULL,
  `fecha_emision` date NOT NULL,
  `num_remito` int NOT NULL,
  `observaciones` varchar(60) NOT NULL,
  PRIMARY KEY (`id_remito`)
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
-- Table structure for table `socio`
--

DROP TABLE IF EXISTS `socio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socio` (
  `id_socio` bigint NOT NULL AUTO_INCREMENT,
  `direccion` varchar(50) NOT NULL,
  `cp` varchar(255) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `cuit` varchar(15) DEFAULT NULL,
  `iva` varchar(255) NOT NULL,
  `web` varchar(320) DEFAULT NULL,
  PRIMARY KEY (`id_socio`)
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
-- Table structure for table `transaccion`
--

DROP TABLE IF EXISTS `transaccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaccion` (
  `id_transaccion` bigint NOT NULL AUTO_INCREMENT,
  `num_transaccion` int NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `id_cheque` bigint DEFAULT NULL,
  `id_factura` bigint DEFAULT NULL,
  `id_pagare` bigint DEFAULT NULL,
  `id_punto_venta` bigint NOT NULL,
  `id_orden_compra` bigint DEFAULT NULL,
  `id_recibo` bigint DEFAULT NULL,
  `id_recibo_x` bigint DEFAULT NULL,
  `id_remito` bigint DEFAULT NULL,
  `id_socio` bigint NOT NULL,
  PRIMARY KEY (`id_transaccion`),
  KEY `FKm3hs8d6ftofhf2s5sfbr1kfs0` (`id_cheque`),
  KEY `FKk2srxw3ol9fvb77gl9bml4ioi` (`id_factura`),
  KEY `FK5owlf111cb3gtehboaq0v0suc` (`id_pagare`),
  KEY `FK3vpks1icl7k7pi59q3wyn9nd9` (`id_punto_venta`),
  KEY `FKrq95ikm6ntdbnj31mo96pgoe7` (`id_orden_compra`),
  KEY `FKmgqmja6m0myomik0rimnu1sxb` (`id_recibo`),
  KEY `FK5ef7ldjxpbsri639c6vsjt3n6` (`id_recibo_x`),
  KEY `FK40jbbby6eeeke66uiedtsf605` (`id_remito`),
  KEY `FKiwaygq2rxaiahct7ncgusxec` (`id_socio`),
  CONSTRAINT `FK3vpks1icl7k7pi59q3wyn9nd9` FOREIGN KEY (`id_punto_venta`) REFERENCES `punto_venta` (`id_punto_venta`),
  CONSTRAINT `FK40jbbby6eeeke66uiedtsf605` FOREIGN KEY (`id_remito`) REFERENCES `remito` (`id_remito`),
  CONSTRAINT `FK5ef7ldjxpbsri639c6vsjt3n6` FOREIGN KEY (`id_recibo_x`) REFERENCES `recibo_x` (`id_recibo_x`),
  CONSTRAINT `FK5owlf111cb3gtehboaq0v0suc` FOREIGN KEY (`id_pagare`) REFERENCES `pagare` (`id_pagare`),
  CONSTRAINT `FKiwaygq2rxaiahct7ncgusxec` FOREIGN KEY (`id_socio`) REFERENCES `socio` (`id_socio`),
  CONSTRAINT `FKk2srxw3ol9fvb77gl9bml4ioi` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`id_factura`),
  CONSTRAINT `FKm3hs8d6ftofhf2s5sfbr1kfs0` FOREIGN KEY (`id_cheque`) REFERENCES `cheque` (`id_cheque`),
  CONSTRAINT `FKmgqmja6m0myomik0rimnu1sxb` FOREIGN KEY (`id_recibo`) REFERENCES `recibo` (`id_recibo`),
  CONSTRAINT `FKrq95ikm6ntdbnj31mo96pgoe7` FOREIGN KEY (`id_orden_compra`) REFERENCES `orden_compra` (`id_orden_compra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaccion`
--

LOCK TABLES `transaccion` WRITE;
/*!40000 ALTER TABLE `transaccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(320) NOT NULL,
  `password` varchar(128) NOT NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
  UNIQUE KEY `UK_m2dvbwfge291euvmk6vkkocao` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (6,'fadfsa@gmail.com','$argon2id$v=19$m=2048,t=2,p=1$v2i3ispXwhfpjJKyjYNOsg$/SYjgvkQ9+dbEnaWojrivc6GNv8rv2mXAlHiKpGepEU','fdda');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-10  0:52:10
