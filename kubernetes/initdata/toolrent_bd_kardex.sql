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
-- Table structure for table `kardex`
--
USE toolrentdb;

DROP TABLE IF EXISTS `kardex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kardex` (
  `id` varchar(36) NOT NULL,
  `tool_id` varchar(36) NOT NULL,
  `user_id` varchar(36) NOT NULL,
  `loans_id` varchar(36) DEFAULT NULL,
  `type` tinytext NOT NULL,
  `quantity` int NOT NULL,
  `movement_date` date NOT NULL,
  `comments` text,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `loans_id` (`loans_id`),
  KEY `kardex_ibfk_1` (`tool_id`),
  CONSTRAINT `kardex_ibfk_1` FOREIGN KEY (`tool_id`) REFERENCES `tools` (`id`) ON DELETE CASCADE,
  CONSTRAINT `kardex_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `kardex_ibfk_3` FOREIGN KEY (`loans_id`) REFERENCES `loans` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kardex`
--

LOCK TABLES `kardex` WRITE;
/*!40000 ALTER TABLE `kardex` DISABLE KEYS */;
INSERT INTO `kardex` VALUES ('0ea31e63-7e97-495c-adad-eb4de527a64e','53139286-81f8-11f0-8b09-00155d4a2cda','5310f2ee-81f8-11f0-8b09-00155d4a2cda','e98cc4ff-b362-488a-ac87-6e325540f42a','LOAN',1,'2025-09-23',NULL,'2025-09-23 23:36:48'),('47493753-a57f-4afc-81f0-a75275cf7fc8','80cdd6bf-a068-46d4-ab12-836bb21eb20d','5310f2ee-81f8-11f0-8b09-00155d4a2cda','d5f40020-155e-4199-bc1b-bd66eff79b39','LOAN',1,'2025-09-23',NULL,'2025-09-23 23:36:33'),('d2df5f48-d551-48e0-9581-57524fbd7db5','5313917c-81f8-11f0-8b09-00155d4a2cda','5310f2ee-81f8-11f0-8b09-00155d4a2cda','3677490e-0867-457e-9f0b-b573500c7c06','LOAN',1,'2025-09-23',NULL,'2025-09-23 23:35:50'),('f20ddbd7-11a4-44cf-884b-aeaee705be4b','e6e12e9d-72f6-4a52-85e9-facb31365ea6','5310f2ee-81f8-11f0-8b09-00155d4a2cda','f908e498-8ac7-40e7-a9e5-8f1f079c5e3a','LOAN',1,'2025-09-23',NULL,'2025-09-23 23:37:03'),('fc57911c-0db4-4592-b7a7-cba31736d68e','53139310-81f8-11f0-8b09-00155d4a2cda','5310f2ee-81f8-11f0-8b09-00155d4a2cda','0ec71626-dc78-4a86-afe8-5aeeb891a29d','LOAN',1,'2025-09-23',NULL,'2025-09-23 23:36:14');
/*!40000 ALTER TABLE `kardex` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-30 14:36:47
