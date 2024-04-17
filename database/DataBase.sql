/*
*Sistema para gestão OS e controle de estoque
* @author Lucas pereira de souza
*/
DROP SCHEMA IF EXISTS `databaseos`;
CREATE SCHEMA databaseos;
USE databaseos;

CREATE TABLE `clientes` (
  `idcli` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `fone` varchar(15) NOT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `endereco` varchar(50) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `complemento` varchar(20) DEFAULT NULL,
  `bairro` varchar(30) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `uf` char(2) NOT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idcli`),
  UNIQUE KEY `cpf` (`cpf`),
  UNIQUE KEY `cpf_2` (`cpf`),
  UNIQUE KEY `cpf_3` (`cpf`)
);

CREATE TABLE `fornecedores` (
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
);

CREATE TABLE `produtos` (
  `idproduto` int NOT NULL AUTO_INCREMENT,
  `produto` varchar(50) NOT NULL,
  `barcode` varchar(30) DEFAULT NULL,
  `fabricante` varchar(45) NOT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `dataentrada` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `datavalidade` date NOT NULL,
  `foto` longblob,
  `estoque` int NOT NULL,
  `estoquemin` int NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `unidademedida` enum('UN','CX','PC','Kg','M') NOT NULL,
  `localarmazenagem` varchar(50) NOT NULL,
  `lote` varchar(45) NOT NULL,
  `lucro` int DEFAULT NULL,
  `idfornecedor` int NOT NULL,
  PRIMARY KEY (`idproduto`),
  KEY `idfornecedor` (`idfornecedor`),
  CONSTRAINT `produtosDida` FOREIGN KEY (`idfornecedor`) REFERENCES `fornecedores` (`idfornecedores`)
);

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
);

CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `login` varchar(15) NOT NULL,
  `senha` varchar(250) NOT NULL,
  `perfil` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
);

INSERT INTO `usuarios` (`nome`, `login`, `senha`, `perfil`) VALUES ('admin', 'admin', md5('admin'), 'admin');