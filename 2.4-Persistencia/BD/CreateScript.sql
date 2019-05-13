/* CREATE TABLE */

CREATE TABLE Client
(
    id int(6) auto_increment primary key,
    nif char(9) not null,
    nom varchar(40) not null,
    cognom1 varchar(20),
    cognom2 varchar(20)
    
);

CREATE TABLE Tipus_Passi_Express
(
    nom varchar(40) primary key,
    preu_per_dia DECIMAL(5,2) not null

);

CREATE TABLE Passi_Express
(
    id int(6) auto_increment primary key,
    client int(6) not null,
    tipus varchar(40) not null,
    data Date not null,
    FOREIGN KEY (client) REFERENCES Client(id),
    FOREIGN KEY (tipus) REFERENCES Tipus_Passi_Express(nom)

);

CREATE TABLE Entrada
(
    id int(6) auto_increment primary key,
    client int(6) not null,
    data Date not null,
    dies_valiesa int(1),
    preu Decimal(5,2)  not null,
    categoria ENUM('ADULT','SENIOR','DISCAPACITAT'),
    FOREIGN KEY (client) REFERENCES Client(id)
);

CREATE TABLE Parc
(
    codi int(6) primary key,
    nom varchar(40) not null,
    url_foto varchar(200) not null
    
);

CREATE TABLE Zona
(
    parc int(6) not null,
    numero int(2) not null,
    nom varchar(40) not null,
    PRIMARY KEY(parc,numero),
    FOREIGN KEY (parc) REFERENCES Parc(codi)
);

CREATE TABLE Atraccio
(
    codi int(3) primary key,
    parc int(6) not null,
    zona int(6) not null,
    capacitat_maxima_ronda int(3) not null,
    descripcio_html varchar(400) not null,
    nom varchar(40) not null,
    temps_per_ronda int(3) not null,
    url_foto varchar(200) not null,
    clients_en_cua int(3) not null,
    alsada_minima_amb_acompanyant int(2) not null,
    alsada_minima int(2) not null,
    estat_operatiu ENUM('OPERATIVA','AVARIADA','TANCADA','ATURADA_TEMPORALMENT'),
    FOREIGN KEY (parc,zona) REFERENCES Zona(parc,numero)
    
);

CREATE TABLE Incidencia
(
    atraccio int(3),
    num int(6),
    data_fi Date,
    data_inici Date not null,
    missatge_estat varchar(200),
    data_fi_prevista Date not null,
    PRIMARY KEY (atraccio,num),
    FOREIGN KEY (atraccio) REFERENCES Atraccio(codi)
    
);

CREATE TABLE Preus
(
    parc1_id int(1) primary key,
    parc2_id int(1),
    parc3_id int(1),
    num_dies int(2) not null,
    preu_adult numeric(10,2) not null,
    preu_nen_senior numeric(10,2) not null,
    preu_discapacitat numeric(10,2) not null

);

CREATE TABLE Entrada_Parc
(
    entrada int(6),
    parc int(6),
    PRIMARY KEY (entrada,parc),
    FOREIGN KEY (entrada) REFERENCES Entrada(id),
    FOREIGN KEY (parc) REFERENCES Parc(codi)
);

CREATE TABLE Info_Utilitzacio
(
    passi int(6),
    atraccio int(3),
    numero_usos int(3) not null,
    tipus_passi ENUM('UN_SOL_US','ILIMITAT','UN_SOL_US_1AFILA','ILIMITAT_I_UN_SOL_US_1AFILA'),
    PRIMARY KEY (passi,atraccio),
    FOREIGN KEY (passi) REFERENCES Passi_Express(id),
    FOREIGN KEY (atraccio) REFERENCES Atraccio(codi)
);

CREATE TABLE Tipus_Passi_Atraccio
(
    tipus_passi_express int(6),
    atraccio int(3),
    tipus_passi ENUM('UN_SOL_US','ILIMITAT','UN_SOL_US_1AFILA','ILIMITAT_I_UN_SOL_US_1AFILA'),
    PRIMARY KEY (tipus_passi_express,atraccio),
    FOREIGN KEY (tipus_passi_express) REFERENCES Passi_Express(id),
    FOREIGN KEY (atraccio) REFERENCES Atraccio(codi)

);