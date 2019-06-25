INSERT INTO PUBLIC.USUARIO (NUMEROSUS,NOME,ENDERECO,SEXO,DATANASCIMENTO,SENHA) VALUES 
('123456','Taciano Amorim','Rua 01','M','1976-01-01','123456');

INSERT INTO PUBLIC.LABORATORIO VALUES  
(1, 'NEO QUIMICA'),
(2, 'EMS SA'),
(3, 'LAFEPE'),
(4, 'HEBRON'),
(5, 'PHAMAMED');

INSERT INTO PUBLIC.UNIDADEFEDERATIVA VALUES 
('PE', 'Pernambuco'),
('BA', 'Bahia'),
('SE', 'Sergipe'),
('SP', 'São Paulo'),
('DF', 'Distrito Federal');

INSERT INTO PUBLIC.UNIDADEATENDIMENTO VALUES
(1, 'PE', 'UPA Sao Jose', 'Rua 01, 44'),
(2, 'PE', 'UPA Carlos correia', 'Rua 37, 42'),
(3, 'PE', 'UPA Pedro Lins', 'Rua mais de um 4445'),
(4, 'SP', 'UPA Murumbi', 'Av. Carlos 123, 3442'),
(5, 'SE', 'UPA Sergipadno', 'Av. garra de pato 123, 3442');

INSERT INTO PUBLIC.SERVIDOR (MATRICULA,UNIDADEATENDIMENTO,NOME,SENHA) VALUES 
('123456',1,'Taciano Servidor','123456');