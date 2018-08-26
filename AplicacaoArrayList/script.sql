drop database if exists bancoarray;
create database bancoarray;

use bancoarray;

create table tb_login(
id_login int primary key auto_increment,
login varchar(50) not null,
senha varchar(50) not null
);

insert into tb_login(login, senha) values("Victor", "123456");

select * from tb_login;