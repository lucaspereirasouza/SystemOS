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
-- Table structure for table `fornecedoresDida`
--

DROP TABLE IF EXISTS `fornecedoresDida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedoresDida` (
  `idfornecedores` int NOT NULL AUTO_INCREMENT,
  `razao` varchar(50) NOT NULL,
  `fantasia` varchar(50) NOT NULL,
  `fone` varchar(15) NOT NULL,
  `vendedor` varchar(20) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `site` varchar(50) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `cpfcnpj` varchar(15) NOT NULL,
  `ie` varchar(20) DEFAULT NULL,
  `endereco` varchar(50) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `complemento` varchar(20) DEFAULT NULL,
  `bairro` varchar(30) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `uf` char(2) NOT NULL,
  PRIMARY KEY (`idfornecedores`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedoresDida`
--

LOCK TABLES `fornecedoresDida` WRITE;
/*!40000 ALTER TABLE `fornecedoresDida` DISABLE KEYS */;
INSERT INTO `fornecedoresDida` VALUES (12,'Admin','Administrador','9999Admin','Admin','Admin@Admin','https://Admin.com','0000','00000','0000','000','00','0','0','0','0'),(13,'Lucas pereira','Lucas games','00','Nulo','lucaspereiradesouza2163@outlook.com','https://lucasgames.com','03654-020','51166827810','123','Rua Filipe Galvão','633','','Vila Granada','São Paulo','SP');
/*!40000 ALTER TABLE `fornecedoresDida` ENABLE KEYS */;
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
