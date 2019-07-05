INSERT INTO UnidadeFederativa (Sigla, Nome) VALUES 
('PE', 'Pernambuco'),
('SE', 'Sergipe'),
('SP', 'São Paulo'),
('RJ', 'Rio de Janeiro'),
('MG', 'Minas Gerais');

INSERT INTO UnidadeAtendimento (unidadeFederativa, Nome, endereco) VALUES
 ('PE', 'UPA Norte', 'Rua cremente, 30'),
 ('PE', 'UPA Sul', 'Rua jose maria, 320'),
 ('PE', 'UPA Caxanga', 'Rua ultima, 130'),
 ('RJ', 'UPA Castro', 'Rua do castro, 30');

INSERT INTO Laboratorio (Nome) VALUES 
('Ache'),
('Ultrapharma');

INSERT INTO Vacina (nome, prescricao) VALUES 
('Sarampo', 'Sarampo prescricao'),
('HN1', 'HN1 prescricao'),
('Tripiceviral', 'Tripiceviral prescricao');

INSERT INTO Lote (vacina, laboratorio, numero, qtdeDose, dataVencimento, valor) VALUES 
(1, 1, '20NE', 30000, '2025-01-01', 205.93),
(1, 2, '20NE', 15000, '2025-01-01', 205.93),
(2, 2, '20NE', 90000, '2025-01-01', 205.93),
(1, 3, '20NE', 10000, '2025-01-01', 205.93);

INSERT INTO Servidor (UnidadeAtendimento, Nome, senha) VALUES
 ('2468', 1, 'Mario Henrique', 'Rua 01, 39', '246'),
 ('1357', 2, 'Carla Jose', 'Rua 02, 39', '135');
 
INSERT INTO Estoque (UnidadeAtendimento, Lote, quantidadeDoses) VALUES
 (1, 1, 1000),
 (1, 2, 1000),
 (1, 3, 1000),
 (2, 1, 1000),
 (2, 2, 1000),
 (3, 1, 1000);
 
INSERT INTO Usuario (numeoSUS, Nome, endereco, sexo, dataNascimento, senha) VALUES
 ('2468', 'Marcos Alencar Junior', 'Rua 01, 39', 'M', '1980-01-01', '246'),
 ('1357', 'Maria da Silva', 'Rua 02, 39', 'F', '1990-01-01', '135');

INSERT INTO Servidor (UnidadeAtendimento, Nome, senha) VALUES
 ('2468', 1, 'Mario Henrique', 'Rua 01, 39', '246'),
 ('1357', 2, 'Carla Jose', 'Rua 02, 39', '135');
 
INSERT INTO Servidor (UnidadeAtendimento, Nome, senha) VALUES
 ('2468', 1, 'Mario Henrique', 'Rua 01, 39', '246'),
 ('1357', 2, 'Carla Jose', 'Rua 02, 39', '135');
 

 
