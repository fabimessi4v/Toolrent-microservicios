-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: toolrent_bd
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `loans`
--

USE toolrentdb;
DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans` (
  `id` varchar(36) NOT NULL,
  `client_id` varchar(36) NOT NULL,
  `tool_id` varchar(36) NOT NULL,
  `customers_id` varchar(36) NOT NULL,
  `delivery_date` date NOT NULL,
  `due_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `status` tinytext NOT NULL,
  `fine` float DEFAULT '0',
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `client_id` (`client_id`),
  KEY `customers_id` (`customers_id`),
  KEY `loans_ibfk_2` (`tool_id`),
  CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `users` (`id`),
  CONSTRAINT `loans_ibfk_2` FOREIGN KEY (`tool_id`) REFERENCES `tools` (`id`) ON DELETE CASCADE,
  CONSTRAINT `loans_ibfk_3` FOREIGN KEY (`customers_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES ('0ec71626-dc78-4a86-afe8-5aeeb891a29d','5310f2ee-81f8-11f0-8b09-00155d4a2cda','53139310-81f8-11f0-8b09-00155d4a2cda','4b94e85d-6b2e-434e-b542-21e906b5549e','2025-09-24','2025-10-30',NULL,'ACTIVE',0,NULL),('3677490e-0867-457e-9f0b-b573500c7c06','5310f2ee-81f8-11f0-8b09-00155d4a2cda','5313917c-81f8-11f0-8b09-00155d4a2cda','4b94e85d-6b2e-434e-b542-21e906b5549e','2025-09-24','2025-10-14',NULL,'ACTIVE',0,NULL),('ba6cd25c-2dc2-475e-8994-d8f21c966e67','5310f2ee-81f8-11f0-8b09-00155d4a2cda','53138df7-81f8-11f0-8b09-00155d4a2cda','0647142a-f745-4c22-a2c3-0f6def07d4de','2025-12-20','2025-12-30','2025-12-30','RETURNED',0,'2025-12-20 16:24:20'),('d5f40020-155e-4199-bc1b-bd66eff79b39','5310f2ee-81f8-11f0-8b09-00155d4a2cda','80cdd6bf-a068-46d4-ab12-836bb21eb20d','4b94e85d-6b2e-434e-b542-21e906b5549e','2025-09-24','2025-10-03',NULL,'ACTIVE',0,NULL),('e98cc4ff-b362-488a-ac87-6e325540f42a','5310f2ee-81f8-11f0-8b09-00155d4a2cda','53139286-81f8-11f0-8b09-00155d4a2cda','4b94e85d-6b2e-434e-b542-21e906b5549e','2025-09-23','2025-09-29',NULL,'ACTIVE',0,NULL),('f908e498-8ac7-40e7-a9e5-8f1f079c5e3a','5310f2ee-81f8-11f0-8b09-00155d4a2cda','e6e12e9d-72f6-4a52-85e9-facb31365ea6','4b94e85d-6b2e-434e-b542-21e906b5549e','2025-09-24','2025-09-29',NULL,'ACTIVE',0,NULL);
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-30 14:36:46
