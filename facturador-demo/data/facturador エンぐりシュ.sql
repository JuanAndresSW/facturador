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
-- Table structure for table `user_avatar`
--

DROP TABLE IF EXISTS `user_avatar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_avatar` (
  `id_user_avatar` int unsigned NOT NULL AUTO_INCREMENT,
  `usv_string` mediumtext NOT NULL,
  `id_user` int unsigned NOT NULL,
  PRIMARY KEY (`id_user_avatar`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  CONSTRAINT `id_user_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_avatar`
--

LOCK TABLES `user_avatar` WRITE;
/*!40000 ALTER TABLE `user_avatar` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_avatar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check`
--

DROP TABLE IF EXISTS `check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check` (
  `id_check` int unsigned NOT NULL AUTO_INCREMENT,
  `num_check` int unsigned NOT NULL,
  `id_point_of_sale` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `bank` varchar(20) NOT NULL,
  `crossed` bit(1) NOT NULL,
  `series` varchar(1) NOT NULL,
  PRIMARY KEY (`id_check`),
  KEY `id_point_of_sale_check_fk_idx` (`id_point_of_sale`),
  KEY `date_of_issue_check_fk_idx` (`date_of_issue`),
  KEY `reference_operation_check_idx` (`id_point_of_sale`,`date_of_issue`),
  CONSTRAINT `reference_operation_check` FOREIGN KEY (`id_point_of_sale`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale`, `date_of_issue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check`
--

LOCK TABLES `check` WRITE;
/*!40000 ALTER TABLE `check` DISABLE KEYS */;
/*!40000 ALTER TABLE `check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trader`
--

DROP TABLE IF EXISTS `trader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trader` (
  `id_trader` int unsigned NOT NULL AUTO_INCREMENT,
  `unique_key` varchar(15) NOT NULL,
  `category` enum('RESPONSABLE_INSCRIPTO','MONOTRIBUTISTA','SUJETO_EXENTO') NOT NULL,
  `gross_income_number` varchar(15) NOT NULL,
  `name` varchar(20) NOT NULL,
  `active` int NOT NULL,
  `passive` int NOT NULL,
  PRIMARY KEY (`id_trader`),
  UNIQUE KEY `unique_key_UNIQUE` (`unique_key`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trader`
--

LOCK TABLES `trader` WRITE;
/*!40000 ALTER TABLE `trader` DISABLE KEYS */;
/*!40000 ALTER TABLE `trader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_account`
--

DROP TABLE IF EXISTS `main_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `main_account` (
  `id_main_account` int unsigned NOT NULL AUTO_INCREMENT,
  `id_trader` int unsigned NOT NULL,
  `id_user` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  PRIMARY KEY (`id_main_account`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  UNIQUE KEY `id_trader_UNIQUE` (`id_trader`),
  KEY `id_trader_fk_idx` (`id_trader`),
  KEY `id_user_main_fk_idx` (`id_user`),
  CONSTRAINT `id_trader_main_fk` FOREIGN KEY (`id_trader`) REFERENCES `trader` (`id_trader`),
  CONSTRAINT `id_user_main_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_account`
--

LOCK TABLES `main_account` WRITE;
/*!40000 ALTER TABLE `main_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch_account`
--

DROP TABLE IF EXISTS `branch_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch_account` (
  `id_branch_account int unsigned NOT NULL AUTO_INCREMENT,
  `id_point_of_sale` int unsigned NOT NULL,
  `id_user` int unsigned NOT NULL,
  `id_main_account` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  PRIMARY KEY (`id_branch_account`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  KEY `id_point_of_sale_branch_fk_idx` (`id_point_of_sale`),
  KEY `fada_idx` (`id_main_account`),
  KEY `id_user_fk_idx` (`id_user`),
  CONSTRAINT `id_account_main_fk` FOREIGN KEY (`id_main_account`) REFERENCES `main_account` (`id_main_account`),
  CONSTRAINT `id_point_of_sale_branch_fk` FOREIGN KEY (`id_point_of_sale`) REFERENCES `point_of_sale` (`id_point_of_sale`),
  CONSTRAINT `id_user_branch_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_account`
--

LOCK TABLES `branch_account` WRITE;
/*!40000 ALTER TABLE `branch_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `branch_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `details_point_of_sale`
--

DROP TABLE IF EXISTS `details_point_of_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `details_point_of_sale` (
  `id_details_point_of_sale` int unsigned NOT NULL AUTO_INCREMENT,
  `color_preference` varchar(6) NOT NULL,                                //Es posible tener preferencia de color sin tener logo...
  `logo` mediumtext NOT NULL,
  `id_point_of_sale` int unsigned NOT NULL,
  PRIMARY KEY (`id_details_point_of_sale`),
  UNIQUE KEY `id_point_of_sale_UNIQUE` (`id_point_of_sale`),
  CONSTRAINT `id_details_point_of_sale_fk` FOREIGN KEY (`id_point_of_sale`) REFERENCES `point_of_sale` (`id_point_of_sale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `details_point_of_sale`
--

LOCK TABLES `details_point_of_sale` WRITE;
/*!40000 ALTER TABLE `details_point_of_sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `details_point_of_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `details_receipt`
--

DROP TABLE IF EXISTS `details_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `details_receipt` (
  `id_details_receipt` int unsigned NOT NULL AUTO_INCREMENT,
  `num` int NOT NULL,
  `deposit_date` date NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `kind_of_values` varchar(255) NOT NULL,
  `id_receipt_x` int NOT NULL,
  PRIMARY KEY (`id_details_receipt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `details_receipt`
--

LOCK TABLES `details_receipt` WRITE;
/*!40000 ALTER TABLE `details_receipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `details_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operation` (
  `id_point_of_sale` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `flux` enum('I','O') NOT NULL,
  `description_of_values` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id_point_of_sale`,`date_of_issue`),
  CONSTRAINT `id_point_of_sale_fk` FOREIGN KEY (`id_point_of_sale`) REFERENCES `point_of_sale` (`id_point_of_sale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `non_registered_operation`
--

DROP TABLE IF EXISTS `non_registered_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `non_registered_operation` (
  `id_non_registered_partner` int unsigned NOT NULL,
  `id_point_of_sale` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  PRIMARY KEY (`id_non_registered_partner`,`id_point_of_sale`,`date_of_issue`),
  KEY `id_non_registered_partner_fk_idx` (`id_non_registered_partner`),
  KEY `date_of_issue_non_registered_fk_idx` (`date_of_issue`),
  KEY `id_point_of_sale_non_registered_fk_idx` (`id_point_of_sale`),
  KEY `reference_operation_fk_idx` (`id_point_of_sale`,`date_of_issue`),
  CONSTRAINT `id_non_registered_partner_fk` FOREIGN KEY (`id_non_registered_partner`) REFERENCES `non_registered_partner` (`id_non_registered_partner`),
  CONSTRAINT `reference_operation_fk` FOREIGN KEY (`id_point_of_sale`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale`, `date_of_issue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `non_registered_operation`
--

LOCK TABLES `non_registered_operation` WRITE;
/*!40000 ALTER TABLE `non_registered_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `non_registered_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id_invoice` int unsigned NOT NULL AUTO_INCREMENT,
  `num_invoice` int unsigned NOT NULL,
  `id_point_of_sale` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `type` varchar(1) NOT NULL,
  `tax` varchar(2) NOT NULL,
  `vat` varchar(1) NOT NULL,
  `payment_form` varchar(255) NOT NULL,
  PRIMARY KEY (`id_invoice`),
  KEY `reference_operation_invoice_idx` (`id_point_of_sale`,`date_of_issue`),
  CONSTRAINT `reference_operation_invoice` FOREIGN KEY (`id_point_of_sale`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale`, `date_of_issue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partner`
--

DROP TABLE IF EXISTS `partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partner` (
  `id_trader` int unsigned NOT NULL,
  `id_point_of_sale` int unsigned NOT NULL,
  `id_trader_solicitado` int unsigned NOT NULL,
  `id_partner` int unsigned NOT NULL,
  `request_date` datetime NOT NULL,
  `use_count` int NOT NULL,
  `request_state` enum('E','A','B') NOT NULL,
  PRIMARY KEY (`id_trader`,`id_point_of_sale`,`id_trader_solicitado`,`id_partner`),
  KEY `id_trader_solicitado_list_fk_idx` (`id_trader_solicitado`),
  KEY `id_point_of_sale_soliciante_list_fk_idx` (`id_point_of_sale`),
  KEY `id_partner_list_fk_idx` (`id_partner`),
  CONSTRAINT `id_trader_solicitado_list_fk` FOREIGN KEY (`id_trader_solicitado`) REFERENCES `trader` (`id_trader`),
  CONSTRAINT `id_trader_list_fk` FOREIGN KEY (`id_trader`) REFERENCES `trader` (`id_trader`),
  CONSTRAINT `id_point_of_sale_soliciante_list_fk` FOREIGN KEY (`id_point_of_sale`) REFERENCES `point_of_sale` (`id_point_of_sale`),
  CONSTRAINT `id_partner_list_fk` FOREIGN KEY (`id_partner`) REFERENCES `point_of_sale` (`id_point_of_sale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partner`
--

LOCK TABLES `partner` WRITE;
/*!40000 ALTER TABLE `partner` DISABLE KEYS */;
/*!40000 ALTER TABLE `partner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registered_operation`
--

DROP TABLE IF EXISTS `registered_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registered_operation` (
  `id_trader` int unsigned NOT NULL,
  `id_point_of_sale` int unsigned NOT NULL,
  `id_partner_trader` int unsigned NOT NULL,
  `id_partner` int unsigned NOT NULL,
  `flux` enum('I','O') NOT NULL,
  `date_of_issue` datetime NOT NULL,
  PRIMARY KEY (`id_trader`,`id_point_of_sale`,`id_partner_trader`,`id_partner`,`flux`,`date_of_issue`),
  KEY `reference_registered_operation_fk_idx` (`flux`,`date_of_issue`),
  KEY `reference_partner_fk_idx` (`id_trader`,`id_point_of_sale`,`id_partner_trader`,`id_partner`),
  CONSTRAINT `reference_registered_operation_fk` FOREIGN KEY (`flux`, `date_of_issue`) REFERENCES `operation` (`flux`, `date_of_issue`),
  CONSTRAINT `reference_partner_fk` FOREIGN KEY (`id_point_of_sale`, `id_point_of_sale`, `id_partner_trader`, `id_partner`) REFERENCES `partner` (`id_trader`, `id_point_of_sale`, `id_trader_solicitado`, `id_partner`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci KEY_BLOCK_SIZE=2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registered_operation`
--

LOCK TABLES `registered_operation` WRITE;
/*!40000 ALTER TABLE `registered_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `registered_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_order` (
  `id_purchase_order` int unsigned NOT NULL AUTO_INCREMENT,
  `num_purchase_order` int unsigned NOT NULL,
  `id_point_of_sale` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `delivery_deadline` date NOT NULL,
  `place_of_delivery` varchar(20) NOT NULL,
  `carrier` varchar(25) NOT NULL,
  `conditions` varchar(20) NOT NULL,
  PRIMARY KEY (`id_purchase_order`),
  KEY `reference_operation_purchase_order_idx` (`id_point_of_sale`,`date_of_issue`),
  CONSTRAINT `reference_operation_purchase_order` FOREIGN KEY (`id_point_of_sale`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale`, `date_of_issue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order`
--

LOCK TABLES `purchase_order` WRITE;
/*!40000 ALTER TABLE `purchase_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promissory_note`
--

DROP TABLE IF EXISTS `promissory_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promissory_note` (
  `id_promissory_note` int unsigned NOT NULL AUTO_INCREMENT,
  `num_promissory_note` int unsigned NOT NULL,
  `id_point_of_sale` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `beneficiary` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `payment_deadline` date NOT NULL,
  `protest` bit(1) NOT NULL,
  `stamped` bit(1) NOT NULL,
  PRIMARY KEY (`id_promissory_note`),
  KEY `reference_operation_promissory_note_idx` (`id_punto_de:venta_emisor`,`date_of_issue`),
  CONSTRAINT `reference_operation_promissory_note` FOREIGN KEY (`id_point_of_sale`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale`, `date_of_issue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promissory_note`
--

LOCK TABLES `promissory_note` WRITE;
/*!40000 ALTER TABLE `promissory_note` DISABLE KEYS */;
/*!40000 ALTER TABLE `promissory_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id_products` int unsigned NOT NULL AUTO_INCREMENT,
  `quantity` int unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `id_point_of_sale` int unsigned NOT NULL,
  PRIMARY KEY (`id_products`),
  KEY `id_point_of_sale_products_fk_idx` (`id_point_of_sale`),
  CONSTRAINT `id_point_of_sale_products_fk` FOREIGN KEY (`id_point_of_sale`) REFERENCES `point_of_sale` (`id_point_of_sale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point_of_sale`
--

DROP TABLE IF EXISTS `point_of_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_of_sale` (
  `id_point_of_sale` int unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(50) NOT NULL,
  `email` varchar(128) NOT NULL,
  `name` varchar(20) NOT NULL,
  `id_trader` int unsigned NOT NULL,
  PRIMARY KEY (`id_point_of_sale`),
  KEY `id_trader_puntoventa_fk_idx` (`id_trader`),
  CONSTRAINT `id_trader_puntoventa_fk` FOREIGN KEY (`id_trader`) REFERENCES `trader` (`id_trader`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point_of_sale`
--

LOCK TABLES `point_of_sale` WRITE;
/*!40000 ALTER TABLE `point_of_sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `point_of_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt` (
  `id_receipt` int unsigned NOT NULL AUTO_INCREMENT,
  `num_receipt` int unsigned NOT NULL,
  `id_point_of_sale` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `amount` int NOT NULL,
  `payment_deadline`
  `address` varchar(40) NOT NULL,
  `payer` varchar(40) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id_receipt`),
  KEY `reference_operation_receipt_idx` (`id_point_of_sale`,`date_of_issue`),
  CONSTRAINT `reference_operation_receipt` FOREIGN KEY (`id_point_of_sale`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale`, `date_of_issue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt_x`
--

DROP TABLE IF EXISTS `receipt_x`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt_x` (
  `id_receipt_x` int unsigned NOT NULL AUTO_INCREMENT,
  `num_receipt_x` int unsigned NOT NULL,
  `id_point_of_sale` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `cash` decimal(10,2) NOT NULL,
  `documents` decimal(10,2) NOT NULL,
  `check` decimal(10,2) NOT NULL,
  `payment_time` time NOT NULL,
  `payer` varchar(40) NOT NULL,
  `payer_address` varchar(20) NOT NULL,
  PRIMARY KEY (`id_receipt_x`),
  KEY `reference_operation_receipt_x_idx` (`id_point_of_sale`,`date_of_issue`),
  CONSTRAINT `reference_operation_receipt_x` FOREIGN KEY (`id_point_of_sale`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale`, `date_of_issue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt_x`
--

LOCK TABLES `receipt_x` WRITE;
/*!40000 ALTER TABLE `receipt_x` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt_x` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remittance`
--

DROP TABLE IF EXISTS `remittance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `remittance` (
  `id_remittance` int unsigned NOT NULL AUTO_INCREMENT,
  `num_remittance` int unsigned NOT NULL,
  `id_point_of_sale` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  PRIMARY KEY (`id_remittance`),
  KEY `reference_operation_remittance_idx` (`id_point_of_sale`,`date_of_issue`),
  CONSTRAINT `reference_operation_remittance` FOREIGN KEY (`id_point_of_sale`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale`, `date_of_issue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remittance`
--

LOCK TABLES `remittance` WRITE;
/*!40000 ALTER TABLE `remittance` DISABLE KEYS */;
/*!40000 ALTER TABLE `remittance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `non_registered_partner`
--

DROP TABLE IF EXISTS `non_registered_partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `non_registered_partner` (
  `id_non_registered_partner` int unsigned NOT NULL AUTO_INCREMENT,
  `unique_key` varchar(15) NOT NULL,
  `name_point_of_sale` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `locality` varchar(20) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `category` enum('RESPONSABLE_INSCRIPTO','MONOTRIBUTISTA','SUJETO_EXENTO') NOT NULL,
  `use_count` int unsigned NOT NULL,
  PRIMARY KEY (`id_non_registered_partner`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `non_registered_partner`
--

LOCK TABLES `non_registered_partner` WRITE;
/*!40000 ALTER TABLE `non_registered_partner` DISABLE KEYS */;
/*!40000 ALTER TABLE `non_registered_partner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_user` int unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
  UNIQUE KEY `UK_m2dvbwfge291euvmk6vkkocao` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
