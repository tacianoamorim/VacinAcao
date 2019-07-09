INSERT INTO PUBLIC.Laboratorio (Nome) VALUES  
 ('NEO QUIMICA'),
 ('EMS SA'),
 ('LAFEPE'),
 ('HEBRON'),
 ('Ache'),
 ('Ultrapharma');

INSERT INTO PUBLIC.Vacina (nome, prescricao) VALUES 
 ('Sarampo', 'Sarampo prescricao'),
 ('HN1', 'HN1 prescricao'),
 ('Tripiceviral', 'Tripiceviral prescricao');

INSERT INTO PUBLIC.Lote (vacina, laboratorio, numero, qtdeDose, dataVencimento, valor) VALUES 
 (1, 1, '2089RD', 30000, '2025-01-01', 205.93),
 (1, 2, '3847NE', 15000, '2025-01-01', 2204.23),
 (2, 2, '7818PED', 90000, '2025-01-01', 3231.24),
 (3, 3, '20394EP', 10000, '2025-01-01', 2123.72);

INSERT INTO PUBLIC.UnidadeFederativa (Sigla, Nome) VALUES 
 ('PE', 'Pernambuco'),
 ('SE', 'Sergipe'),
 ('SP', 'São Paulo'),
 ('BA', 'Bahia'),
 ('RJ', 'Rio de Janeiro'),
 ('MG', 'Minas Gerais'),
 ('DF', 'Distrito Federal');

INSERT INTO PUBLIC.UnidadeAtendimento (unidadeFederativa, Nome, endereco) VALUES 
 ('PE', 'UPA Sao Jose', 'Rua 01, 44'),
 ('PE', 'UPA Carlos correia', 'Rua 37, 42'),
 ('SP', 'UPA Pedro Lins', 'Rua mais de um 4445'),
 ('SP', 'UPA Murumbi', 'Av. Carlos 123, 3442'),
 ('SE', 'UPA Sergipadno', 'Av. garra de pato 123, 3442'),
 ('PE', 'UPA Norte', 'Rua cremente, 30'),
 ('PE', 'UPA Sul', 'Rua jose maria, 320'),
 ('PE', 'UPA Caxanga', 'Rua ultima, 130'),
 ('RJ', 'UPA Castro', 'Rua do castro, 30');

INSERT INTO PUBLIC.Estoque (unidadeAtendimento, lote, quantidadeDoses) VALUES 
 (1, 1, 100),
 (1, 3, 100),
 (1, 4, 200),
 (2, 2, 200),
 (3, 2, 100),
 (4, 2, 200),
 (5, 3, 200),
 (6, 2, 100),
 (7, 3, 200);
 
INSERT INTO PUBLIC.Usuario (numeroSUS, Nome, endereco, sexo, dataNascimento, senha) VALUES
 ('2468', 'Marcos Alencar Junior', 'Rua 01, 39', 'M', '1980-01-01', '246'),
 ('1357', 'Maria da Silva', 'Rua 02, 39', 'F', '1990-01-01', '135');

INSERT INTO PUBLIC.Servidor (matricula, UnidadeAtendimento, Nome, senha) VALUES
 ('2468', 1, 'Mario Henrique', '246'),
 ('1357', 2, 'Carla Jose', '135');
 
 