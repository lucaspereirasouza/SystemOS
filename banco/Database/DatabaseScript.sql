
CREATE TABLE `fornecedores` (
  `idfornecedor` int NOT NULL AUTO_INCREMENT,
  `fornecedor` varchar(50) NOT NULL,
  `contato` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `endereco` varchar(20) NOT NULL,
  `complemento` varchar(10) DEFAULT NULL,
  `cpfecnpj` varchar(14) NOT NULL,
  `cidade` varchar(40) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `bairro` varchar(30) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `numero` varchar(10) NOT NULL,
  PRIMARY KEY (`idfornecedor`),
  UNIQUE KEY `cpfecnpj` (`cpfecnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `login` varchar(20) NOT NULL,
  `senha` varchar(250) NOT NULL,
  `perfil` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `clientes` (
  `idclientes` int NOT NULL AUTO_INCREMENT,
  `clientes` varchar(50) NOT NULL,
  `contato` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `endereco` varchar(20) NOT NULL,
  `complemento` varchar(10) DEFAULT NULL,
  `cpf` varchar(11) NOT NULL,
  `cidade` varchar(40) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `bairro` varchar(30) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `numero` varchar(10) NOT NULL,
  PRIMARY KEY (`idclientes`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `produtos` (
  `idproduto` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `barcode` varchar(20) DEFAULT NULL,
  `descricao` varchar(200) NOT NULL,
  `foto` longblob,
  `estoque` int NOT NULL,
  `estoquemin` int NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `medida` varchar(10) NOT NULL,
  `armazenagem` varchar(50) NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`idproduto`),
  KEY `id` (`id`),
  CONSTRAINT `produtos_ibfk_1` FOREIGN KEY (`id`) REFERENCES `fornecedores` (`idfornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `servicos` (
  `idservicos` int NOT NULL,
  `dataOS` timestamp NOT NULL,
  `equipamento` varchar(200) NOT NULL,
  `defeito` varchar(200) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `idcli` int NOT NULL,
  PRIMARY KEY (`idservicos`),
  KEY `idcli` (`idcli`),
  CONSTRAINT `servicos_ibfk_1` FOREIGN KEY (`idcli`) REFERENCES `clientes` (`idclientes`)
)
