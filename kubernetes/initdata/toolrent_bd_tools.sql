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
-- Table structure for table `tools`
--

USE toolrentdb;

DROP TABLE IF EXISTS `tools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tools` (
  `id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL,
  `status` tinytext NOT NULL,
  `replacement_value` float NOT NULL,
  `rental_price` float NOT NULL,
  `stock` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `tool_image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tools`
--

LOCK TABLES `tools` WRITE;
/*!40000 ALTER TABLE `tools` DISABLE KEYS */;
INSERT INTO `tools` VALUES ('0f4768e1-df54-4aa3-84c2-ac580dd4c2dd','martillo de oro','Manual','Disponible',5000000,5000,2,'2025-09-05 21:05:38','https://storage.googleapis.com/toolrent-bucket/martillo_de_oro.jpg'),('53138df7-81f8-11f0-8b09-00155d4a2cda','Taladro Bosch','Electric','AVAILABLE',120000,5000,5,'2025-08-25 21:13:19',NULL),('5313917c-81f8-11f0-8b09-00155d4a2cda','Martillo','Manual','AVAILABLE',15000,1000,21,'2025-08-25 21:13:19',NULL),('53139286-81f8-11f0-8b09-00155d4a2cda','Sierra Circular','Electric','UNDER_REPAIR',90000,4000,2,'2025-08-25 21:13:19',NULL),('53139310-81f8-11f0-8b09-00155d4a2cda','Escalera 3m','Manual','AVAILABLE',45000,2000,12,'2025-08-25 21:13:19',NULL),('80cdd6bf-a068-46d4-ab12-836bb21eb20d','casco AZUL','Equipo de Seguridad','Disponible',50000,500,5,'2025-09-08 23:43:49',NULL),('83f6deb0-5445-4509-a38b-bd73afa79b8a','rotomartillo','Electrica','Disponible',150000,15000,4,'2025-09-13 18:03:02',NULL),('e6e12e9d-72f6-4a52-85e9-facb31365ea6','Taladro de pecho manual','Manual','Disponible',600000,20000,9,'2025-09-07 19:16:05',NULL);
/*!40000 ALTER TABLE `tools` ENABLE KEYS */;
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
