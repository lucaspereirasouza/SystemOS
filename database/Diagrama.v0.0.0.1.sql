CREATE SCHEMA OSDB;

CREATE TABLE `address` (
  `idendereco` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `cep` varchar(10) DEFAULT null,
  `endereco` varchar(50) NOT NULL,
  `numero` varchar(10) NOT NULL
);

CREATE TABLE `identity` (
  `identity` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `idendereco` int NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `cnpj` varchar(15),
  `fone` varchar(15) NOT NULL,
  `telefone` varchar(15)
);

CREATE TABLE `client` (
  `idclient` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `identity` int NOT NULL,
  `iorder` int NOT NULL
);

CREATE TABLE `supplier` (
  `idsupplier` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `identity` int NOT NULL
);

CREATE TABLE `product` (
  `idproduct` int PRIMARY KEY NOT NULL,
  `productname` varchar(50) NOT NULL,
  `fabricante` varchar(45) NOT NULL,
  `descricao` varchar(200) DEFAULT null,
  `dataentrada` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `datavalidade` date NOT NULL,
  `foto` longblob,
  `estoque` int NOT NULL,
  `estoquemin` int NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `unidademedida` ENUM ('UN', 'CX', 'PC', 'Kg', 'M') NOT NULL,
  `localarmazenagem` varchar(50) NOT NULL,
  `lote` varchar(45) NOT NULL,
  `lucro` int DEFAULT null,
  `idfornecedor` int NOT NULL
);

CREATE TABLE `services` (
  `idservice` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `idproduct` int NOT NULL,
  `dataOS` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `equipamento` varchar(200) NOT NULL,
  `defeito` varchar(200) NOT NULL,
  `valor` decimal(10,2) DEFAULT null,
  `id` int NOT NULL
);

CREATE TABLE `admin` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `login` varchar(15) NOT NULL,
  `password` varchar(250) NOT NULL,
  `identity` int NOT NULL
);

CREATE TABLE `operator` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `login` varchar(15) NOT NULL,
  `senha` varchar(250) NOT NULL,
  `identity` int NOT NULL,
  `idproduct` int NOT NULL
);

CREATE TABLE `payment` (
  `idpayment` int PRIMARY KEY NOT NULL AUTO_INCREMENT
);

CREATE TABLE `order` (
  `idorder` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `idpayment` int NOT NULL,
  `idproduct` int NOT NULL
);

CREATE INDEX `idfornecedor` ON `product` (`idfornecedor`);

CREATE INDEX `id` ON `services` (`id`);

ALTER TABLE `address` ADD FOREIGN KEY (`idendereco`) REFERENCES `identity` (`idendereco`);

ALTER TABLE `identity` ADD FOREIGN KEY (`identity`) REFERENCES `admin` (`identity`);

ALTER TABLE `identity` ADD FOREIGN KEY (`identity`) REFERENCES `supplier` (`identity`);

ALTER TABLE `identity` ADD FOREIGN KEY (`identity`) REFERENCES `operator` (`identity`);

ALTER TABLE `client` ADD FOREIGN KEY (`iorder`) REFERENCES `order` (`idorder`);

ALTER TABLE `order` ADD FOREIGN KEY (`idpayment`) REFERENCES `payment` (`idpayment`);

CREATE TABLE `product_order` (
  `product_idproduct` int,
  `order_idproduct` int,
  PRIMARY KEY (`product_idproduct`, `order_idproduct`)
);

ALTER TABLE `product_order` ADD FOREIGN KEY (`product_idproduct`) REFERENCES `product` (`idproduct`);

ALTER TABLE `product_order` ADD FOREIGN KEY (`order_idproduct`) REFERENCES `order` (`idproduct`);

