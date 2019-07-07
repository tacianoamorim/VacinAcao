CREATE TABLE Usuario (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,numeroSUS                      VARCHAR(20) NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,endereco                       VARCHAR(100) NOT NULL
 ,sexo                           CHAR(1) NOT NULL
 ,dataNascimento                 DATE NOT NULL
 ,senha                          VARCHAR(512) NOT NULL
 ,PRIMARY KEY (id)
);

CREATE TABLE Vacina (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,prescricao                     VARCHAR(512) NOT NULL
 ,PRIMARY KEY (id)
);

CREATE TABLE Laboratorio (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,PRIMARY KEY (id)
);

CREATE TABLE UnidadeAtendimento (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,unidadeFederativa              CHAR(2) NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,endereco                       VARCHAR(100) NOT NULL
 ,PRIMARY KEY (id)
);

CREATE INDEX FK_UNIDADEATENDIMENTO_UNIDADEFEDERATIVA ON UnidadeAtendimento (unidadeFederativa);

CREATE TABLE UnidadeFederativa (
  sigla                          CHAR(2) NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,PRIMARY KEY (sigla)
);

CREATE TABLE Lote (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,vacina                         INTEGER UNSIGNED NOT NULL
 ,laboratorio                    INTEGER UNSIGNED NOT NULL
 ,numero                         VARCHAR(20) NOT NULL
 ,quantidadeDose                 INTEGER UNSIGNED NOT NULL
 ,dataVencimento                 DATE NOT NULL
 ,valor                          DOUBLE NOT NULL
 ,PRIMARY KEY (id)
);

CREATE INDEX FK_LOTE_LABORATORIO ON Lote (laboratorio);

CREATE INDEX FK_LOTE_VACINA ON Lote (vacina);

CREATE TABLE LoteUnidadeFederativa (
  lote                           INTEGER UNSIGNED NOT NULL
 ,unidadeFederativa              CHAR(2) NOT NULL
 ,dataDistribuicao               DATE NULL
 ,PRIMARY KEY (lote, unidadeFederativa)
);

CREATE INDEX FK_LOTEUNIDADEFEDERATIVA_LOTE ON LoteUnidadeFederativa (lote);

CREATE INDEX FK_LOTEUNIDADEFEDERATIVA_UNIDADEFEDERATIVA ON LoteUnidadeFederativa (unidadeFederativa);

CREATE TABLE Estoque (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,unidadeAtendimento             INTEGER UNSIGNED NOT NULL
 ,lote                           INTEGER UNSIGNED NOT NULL
 ,quantidadeDoses                INTEGER UNSIGNED NOT NULL
 ,PRIMARY KEY (id)
);

CREATE INDEX FK_ESTOQUE_LOTE ON Estoque (lote);

CREATE INDEX FK_ESTOQUE_UNIDADEFEDERATIVA ON Estoque (unidadeAtendimento);

CREATE TABLE Campanha (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,vacina                         INTEGER UNSIGNED NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,valorTotal                     DOUBLE NOT NULL
 ,dataInicioDivulgacao           DATE NOT NULL
 ,dataFimDivulgacao              DATE NOT NULL
 ,dataInicioExecucao             DATE NOT NULL
 ,dataFimExecucao                DATE NOT NULL
 ,observacao                     VARCHAR(512) NOT NULL
 ,PRIMARY KEY (id)
);

CREATE INDEX FK_CAMPANHA_VACINA ON Campanha (vacina);

CREATE TABLE Servidor (
  matricula                      INTEGER UNSIGNED NOT NULL
 ,unidadeAtendimento             INTEGER UNSIGNED NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,senha                          VARCHAR(512) NOT NULL
 ,PRIMARY KEY (matricula)
);

CREATE INDEX FK_SERVIDOR_UNIDADEATENDIMENTO ON Servidor (unidadeAtendimento);

CREATE TABLE Vacinacao (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,servidor                       INTEGER UNSIGNED NOT NULL
 ,usuario                        INTEGER UNSIGNED NOT NULL
 ,Estoque_id                     INTEGER UNSIGNED NOT NULL
 ,dataAplicacao                  DATE NOT NULL
 ,PRIMARY KEY (id)
);

CREATE INDEX FK_VACINACAO_USUARIO ON Vacinacao (usuario);

CREATE INDEX FK_VACINACAO_SERVIDOR ON Vacinacao (servidor);

ALTER TABLE UnidadeAtendimento
  ADD CONSTRAINT Rel_UnidadeFederativa_UnidadeAtendimento FOREIGN KEY (unidadeFederativa)
    REFERENCES UnidadeFederativa (sigla)
;
ALTER TABLE Lote
  ADD CONSTRAINT Rel_Laboratorio_Lote FOREIGN KEY (laboratorio)
    REFERENCES Laboratorio (id)
;
ALTER TABLE Lote
  ADD CONSTRAINT Rel_Vacina_Lote FOREIGN KEY (vacina)
    REFERENCES Vacina (id)
;
ALTER TABLE LoteUnidadeFederativa
  ADD CONSTRAINT Rel_Lote_LoteUnidadeFederativa FOREIGN KEY (lote)
    REFERENCES Lote (id)
;
ALTER TABLE LoteUnidadeFederativa
  ADD CONSTRAINT Rel_UnidadeFederativa_LoteUnidadeFederativa FOREIGN KEY (unidadeFederativa)
    REFERENCES UnidadeFederativa (sigla)
;
ALTER TABLE Estoque
  ADD CONSTRAINT Rel_Lote_Estoque FOREIGN KEY (lote)
    REFERENCES Lote (id)
;
ALTER TABLE Estoque
  ADD CONSTRAINT Rel_UnidadeAtendimento_estoque FOREIGN KEY (unidadeAtendimento)
    REFERENCES UnidadeAtendimento (id)
;
ALTER TABLE Campanha
  ADD CONSTRAINT Rel_Vacina_Campanha FOREIGN KEY (vacina)
    REFERENCES Vacina (id)
;
ALTER TABLE Servidor
  ADD CONSTRAINT Rel_UnidadeAtendimento_Servidor FOREIGN KEY (unidadeAtendimento)
    REFERENCES UnidadeAtendimento (id)
;
ALTER TABLE Vacinacao
  ADD CONSTRAINT Rel_Usuario_Vacinacao FOREIGN KEY (usuario)
    REFERENCES Usuario (id)
;
ALTER TABLE Vacinacao
  ADD CONSTRAINT Rel_Servidor_Vacinacao FOREIGN KEY (servidor)
    REFERENCES Servidor (matricula)
;
ALTER TABLE Vacinacao
  ADD CONSTRAINT Rel_15 FOREIGN KEY (Estoque_id)
    REFERENCES Estoque (id)
;