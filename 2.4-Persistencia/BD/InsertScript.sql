/* INSERT DATA */
/*
INSERT INTO Client
VALUES(1,'47129014J','Gorka','Urteaga','Jaimez');
INSERT INTO Client (id,nif,nom,cognom1)
VALUES(2,'47999999K','Quim','Marsal');
*/

/* PARCS */

USE m2_gurteaga;

INSERT INTO Parc
VALUES(1,'PortAventura Park','https://s3-eu-west-1.amazonaws.com/portaventura-world-production-files/wilson_cms/images/images/000/001/758/small_square/CCPA_0112.jpg');

INSERT INTO Parc
VALUES(2,'PortAventura Caribe','https://s3-eu-west-1.amazonaws.com/portaventura-world-production-files/wilson_cms/images/images/000/000/660/original/Generales-CCAP_0788.jpg');

INSERT INTO Parc
VALUES(3,'Ferrari Land','https://www.cityticketshotels.com/wp-content/uploads/default-705.jpg');


/* ZONES */

INSERT INTO Zona
VALUES(1,1,'Far Werst');

INSERT INTO Zona
VALUES(2,1,'Mexico');

INSERT INTO Zona
VALUES(3,1,'Mediterrania');

INSERT INTO Zona
VALUES(4,1,'Polynesia');

/* ATRACCIONS */

INSERT INTO Atraccio
VALUES(1,1,10,'descripHTML','Buffalo Rodeo',2,'https://s3-eu-west-1.amazonaws.com/portaventura-world-production-files/wilson_cms/images/images/000/000/725/landscape_small/PA21707i_LR.jpg',200,140,150,'OPERATIVA',null);

INSERT INTO Atraccio
VALUES(2,1,20,'descripHTML','Hurakan Condor',1,'https://s3-eu-west-1.amazonaws.com/portaventura-world-production-files/wilson_cms/images/images/000/000/496/landscape_small/Generales-M%C3%A9xico_0713.jpg',150,155,155,'ATURADA_TEMPORALMENT',null);

INSERT INTO Atraccio
VALUES(3,1,15,'descripHTML','Tren de la Mina',3,'https://s3-eu-west-1.amazonaws.com/portaventura-world-production-files/wilson_cms/images/images/000/001/765/landscape_small/PAP-MEX_ATR_DIAB_ONR_0615_010.jpg',320,140,145,'ATURADA_TEMPORALMENT',null);

/* INCIDENCIES */

INSERT INTO Incidencia
VALUES(1,1,NOW(), NOW(),'Falta un cargol com sempre...', NOW());

INSERT INTO Incidencia
VALUES(2,2,null, NOW(),'Netejar vomit...', NOW());

UPDATE Atraccio
set incidencia = 2
where codi = 2;

/* TIPUS ACCES */

INSERT INTO Tipus_Acces
VALUES(1,'UN_SOL_US');

INSERT INTO Tipus_Acces
VALUES(2,'ILIMITAT');

INSERT INTO Tipus_Acces
VALUES(3,'UN_SOL_US_1aFila');

INSERT INTO Tipus_Acces
VALUES(4,'ILIMITAT_I_UN_SOL_US_1aFILA');

/* TIPUS PASSI EXPRESS */
INSERT INTO Tipus_Passi_Express
VALUES (1,'GOLD',30,false);

INSERT INTO Tipus_Passi_Express
VALUES (2,'PLATINUM',50,false);

/* TIPUS PASSI ATRACCIO */

INSERT INTO Tipus_Passi_Atraccio
VALUES (1,1,1);

INSERT INTO Tipus_Passi_Atraccio
VALUES (1,2,2);

INSERT INTO Tipus_Passi_Atraccio
VALUES (1,3,2);

INSERT INTO Tipus_Passi_Atraccio
VALUES (2,1,3);

INSERT INTO Tipus_Passi_Atraccio
VALUES (2,2,4);

INSERT INTO Tipus_Passi_Atraccio
VALUES (2,3,2);

/* PREUS */

-- 1 DIA 1 PARC
INSERT INTO Preus
VALUES(1,1,0,0,1,50,44,25);
INSERT INTO Preus
VALUES(2,2,0,0,1,50,44,25);
INSERT INTO Preus
VALUES(3,3,0,0,1,50,44,25);

-- 1 DIA 2 PARCS
INSERT INTO Preus
VALUES(4,1,2,0,1,57,50,29);
INSERT INTO Preus
VALUES(5,1,3,0,1,57,50,29);
INSERT INTO Preus
VALUES(6,2,3,0,1,57,50,29);

-- 1 DIA 3 PARCS
INSERT INTO Preus
VALUES(7,1,2,3,1,61,54,33);

-- 2 DIES 1 PARC
INSERT INTO Preus
VALUES(8,1,0,0,2,57,50,29);
INSERT INTO Preus
VALUES(9,2,0,0,2,57,50,29);
INSERT INTO Preus
VALUES(10,3,0,0,2,57,50,29);



-- 2 DIES 2 PARCS
INSERT INTO Preus
VALUES(11,1,2,0,2,70,61,35);
INSERT INTO Preus
VALUES(12,1,3,0,2,70,61,35);
INSERT INTO Preus
VALUES(13,2,3,0,2,70,61,35);

-- 2 DIES 3 PARCS
INSERT INTO Preus
VALUES(14,1,2,3,2,75,66,40);

-- 3 DIES 1 PARC
INSERT INTO Preus
VALUES(15,1,0,0,3,61,54,33);
INSERT INTO Preus
VALUES(16,2,0,0,3,61,54,33);
INSERT INTO Preus
VALUES(17,3,0,0,3,61,54,33);

-- 3 DIES 2 PARCS
INSERT INTO Preus
VALUES(18,1,2,0,3,75,66,40);
INSERT INTO Preus
VALUES(19,1,3,0,3,75,66,40);
INSERT INTO Preus
VALUES(20,2,3,0,3,75,66,40);

-- 3 DIES 3 PARCS

INSERT INTO Preus
VALUES(21,1,2,3,3,95,83,48);