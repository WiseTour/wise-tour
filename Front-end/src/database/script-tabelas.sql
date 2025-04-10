CREATE DATABASE WiseTour;
USE WiseTour;

CREATE TABLE cadastro (
idCadastro INT auto_increment primary KEY,
nome VARCHAR(45),
email VARCHAR(45),
numeroUsuario VARCHAR(45)
);

select * from cadastro;

create table login (
idLogin INT PRIMARY KEY AUTO_INCREMENT,
email varchar(45),
senha varchar(45));

insert into login (email, senha) values (
'ian@hotmail.com', 'urubu100');

select * from login;
