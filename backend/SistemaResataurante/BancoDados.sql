create database restaurante;

create table produtos(
	id_prod int primary key auto_increment,
    nome_prod varchar(100),
    descricao_prod varchar (250),
    preco_prod decimal (10,5)
);

create table usuarios(
	id_usuario int primary key auto_increment,
    nome_usuario varchar(100),
    email varchar (150),
    senha varchar (100),
    tipo varchar (50)
);

create table comandas(
	id_comanda int primary key auto_increment,
    nome_cliente varchar (50),
    data datetime,
    status varchar(50),
    total decimal(10,2)
);

create table itens_comandas(
	id_itens_comanda  int primary key auto_increment,
    comanda_id int, 
    produto_id int,
    quantidade int, 
    observacoes varchar(100), 
    foreign key (comanda_id) references comandas(id_comanda),
    foreign key (produto_id) references produtos(id_prod)
);
