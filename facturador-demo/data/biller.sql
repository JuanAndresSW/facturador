CREATE DATABASE  IF NOT EXISTS `facturador_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `facturador_db`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: facturador_db
-- ------------------------------------------------------
-- Server version	8.0.31

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
  KEY `id_user_account_fk_idx` (`id_user`),
  KEY `id_trader_account_fk_idx` (`id_trader`),
  CONSTRAINT `id_trader_account_fk` FOREIGN KEY (`id_trader`) REFERENCES `trader` (`id_trader`) ON DELETE CASCADE,
  CONSTRAINT `id_user_account_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `fantasy_name` varchar(30) NOT NULL,
  `email` varchar(128) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `province` varchar(20) NOT NULL,
  `city` varchar(45) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `street` varchar(50) NOT NULL,
  `address_number` varchar(5) NOT NULL,
  `preference_color` varchar(7) NOT NULL,
  `creation_date` date NOT NULL,
  `photo` mediumtext NOT NULL,
  `logo` mediumtext NOT NULL,
  `id_owner_trader` int unsigned NOT NULL,
  PRIMARY KEY (`id_branch`),
  KEY `id_trader_branch_fk_idx` (`id_owner_trader`),
  CONSTRAINT `id_trader_branch_fk` FOREIGN KEY (`id_owner_trader`) REFERENCES `trader` (`id_trader`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_note`
--

DROP TABLE IF EXISTS `credit_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit_note` (
  `id_credit` int unsigned NOT NULL AUTO_INCREMENT,
  `sell_conditions` enum('CASH','CARD','CHECKING_ACCOUNT','DOCUMENT') NOT NULL,
  `vat` int unsigned NOT NULL,
  `id_operation_parent` int unsigned NOT NULL,
  `type` enum('A','B','C') NOT NULL,
  `count_credit_number` int(8) unsigned zerofill NOT NULL,
  `credit_number` varchar(13) NOT NULL,
  PRIMARY KEY (`id_credit`),
  UNIQUE KEY `id_operation_parent_UNIQUE` (`id_operation_parent`),
  KEY `operation_credit_fk_idx` (`id_operation_parent`),
  CONSTRAINT `operation_credit_fk` FOREIGN KEY (`id_operation_parent`) REFERENCES `operation` (`id_operation`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_note`
--

LOCK TABLES `credit_note` WRITE;
/*!40000 ALTER TABLE `credit_note` DISABLE KEYS */;
/*!40000 ALTER TABLE `credit_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `debit_note`
--

DROP TABLE IF EXISTS `debit_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `debit_note` (
  `id_debit` int unsigned NOT NULL AUTO_INCREMENT,
  `sell_conditions` enum('CASH','CARD','CHECKING_ACCOUNT','DOCUMENT') NOT NULL,
  `vat` int unsigned NOT NULL,
  `id_operation_parent` int unsigned NOT NULL,
  `type` enum('A','B','C') NOT NULL,
  `count_debit_number` int(8) unsigned zerofill NOT NULL,
  `debit_number` varchar(13) NOT NULL,
  PRIMARY KEY (`id_debit`),
  UNIQUE KEY `id_operation_parent_UNIQUE` (`id_operation_parent`),
  KEY `operation_debit_fk_idx` (`id_operation_parent`),
  CONSTRAINT `operation_debit_fk` FOREIGN KEY (`id_operation_parent`) REFERENCES `operation` (`id_operation`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debit_note`
--

LOCK TABLES `debit_note` WRITE;
/*!40000 ALTER TABLE `debit_note` DISABLE KEYS */;
/*!40000 ALTER TABLE `debit_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id_invoice` int unsigned NOT NULL AUTO_INCREMENT,
  `sell_conditions` enum('CASH','CARD','CHECKING_ACCOUNT','DOCUMENT') NOT NULL,
  `vat` int unsigned NOT NULL,
  `id_operation_parent` int unsigned NOT NULL,
  `type` enum('A','B','C') NOT NULL,
  `count_invoice_number` int unsigned NOT NULL,
  `invoice_number` varchar(13) NOT NULL,
  PRIMARY KEY (`id_invoice`),
  UNIQUE KEY `id_operation_parent_UNIQUE` (`id_operation_parent`),
  KEY `operation_invoice_fk_idx` (`id_operation_parent`),
  CONSTRAINT `operation_invoice_fk` FOREIGN KEY (`id_operation_parent`) REFERENCES `operation` (`id_operation`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operation` (
  `id_operation` int unsigned NOT NULL AUTO_INCREMENT,
  `id_issuing_trader` int unsigned NOT NULL,
  `issuing_point_of_sale_number` varchar(4) NOT NULL,
  `issue_date` date NOT NULL,
  PRIMARY KEY (`id_operation`),
  KEY `trader_operation_fk_idx` (`id_issuing_trader`),
  CONSTRAINT `trader_operation_fk` FOREIGN KEY (`id_issuing_trader`) REFERENCES `trader` (`id_trader`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point_of_sale`
--

DROP TABLE IF EXISTS `point_of_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_of_sale` (
  `id_point_of_sale` int unsigned NOT NULL AUTO_INCREMENT,
  `point_of_sale_number` int unsigned NOT NULL,
  `creation_date` date NOT NULL,
  `id_owner_branch` int unsigned NOT NULL,
  PRIMARY KEY (`id_point_of_sale`),
  KEY `id_branch_pos_fk_idx` (`id_owner_branch`),
  CONSTRAINT `id_branch_pos_fk` FOREIGN KEY (`id_owner_branch`) REFERENCES `branch` (`id_branch`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `id_pos_control` int unsigned NOT NULL AUTO_INCREMENT,
  `current_count` int unsigned NOT NULL,
  `total_count` int unsigned NOT NULL,
  `id_trader` int unsigned NOT NULL,
  PRIMARY KEY (`id_pos_control`),
  KEY `id_trader_control_of_pos_fk_idx` (`id_trader`),
  CONSTRAINT `id_trader_control_of_pos_fk` FOREIGN KEY (`id_trader`) REFERENCES `trader` (`id_trader`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `points_of_sale_control`
--

LOCK TABLES `points_of_sale_control` WRITE;
/*!40000 ALTER TABLE `points_of_sale_control` DISABLE KEYS */;
/*!40000 ALTER TABLE `points_of_sale_control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id_product` int unsigned NOT NULL AUTO_INCREMENT,
  `quantity` int unsigned NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `detail` varchar(30) NOT NULL,
  `id_operation_parent` int unsigned NOT NULL,
  PRIMARY KEY (`id_product`),
  KEY `operation_product_fk_idx` (`id_operation_parent`),
  CONSTRAINT `operation_product_fk` FOREIGN KEY (`id_operation_parent`) REFERENCES `operation` (`id_operation`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=562 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receiver`
--

DROP TABLE IF EXISTS `receiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receiver` (
  `id_receiver` int unsigned NOT NULL AUTO_INCREMENT,
  `receiver_code` varchar(15) NOT NULL,
  `receiver_name` varchar(30) NOT NULL,
  `receiver_address` varchar(45) NOT NULL,
  `receiver_vat_category` enum('REGISTERED_RESPONSIBLE','MONOTAX_RESPONSIBLE','EXCEMPT','END_CONSUMER','OTHER') NOT NULL,
  `receiver_postal_code` varchar(8) NOT NULL,
  `receiver_locality` varchar(45) NOT NULL,
  `id_operation_parent` int unsigned NOT NULL,
  PRIMARY KEY (`id_receiver`),
  UNIQUE KEY `id_operation_parent_UNIQUE` (`id_operation_parent`),
  KEY `operation_receiver_fk_idx` (`id_operation_parent`),
  CONSTRAINT `operation_receiver_fk` FOREIGN KEY (`id_operation_parent`) REFERENCES `operation` (`id_operation`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiver`
--

LOCK TABLES `receiver` WRITE;
/*!40000 ALTER TABLE `receiver` DISABLE KEYS */;
/*!40000 ALTER TABLE `receiver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remittance`
--

DROP TABLE IF EXISTS `remittance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `remittance` (
  `id_remittance` int unsigned NOT NULL AUTO_INCREMENT,
  `observation` varchar(65) NOT NULL,
  `count_remittance_number` int NOT NULL,
  `remittance_number` varchar(13) NOT NULL,
  `id_operation_parent` int unsigned NOT NULL,
  PRIMARY KEY (`id_remittance`),
  KEY `operation_remittance_fk_idx` (`id_operation_parent`),
  CONSTRAINT `operation_remittance_fk` FOREIGN KEY (`id_operation_parent`) REFERENCES `operation` (`id_operation`)
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
-- Table structure for table `sender`
--

DROP TABLE IF EXISTS `sender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sender` (
  `id_sender` int unsigned NOT NULL AUTO_INCREMENT,
  `sender_code` varchar(15) NOT NULL,
  `sender_name` varchar(30) NOT NULL,
  `sender_address` varchar(45) NOT NULL,
  `sender_contact` varchar(128) NOT NULL,
  `sender_vat_category` enum('REGISTERED_RESPONSIBLE','MONOTAX_RESPONSIBLE') NOT NULL,
  `id_operation_parent` int unsigned NOT NULL,
  PRIMARY KEY (`id_sender`),
  UNIQUE KEY `id_operation_parent_UNIQUE` (`id_operation_parent`),
  KEY `operation_sender_fk_idx` (`id_operation_parent`),
  CONSTRAINT `operation_sender_fk` FOREIGN KEY (`id_operation_parent`) REFERENCES `operation` (`id_operation`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sender`
--

LOCK TABLES `sender` WRITE;
/*!40000 ALTER TABLE `sender` DISABLE KEYS */;
/*!40000 ALTER TABLE `sender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `third_party`
--

DROP TABLE IF EXISTS `third_party`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `third_party` (
  `id_third_party` int unsigned NOT NULL AUTO_INCREMENT,
  `cuit` varchar(15) NOT NULL,
  `email` varchar(128) NOT NULL,
  `province` varchar(20) NOT NULL,
  `department` varchar(45) NOT NULL,
  `locality` varchar(45) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `street` varchar(50) NOT NULL,
  `address_number` varchar(5) NOT NULL,
  `branch_name` varchar(30) NOT NULL,
  `vat_category` enum('REGISTERED_RESPONSIBLE','MONOTAX_RESPONSIBLE') NOT NULL,
  `usage_counter` int unsigned NOT NULL,
  PRIMARY KEY (`id_third_party`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `third_party`
--

LOCK TABLES `third_party` WRITE;
/*!40000 ALTER TABLE `third_party` DISABLE KEYS */;
/*!40000 ALTER TABLE `third_party` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id_ticket` int unsigned NOT NULL AUTO_INCREMENT,
  `id_operation_parent` int unsigned NOT NULL,
  `count_ticket_number` int unsigned NOT NULL,
  `ticket_number` varchar(13) NOT NULL,
  PRIMARY KEY (`id_ticket`),
  UNIQUE KEY `id_operation_parent_UNIQUE` (`id_operation_parent`),
  CONSTRAINT `operation_ticket_fk` FOREIGN KEY (`id_operation_parent`) REFERENCES `operation` (`id_operation`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
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
  `business_name` varchar(20) NOT NULL,
  `vat_category` enum('REGISTERED_RESPONSIBLE','MONOTAX_RESPONSIBLE') NOT NULL,
  PRIMARY KEY (`id_trader`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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

-- Dump completed on 2022-11-09 19:44:25
