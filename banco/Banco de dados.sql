DROP table produtos;

CREATE table produtos (
`idprodutos` INT primary key NOT NULL AUTO_INCREMENT,
  `` VARCHAR(45) NULL,
);

CREATE TABLE `dbsistema`.`produtoss` (
  idprodutos INT NOT NULL AUTO_INCREMENT,
  produto varchar(15),
  barcode VARCHAR(20) NOT NULL,
  descricao VARCHAR(200) not null,
  foto blob null,
  estoque int,
  estoquemin int,
  valor decimal(10,2),
  unidade enum("UN", "CX", "PC","Kg","m"),
  localarmazenagem varchar(15)
);
  
  
  select * from produtos;
  
- codigo do produto (PK)
  - barcode 
  - descricao
  - foto (longblob)
  - estoque (int)
  - estoquemin (int)
  - valor
  - unidade de medida (combo box: UN CX PC Kg m)
  - local de armazenagem


CREATE TABLE `dbsistema`.`new_table` (
  `idprodutos` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idnew_table`));
  
  create table produtosDida(
  codigo int primary key auto_increment,
  produto varchar(50) not null,
  barcode varchar(255) null,
  lote varchar(20) not null,
  descricao varchar(250),
  fabricante varchar(50) null,
  dataent timestamp default current_timestamp,
  dataval date not null,
  foto longblob,
  estoque int not null,
  estoquemin int not null,
  unidade char(2) not null,
  localarm varchar(50),
  custo decimal(10,2),
  lucro decimal(10,2),
  idfor int not null,
  foreign key (idfor) references fornecedores(idfornecedores)
);
  
  
  insert into produtosDida(
  produtos,lote,descricao,dataval, estoque, estoquemin, unidade, custo, idfor
  )values('Fonte ATx 450','1','Fonte ATX real 450 Watts',20280802,5,2,'UN',275.35,1);