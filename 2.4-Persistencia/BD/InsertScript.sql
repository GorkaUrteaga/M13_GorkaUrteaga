/* INSERT DATA */
/*
INSERT INTO Client
VALUES(1,'47129014J','Gorka','Urteaga','Jaimez');
INSERT INTO Client (id,nif,nom,cognom1)
VALUES(2,'47999999K','Quim','Marsal');
*/

/* PARCS */

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
VALUES(1,2,'Mexico');

INSERT INTO Zona
VALUES(1,3,'Mediterrania');

INSERT INTO Zona
VALUES(1,4,'Polynesia');

/* ATRACCIONS */

INSERT INTO Atraccio
VALUES(1,1,1,10,null,'Buffalo Rodeo',5,'https://s3-eu-west-1.amazonaws.com/portaventura-world-production-files/wilson_cms/images/images/000/000/722/medium/PA21703i_LR.jpg',10,140,150,'OPERATIVA');