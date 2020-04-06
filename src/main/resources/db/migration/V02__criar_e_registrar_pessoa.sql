 CREATE TABLE person (
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(50) NOT NULL,
	STREET_ADDRESS VARCHAR(30),
	NUMBER_HOUSE VARCHAR(30),
	COMPLEMENT VARCHAR(30),
	DISTRICT VARCHAR(30),
	ZIP_CODE VARCHAR(30),
	CITY VARCHAR(30),
	STATE VARCHAR(30),
	ACTIVE BOOLEAN NOT NULL,
PRIMARY KEY (ID))ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person (NAME, STREET_ADDRESS, NUMBER_HOUSE, COMPLEMENT, DISTRICT, ZIP_CODE, CITY, STATE, ACTIVE) values ('João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-12', 'Uberlândia', 'MG', true);
INSERT INTO person (NAME, STREET_ADDRESS, NUMBER_HOUSE, COMPLEMENT, DISTRICT, ZIP_CODE, CITY, STATE, ACTIVE) values ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-12', 'Ribeirão Preto', 'SP', true);
INSERT INTO person (NAME, STREET_ADDRESS, NUMBER_HOUSE, COMPLEMENT, DISTRICT, ZIP_CODE, CITY, STATE, ACTIVE) values ('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-12', 'Goiânia', 'GO', true);
INSERT INTO person (NAME, STREET_ADDRESS, NUMBER_HOUSE, COMPLEMENT, DISTRICT, ZIP_CODE, CITY, STATE, ACTIVE) values ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA', true);
INSERT INTO person (NAME, STREET_ADDRESS, NUMBER_HOUSE, COMPLEMENT, DISTRICT, ZIP_CODE, CITY, STATE, ACTIVE) values ('Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-12', 'Natal', 'RN', true);
INSERT INTO person (NAME, STREET_ADDRESS, NUMBER_HOUSE, COMPLEMENT, DISTRICT, ZIP_CODE, CITY, STATE, ACTIVE) values ('Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-12', 'Porto Alegre', 'RS', true);
INSERT INTO person (NAME, STREET_ADDRESS, NUMBER_HOUSE, COMPLEMENT, DISTRICT, ZIP_CODE, CITY, STATE, ACTIVE) values ('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ', true);
INSERT INTO person (NAME, STREET_ADDRESS, NUMBER_HOUSE, COMPLEMENT, DISTRICT, ZIP_CODE, CITY, STATE, ACTIVE) values ('Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-12', 'Belo Horizonte', 'MG', true);
INSERT INTO person (NAME, STREET_ADDRESS, NUMBER_HOUSE, COMPLEMENT, DISTRICT, ZIP_CODE, CITY, STATE, ACTIVE) values ('Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG', true);
INSERT INTO person (NAME, STREET_ADDRESS, NUMBER_HOUSE, COMPLEMENT, DISTRICT, ZIP_CODE, CITY, STATE, ACTIVE) values ('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM', true);
