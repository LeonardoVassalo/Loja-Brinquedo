CREATE DATABASE toyshell;
USE toyshell ;


CREATE TABLE CadBrinquedos (
idBrinquedo int primary key auto_increment,
nome varchar(100) not null,
descricao varchar (100) not null,
categoria varchar(50) not null,
marca varchar (20) not null,
valor decimal (10.4) not null,
detalhes varchar(200),
imagem varchar(100)
) ENGINE=INNODB;

CREATE TABLE usuario(
idUsuario int primary key auto_increment,
nome varchar(100) not null,
senha varchar(50) not null
) ENGINE=INNODB;

insert into usuario (nome, senha) values ('leo', 'leo');

select*from usuario;