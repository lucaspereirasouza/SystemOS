DROP table produtos;

CREATE table produtos (
`idprodutos` INT primary key NOT NULL AUTO_INCREMENT,
  `teste` VARCHAR(45) NULL,
);

CREATE TABLE `dbsistema`.`produtoss` (
  `idprodutos` INT NOT NULL AUTO_INCREMENT,
  `teste` VARCHAR(45) NULL,
  PRIMARY KEY (`idprodutos`));


CREATE TABLE `dbsistema`.`new_table` (
  `idprodutos` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idnew_table`));