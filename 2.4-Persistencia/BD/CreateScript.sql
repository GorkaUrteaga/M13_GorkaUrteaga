/* CREATE TABLE */

CREATE TABLE Client
(
    id int(6) primary key,
    nif char(9) not null,
    nom varchar(40) not null,
    cognom1 varchar(20),
    cognom2 varchar(20),
    password varchar(200) not null
);

CREATE TABLE Tipus_Passi_Express
(
    id int(6) primary key,
    nom varchar(40),
    preu_per_dia DECIMAL(5,2) not null,
    arxivat boolean not null
    
);

CREATE TABLE Passi_Express
(
    id int(6) primary key,
    client int(6) not null,
    tipus int(6) not null,
    data Date not null,
    FOREIGN KEY (client) REFERENCES Client(id),
    FOREIGN KEY (tipus) REFERENCES Tipus_Passi_Express(id)

);

CREATE TABLE Entrada
(
    id int(6) primary key,
    client int(6),
    data Date not null,
    dies_valiesa int(1) not null,
    preu Decimal(5,2)  not null,
    categoria ENUM('ADULT','SENIOR','DISCAPACITAT'),
    FOREIGN KEY (client) REFERENCES Client(id)
);

CREATE TABLE Parc
(
    codi int(6) primary key,
    nom varchar(40) not null,
    url_foto varchar(500) not null
    
);

CREATE TABLE Zona
(
    numero int(2) primary key,
    parc int(6) not null,
    nom varchar(40) not null,
    FOREIGN KEY (parc) REFERENCES Parc(codi)
);

CREATE TABLE Atraccio
(
    codi int(3) primary key,
    zona int(2) not null,
    capacitat_maxima_ronda int(3) not null,
    descripcio_html varchar(400),
    nom varchar(40) not null,
    temps_per_ronda int(3) not null,
    url_foto varchar(500) not null,
    clients_en_cua int(3) not null,
    alsada_minima_amb_acompanyant int(3) not null,
    alsada_minima int(3) not null,
    estat_operatiu ENUM('OPERATIVA','AVARIADA','TANCADA','ATURADA_TEMPORALMENT') not null,
    incidencia int(6),
    FOREIGN KEY (zona) REFERENCES Zona(numero)
    
);

CREATE TABLE Incidencia
(
    num int(6) primary key,
    atraccio int(3) not null,
    data_fi Datetime,
    data_inici Datetime not null,
    missatge_estat varchar(200),
    data_fi_prevista Datetime not null,
    FOREIGN KEY (atraccio) REFERENCES Atraccio(codi)
    
);

ALTER TABLE Atraccio
ADD CONSTRAINT FK_Atraccio_Incidencia FOREIGN KEY (incidencia) REFERENCES Incidencia(num); 

CREATE TABLE Preus
(
    id int(2) primary key,
    parc1_id int(1) not null,
    parc2_id int(1) not null,
    parc3_id int(1) not null,
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

CREATE TABLE Tipus_Acces
(
    id int(1) primary key,
    tipus varchar(30) not null
);

CREATE TABLE Info_Utilitzacio
(
    passi int(6),
    atraccio int(3),
    numero_usos int(3) not null,
    tipus_acces int(1) not null,
    PRIMARY KEY (passi,atraccio),
    FOREIGN KEY (passi) REFERENCES Passi_Express(id),
    FOREIGN KEY (atraccio) REFERENCES Atraccio(codi),
    FOREIGN KEY (tipus_acces) REFERENCES Tipus_Acces(id)
);

CREATE TABLE Tipus_Passi_Atraccio
(
    tipus_passi_express int(6),
    atraccio int(3),
    tipus_acces int(1) not null,
    PRIMARY KEY (tipus_passi_express,atraccio),
    FOREIGN KEY (tipus_passi_express) REFERENCES Tipus_Passi_Express(id),
    FOREIGN KEY (atraccio) REFERENCES Atraccio(codi),
    FOREIGN KEY (tipus_acces) REFERENCES Tipus_Acces(id)
);