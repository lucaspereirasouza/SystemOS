/**
* Sistema de gestão de OS
* @author Lucas pereira de souza 
*/
create database dbsistema;
create database dbcliente;
use dbsistema;
drop table cliente;
use dbcliente;

select * from clientes;
drop table clientes;
create table clientes (
	idcli int primary key auto_increment,
    nome varchar(50) not null,
    fone varchar(15) not null,
    cep varchar(10),
    endereco varchar(50) not null,
    numero varchar(10) not null,
    complemento varchar(20),
    bairro varchar(30) not null,
    cidade varchar(30) not null,
    uf char(2) not null
);
/**

id int AI PK 
nome varchar(50) 
endereco varchar(50) 
telefone varchar(20) 
email varchar(50) 
cpf varchar(15) 
tipo varchar(50)
**/
drop table clientes;
create table clientes (
	idcli int primary key auto_increment,
    nome varchar(50) not null,
    fone varchar(15) not null,
    cep varchar(10),
    endereco varchar(50) not null,
    numero varchar(10) not null,
    complemento varchar(20),
    bairro varchar(30) not null,
    cidade varchar(30) not null,
    uf char(2) not null
);
drop table catuaba;
use dbsistema;
select * from usuarios;

drop table servicos;

create table usuarios(
id int primary key auto_increment,
nome varchar(50) not null,
login varchar(15) not null unique,
senha varchar (250) not null,
perfil varchar(10) not null
);
insert into usuarios(nome,login,senha,perfil) values ("Admin","Admin",md5("admin"),"admin");
insert into usuarios(nome,login,senha,perfil) values ("Lucas P. Souza","Lucaspereira",md5("123@senac"),"user");
delete from usuarios where id = ?;

drop table usuarios;

select * from usuarios;
create table servicos(
	os int primary key auto_increment,
	dataOS timestamp default current_timestamp,
    equipamento varchar(200) not null,
    defeito varchar(200) not null,
    valor decimal(10,2),
    id int not null,
    foreign key(id) references clientes(idcli)
);
insert into servicos (equipamento,defeito,valor,id)
value ('teste','troca da fonte',250,23);



insert into servicos (id,equipamento,defeito,valor)
value (23,'teste','troca da fonte',250);

select nome,fone from clientes order by nome;

select * from clientes;
select * from usuarios;
select * from servicos;

select * from servicos where os = 1;

insert into clientes (nome,fone,cep,endereco,numero,complemento,bairro,cidade,ufusuarios)
values ('lucas','123456','endereco','214','214','a','a','a','a');

select * from servicos
inner join clientes
on servicos.id = clientes.idcli;

select servicos.os,servicos.dataOS,servicos.equipamento,servicos.defeito,servicos.valor,
clientes.nome;
select
from servicos
inner join clientes
on servicos.idclientes = clientes.idcli;


select nome,fone,cep from cclientesclientesservicoslientes order by nome;

SELECT OS FROM servicos WHERE OS = (SELECT MAX(OS) FROM servicos);

ALTER TABLE clientes
drop column cpf;

ALTER TABLE clientes
add column cpf varchar(11);

select os,dataOS,defeito,valor,nome from servicos inner join clientes on servicos.id = clientes.idcli;