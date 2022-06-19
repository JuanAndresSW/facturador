-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: facturador_db
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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id_account` int unsigned NOT NULL AUTO_INCREMENT,
  `id_trader` int unsigned NOT NULL,
  `id_user` int unsigned NOT NULL,
  `creation_date` datetime NOT NULL,
  PRIMARY KEY (`id_account`),
  KEY `id_user_main_fk_idx` (`id_user`),
  KEY `id_trader_main_account_fk_idx` (`id_trader`),
  CONSTRAINT `id_trader_account_fk` FOREIGN KEY (`id_trader`) REFERENCES `trader` (`id_trader`),
  CONSTRAINT `id_user_account_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `id_branch` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `email` varchar(128) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `province` varchar(20) NOT NULL,
  `department` varchar(45) NOT NULL,
  `locality` varchar(45) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `street` varchar(50) NOT NULL,
  `address_number` varchar(5) NOT NULL,
  `preference_color` varchar(7) NOT NULL,
  `creation_date` date NOT NULL,
  `photo` mediumtext NOT NULL,
  `logo` mediumtext NOT NULL,
  `id_trader` int unsigned NOT NULL,
  PRIMARY KEY (`id_branch`),
  KEY `id_trader_branch_fk_idx` (`id_trader`),
  CONSTRAINT `id_trader_branch_fk` FOREIGN KEY (`id_trader`) REFERENCES `trader` (`id_trader`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
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
  `id_point_of_sale_issuing` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `bank` varchar(20) NOT NULL,
  `crossed` bit(1) NOT NULL,
  `series` varchar(1) NOT NULL,
  PRIMARY KEY (`id_check`),
  KEY `reference_operation_check_fk_idx` (`id_point_of_sale_issuing`,`date_of_issue`),
  CONSTRAINT `reference_operation_check_fk` FOREIGN KEY (`id_point_of_sale_issuing`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale_issuing`, `date_of_issue`)
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
-- Table structure for table `details_receipt`
--

DROP TABLE IF EXISTS `details_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `details_receipt` (
  `id_details_receipt` int unsigned NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,2) NOT NULL,
  `deposit_date` date NOT NULL,
  `num` int unsigned NOT NULL,
  `kind_of_value` varchar(255) NOT NULL,
  `id_receipt_x` int unsigned NOT NULL,
  PRIMARY KEY (`id_details_receipt`),
  KEY `id_receipt_x_details_fk_idx` (`id_receipt_x`),
  CONSTRAINT `id_receipt_x_details_fk` FOREIGN KEY (`id_receipt_x`) REFERENCES `receipt_x` (`id_receipt_x`)
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
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id_invoice` int unsigned NOT NULL AUTO_INCREMENT,
  `num_invoice` int unsigned NOT NULL,
  `id_point_of_sale_issuing` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `type` enum('A','B','C') NOT NULL,
  `tax` varchar(2) NOT NULL,
  `vat` varchar(1) NOT NULL,
  `payment_form` varchar(255) NOT NULL,
  PRIMARY KEY (`id_invoice`),
  KEY `reference_operation_invoice_fk_idx` (`id_point_of_sale_issuing`,`date_of_issue`),
  CONSTRAINT `reference_operation_invoice_fk` FOREIGN KEY (`id_point_of_sale_issuing`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale_issuing`, `date_of_issue`)
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
-- Table structure for table `non_registered_operation`
--

DROP TABLE IF EXISTS `non_registered_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `non_registered_operation` (
  `id_non_registered_partner_requested` int unsigned NOT NULL,
  `id_point_of_sale_requester` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  PRIMARY KEY (`id_non_registered_partner_requested`,`id_point_of_sale_requester`,`date_of_issue`),
  KEY `reference_operation_in_non_registred_fk_idx` (`id_point_of_sale_requester`,`date_of_issue`),
  CONSTRAINT `reference_operation_in_non_registered_fk` FOREIGN KEY (`id_point_of_sale_requester`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale_issuing`, `date_of_issue`)
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
-- Table structure for table `non_registered_partner`
--

DROP TABLE IF EXISTS `non_registered_partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `non_registered_partner` (
  `id_non_registered_partner` int unsigned NOT NULL AUTO_INCREMENT,
  `unique_key` varchar(15) NOT NULL,
  `address` varchar(50) NOT NULL,
  `locality` varchar(20) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `name_point_of_sale` varchar(20) NOT NULL,
  `vat_category` enum('REGISTERED_RESPONSIBLE','MONOTAX_RESPONSIBLE') NOT NULL,
  `use_account` int NOT NULL,
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
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operation` (
  `id_point_of_sale_issuing` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `flux` enum('I','O') NOT NULL,
  `description` varchar(60) NOT NULL,
  PRIMARY KEY (`id_point_of_sale_issuing`,`date_of_issue`),
  CONSTRAINT `id_operation_pos_fk` FOREIGN KEY (`id_point_of_sale_issuing`) REFERENCES `point_of_sale` (`id_point_of_sale`)
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
-- Table structure for table `partner`
--

DROP TABLE IF EXISTS `partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partner` (
  `id_trader_requester` int unsigned NOT NULL,
  `id_branch_requester` int unsigned NOT NULL,
  `id_trader_requested` int unsigned NOT NULL,
  `id_branch_requested` int unsigned NOT NULL,
  `request_date` datetime NOT NULL,
  `use_count` int NOT NULL,
  `request_state` enum('W','A','L') NOT NULL,
  PRIMARY KEY (`id_trader_requester`,`id_branch_requester`,`id_trader_requested`,`id_branch_requested`),
  KEY `id_branch_requested_fk_idx` (`id_branch_requested`),
  KEY `id_branch_requester_fk_idx` (`id_branch_requester`),
  KEY `id_trader_requested_fk` (`id_trader_requested`),
  CONSTRAINT `id_branch_requested_fk` FOREIGN KEY (`id_branch_requested`) REFERENCES `branch` (`id_branch`),
  CONSTRAINT `id_branch_requester_fk` FOREIGN KEY (`id_branch_requester`) REFERENCES `branch` (`id_branch`),
  CONSTRAINT `id_trader_requested_fk` FOREIGN KEY (`id_trader_requested`) REFERENCES `trader` (`id_trader`),
  CONSTRAINT `id_trader_requester_fk` FOREIGN KEY (`id_trader_requester`) REFERENCES `trader` (`id_trader`)
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
-- Table structure for table `point_of_sale`
--

DROP TABLE IF EXISTS `point_of_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_of_sale` (
  `id_point_of_sale` int unsigned NOT NULL AUTO_INCREMENT,
  `point_of_sale_number` int NOT NULL,
  `id_branch` int unsigned NOT NULL,
  PRIMARY KEY (`id_point_of_sale`),
  KEY `id_branch_pos_fk_idx` (`id_branch`),
  CONSTRAINT `id_branch_pos_fk` FOREIGN KEY (`id_branch`) REFERENCES `branch` (`id_branch`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point_of_sale`
--

LOCK TABLES `point_of_sale` WRITE;
/*!40000 ALTER TABLE `point_of_sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `point_of_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `points_of_sale_control`
--

DROP TABLE IF EXISTS `points_of_sale_control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `points_of_sale_control` (
  `id_pos_control` int NOT NULL AUTO_INCREMENT,
  `current_count` int NOT NULL,
  `total_count` int NOT NULL,
  `id_trader` int unsigned NOT NULL,
  PRIMARY KEY (`id_pos_control`),
  KEY `id_trader_control_of_pos_fk_idx` (`id_trader`),
  CONSTRAINT `id_trader_control_of_pos_fk` FOREIGN KEY (`id_trader`) REFERENCES `trader` (`id_trader`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `points_of_sale_control`
--

LOCK TABLES `points_of_sale_control` WRITE;
/*!40000 ALTER TABLE `points_of_sale_control` DISABLE KEYS */;
/*!40000 ALTER TABLE `points_of_sale_control` ENABLE KEYS */;
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
  `id_point_of_sale_issuing` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `beneficiary` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `deadline` date NOT NULL,
  `protest` bit(1) NOT NULL,
  `stamp` bit(1) NOT NULL,
  PRIMARY KEY (`id_promissory_note`),
  KEY `reference_operation_promissory_note_fk_idx` (`id_point_of_sale_issuing`,`date_of_issue`),
  CONSTRAINT `reference_operation_promissory_note_fk` FOREIGN KEY (`id_point_of_sale_issuing`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale_issuing`, `date_of_issue`)
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
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_order` (
  `id_purchase_order` int unsigned NOT NULL AUTO_INCREMENT,
  `num_purchase_order` int unsigned NOT NULL,
  `deadline` date NOT NULL,
  `place_of_delivery` varchar(20) NOT NULL,
  `carrier` varchar(25) NOT NULL,
  `id_point_of_sale_issuing` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `conditions` varchar(20) NOT NULL,
  PRIMARY KEY (`id_purchase_order`),
  KEY `reference_operation_purchase_order_fk_idx` (`id_point_of_sale_issuing`,`date_of_issue`),
  CONSTRAINT `reference_operation_purchase_order_fk` FOREIGN KEY (`id_point_of_sale_issuing`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale_issuing`, `date_of_issue`)
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
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt` (
  `id_receipt` int unsigned NOT NULL AUTO_INCREMENT,
  `num_receipt` int unsigned NOT NULL,
  `amount` int NOT NULL,
  `address` varchar(40) NOT NULL,
  `id_point_of_sale_issuing` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `payer` varchar(40) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id_receipt`),
  KEY `reference_operation_receipt_fk_idx` (`id_point_of_sale_issuing`,`date_of_issue`),
  CONSTRAINT `reference_operation_receipt_fk` FOREIGN KEY (`id_point_of_sale_issuing`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale_issuing`, `date_of_issue`)
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
  `id_point_of_sale_issuing` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  `cash` decimal(10,2) NOT NULL,
  `check` decimal(10,2) NOT NULL,
  `documents` decimal(10,2) NOT NULL,
  `time` time NOT NULL,
  `payer` varchar(40) NOT NULL,
  `payer_address` varchar(20) NOT NULL,
  PRIMARY KEY (`id_receipt_x`),
  KEY `reference_operation_receipt_x_fk_idx` (`id_point_of_sale_issuing`,`date_of_issue`),
  CONSTRAINT `reference_operation_receipt_x_fk` FOREIGN KEY (`id_point_of_sale_issuing`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale_issuing`, `date_of_issue`)
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
-- Table structure for table `registered_operation`
--

DROP TABLE IF EXISTS `registered_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registered_operation` (
  `id_trader` int unsigned NOT NULL,
  `id_branch` int unsigned NOT NULL,
  `id_partner_trader` int unsigned NOT NULL,
  `id_partner` int unsigned NOT NULL,
  `id_point_of_sale_issuing` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  PRIMARY KEY (`id_trader`,`id_branch`,`id_partner_trader`,`id_partner`,`id_point_of_sale_issuing`,`date_of_issue`),
  KEY `reference_operation_in_registered_op_fk_idx` (`id_point_of_sale_issuing`,`date_of_issue`),
  CONSTRAINT `reference_operation_in_registered_op_fk` FOREIGN KEY (`id_point_of_sale_issuing`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale_issuing`, `date_of_issue`),
  CONSTRAINT `reference_partner_in_registered_op_fk` FOREIGN KEY (`id_trader`, `id_branch`, `id_partner_trader`, `id_partner`) REFERENCES `partner` (`id_trader_requester`, `id_branch_requester`, `id_trader_requested`, `id_branch_requested`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registered_operation`
--

LOCK TABLES `registered_operation` WRITE;
/*!40000 ALTER TABLE `registered_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `registered_operation` ENABLE KEYS */;
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
  `id_point_of_sale_issuing` int unsigned NOT NULL,
  `date_of_issue` datetime NOT NULL,
  PRIMARY KEY (`id_remittance`),
  KEY `reference_operation_remittance_fk_idx` (`id_point_of_sale_issuing`,`date_of_issue`),
  CONSTRAINT `reference_operation_remittance_fk` FOREIGN KEY (`id_point_of_sale_issuing`, `date_of_issue`) REFERENCES `operation` (`id_point_of_sale_issuing`, `date_of_issue`)
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
-- Table structure for table `trader`
--

DROP TABLE IF EXISTS `trader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trader` (
  `id_trader` int unsigned NOT NULL AUTO_INCREMENT,
  `cuit` varchar(15) NOT NULL,
  `name` varchar(20) NOT NULL,
  `vat_category` enum('REGISTERED_RESPONSIBLE','MONOTAX_RESPONSIBLE') NOT NULL,
  `actives` int unsigned NOT NULL,
  `passives` int unsigned NOT NULL,
  PRIMARY KEY (`id_trader`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trader`
--

LOCK TABLES `trader` WRITE;
/*!40000 ALTER TABLE `trader` DISABLE KEYS */;
/*!40000 ALTER TABLE `trader` ENABLE KEYS */;
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
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_avatar`
--

DROP TABLE IF EXISTS `user_avatar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_avatar` (
  `id_user_avatar` int unsigned NOT NULL AUTO_INCREMENT,
  `avatar` mediumtext NOT NULL,
  `id_user` int unsigned NOT NULL,
  PRIMARY KEY (`id_user_avatar`),
  UNIQUE KEY `UK_9r98vdxdtt1tw76ecsrxpv2io` (`id_user`),
  CONSTRAINT `id_user_avatar_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_avatar`
--

LOCK TABLES `user_avatar` WRITE;
/*!40000 ALTER TABLE `user_avatar` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_avatar` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-18  6:02:38
