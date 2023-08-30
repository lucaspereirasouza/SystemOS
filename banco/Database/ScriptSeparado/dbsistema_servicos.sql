-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 10.26.45.241    Database: dbsistema
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.22.04.1

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
-- Table structure for table `servicos`
--

DROP TABLE IF EXISTS `servicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicos` (
  `os` int NOT NULL AUTO_INCREMENT,
  `dataOS` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `equipamento` varchar(200) NOT NULL,
  `defeito` varchar(200) NOT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`os`),
  KEY `id` (`id`),
  CONSTRAINT `servicos_ibfk_1` FOREIGN KEY (`id`) REFERENCES `clientes` (`idcli`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicos`
--

LOCK TABLES `servicos` WRITE;
/*!40000 ALTER TABLE `servicos` DISABLE KEYS */;
INSERT INTO `servicos` VALUES (8,'2023-06-07 19:51:11','Xbox 360','Troca da fonte',220.00,23),(9,'2023-06-12 17:34:55','Notebook','Notebook quebrado',123.00,23),(10,'2023-06-14 16:58:55','Xbox 360','Luz vermelha, não liga',520.25,23),(11,'2023-06-14 16:59:40','Equipamento decimal','Decimal',520.26,23),(12,'2023-06-14 17:51:23','play 5','Não ta desbloqueado',12.00,29),(19,'2023-06-14 18:58:07','Equipamento novo','Equipamento novo',12345.00,24),(20,'2023-06-14 19:00:20','Ps4','Desbloqueio do console',125.00,24),(21,'2023-06-19 17:40:19','Notebook','Tela quebrada',125.00,33),(22,'2023-07-26 20:25:24','teste','teste',123.00,23),(23,'2023-07-26 20:29:58','notebook','notebook',123.00,24);
/*!40000 ALTER TABLE `servicos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-30 14:32:59
