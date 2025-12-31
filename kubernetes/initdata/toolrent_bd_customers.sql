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
-- Table structure for table `customers`
--
USE toolrentdb;

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rut` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` tinytext NOT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rut` (`rut`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('0647142a-f745-4c22-a2c3-0f6def07d4de','gdfdg','dfgdf','fddfg','fabimessi4@gmail.com','Activo','2025-09-21 18:50:37'),('35c896d3-877e-4784-b74e-beb687e1f1fb','Eusebio Rojas','11.053.588-0','978757056','eusebio@gmail.com','Activo','2025-09-10 22:26:54'),('4b94e85d-6b2e-434e-b542-21e906b5549e','Carlos Iturra','111111111','978757099','carlositurra@gmail.com','Activo','2025-09-21 18:44:06'),('5312eb33-81f8-11f0-8b09-00155d4a2cda','Juan Pérez','12.345.678-9','987654321','juanperez@mail.com','ACTIVE','2025-08-25 21:13:19'),('5312f00e-81f8-11f0-8b09-00155d4a2cda','María González','9.876.543-2','912345678','maria.gonzalez@mail.com','RESTRICTED','2025-08-25 21:13:19'),('5312f23b-81f8-11f0-8b09-00155d4a2cda','Carlos Ruiz','11.223.344-5','934567812','carlos.ruiz@mail.com','RESTRICTED','2025-08-25 21:13:19'),('66faf970-1bc5-4186-afd3-1d8b963bac2a','Juan Ignacio Pérez','46456','(9) 7875 7036','fabi@fabi.com','Activo','2025-09-21 22:02:34'),('7bc126f3-62e1-4a39-b682-b151b3ffc31a','dfsgdfsg','sdfgds','dsfg','dsfg@dfgs.ff','Activo','2025-09-21 18:48:53'),('8b8310af-fff5-47c6-b546-69a72d1d2a62','Nuevo Cliente','12.222.333-4','+56 9 5555 5555','nuevo@email.com','Activo','2025-09-09 22:04:23');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
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
