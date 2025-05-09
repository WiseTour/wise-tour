CREATE DATABASE WiseTour;

USE WiseTour;

CREATE TABLE Unidade_Federativa_Brasil (
sigla CHAR(2) PRIMARY KEY,
unidade_federativa VARCHAR(45) NOT NULL UNIQUE,
regiao VARCHAR(45)
);

INSERT INTO Unidade_Federativa_Brasil (
    sigla,
    unidade_federativa,
    regiao
) VALUES (
    'SP',
    'São Paulo',
    'Sudeste'
);


CREATE TABLE Pais (
id_pais INT PRIMARY KEY AUTO_INCREMENT,
pais VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Fonte_Dados (
id_fonte_dados INT PRIMARY KEY AUTO_INCREMENT,
titulo_arquivo_fonte VARCHAR(255) UNIQUE NOT NULL,
edicao VARCHAR(45) NOT NULL,
orgao_emissor VARCHAR(45) NOT NULL,
url_origem TEXT NOT NULL,
data_coleta DATE NOT NULL,
observacoes TEXT
);

CREATE TABLE Informacao_Contato_Cadastro (
id_informacao_contato_cadastro INT PRIMARY KEY AUTO_INCREMENT,
email VARCHAR(255) NOT NULL,
telefone VARCHAR(11) NOT NULL,
nome VARCHAR(255) NOT NULL,
fidelizado VARCHAR(255) NOT NULL,
CONSTRAINT chk_fidelizado CHECK (fidelizado IN ('Sim', 'Não'))
);

INSERT INTO Informacao_Contato_Cadastro (
    email,
    telefone,
    nome,
    fidelizado
) VALUES (
    'cliente@example.com',
    '11987654321',
    'João Silva',
    'Sim'
);

select * FROM Informacao_Contato_Cadastro;

CREATE TABLE Etapa (
idEtapa INT PRIMARY KEY AUTO_INCREMENT,
etapa VARCHAR(45) NOT NULL,
CONSTRAINT chk_etapa CHECK (etapa IN ('Extração', 'Tratamento', 'Carregamento'))
);

CREATE TABLE Log_Categoria (
id_log_categoria_ETL INT PRIMARY KEY AUTO_INCREMENT,
categoria VARCHAR(45) NOT NULL,
CONSTRAINT chk_categoria CHECK (categoria IN('Erro', 'Aviso', 'Sucesso'))
);


CREATE TABLE Chegada_Turistas_Internacionais_Brasil_Mensal (
id_chegadas_turistas_internacionais_brasil_mensal INT AUTO_INCREMENT,
mes INT NOT NULL,
ano INT NOT NULL,
chegadas INT NOT NULL,
via_acesso VARCHAR(45) NOT NULL,
fk_uf_destino CHAR(2),
fk_fonte_dados INT,
fk_pais_origem INT,
CONSTRAINT FOREIGN KEY (fk_uf_destino) REFERENCES Unidade_Federativa_Brasil (sigla),
CONSTRAINT FOREIGN KEY (fk_fonte_dados) REFERENCES Fonte_Dados (id_fonte_dados),
CONSTRAINT FOREIGN KEY (fk_pais_origem) REFERENCES Pais (id_pais),
PRIMARY KEY (id_chegadas_turistas_internacionais_brasil_mensal, fk_uf_destino, fk_fonte_dados, fk_pais_origem)
);

CREATE TABLE Perfil_Estimado_Turistas (
id_perfil_estimado_turistas INT AUTO_INCREMENT,
fk_pais_origem INT,
fk_uf_entrada CHAR(2),
ano INT NOT NULL,
mes INT,
quantidade_turistas INT NOT NULL,
genero VARCHAR(45),
faixa_etaria VARCHAR(45),
via_acesso VARCHAR(45) NOT NULL,
composicao_grupo_familiar VARCHAR(45),
fonte_informacao_viagem VARCHAR(45),
servico_agencia_turismo INT,
motivo_viagem VARCHAR(45) NOT NULL,
motivacao_viagem_lazer VARCHAR(45),
gasto_media_percapita_em_reais DOUBLE NOT NULL,
CONSTRAINT FOREIGN KEY (fk_pais_origem) REFERENCES Pais (id_pais),
CONSTRAINT FOREIGN KEY (fk_uf_entrada) REFERENCES Unidade_Federativa_Brasil (sigla),
PRIMARY KEY (id_perfil_estimado_turistas, fk_pais_origem, fk_uf_entrada)
);

CREATE TABLE Perfil_Estimado_Turista_Fonte (
fk_fonte INT,
fk_perfil_estimado_turistas INT,
fk_pais_origem INT,
CONSTRAINT FOREIGN KEY (fk_fonte) REFERENCES Fonte_Dados (id_fonte_dados),
CONSTRAINT FOREIGN KEY (fk_perfil_estimado_turistas) REFERENCES Perfil_Estimado_Turistas (id_perfil_estimado_turistas),
CONSTRAINT FOREIGN KEY (fk_pais_origem) REFERENCES Pais (id_pais),
PRIMARY KEY (fk_fonte, fk_perfil_estimado_turistas, fk_pais_origem)
);

CREATE TABLE Destinos (
fk_perfil_estimado_turistas INT,
fk_pais_origem INT,
fk_uf_destino CHAR(2),
permanencia_media DOUBLE NOT NULL,
CONSTRAINT FOREIGN KEY (fk_perfil_estimado_turistas) REFERENCES Perfil_Estimado_Turistas (id_perfil_estimado_turistas),
CONSTRAINT FOREIGN KEY (fk_pais_origem) REFERENCES Pais (id_pais),
CONSTRAINT FOREIGN KEY (fk_uf_destino) REFERENCES Unidade_Federativa_Brasil (sigla),
PRIMARY KEY (fk_perfil_estimado_turistas, fk_pais_origem, fk_uf_destino)
);

CREATE TABLE Empresa (
cnpj CHAR(14),
nome_fantasia VARCHAR(255) NOT NULL,
razao_social VARCHAR(255) NOT NULL,
fk_informacao_contato_cadastro INT,
cep CHAR(8) NOT NULL,
tipo_logradouro VARCHAR(45) NOT NULL,
nome_logradouro VARCHAR(45) NOT NULL,
numero INT NOT NULL,
complemento TEXT NULL,
bairro VARCHAR(100) NOT NULL,
cidade VARCHAR(100) NOT NULL,
fk_uf_sigla CHAR(2),
CONSTRAINT FOREIGN KEY (fk_informacao_contato_cadastro) REFERENCES Informacao_Contato_Cadastro (id_informacao_contato_cadastro),
CONSTRAINT FOREIGN KEY (fk_uf_sigla) REFERENCES Unidade_Federativa_Brasil (sigla),
PRIMARY KEY (cnpj, fk_informacao_contato_cadastro, fk_uf_sigla)
);

CREATE TABLE Funcionario (
id_funcionario INT AUTO_INCREMENT,
nome VARCHAR(255) NOT NULL,
cargo VARCHAR(70) NOT NULL,
telefone VARCHAR(11) NOT NULL,
fk_cnpj CHAR(14) NOT NULL,
fk_informacao_contato_cadastro INT,
fk_uf_sigla CHAR(2),
CONSTRAINT FOREIGN KEY (fk_cnpj) REFERENCES Empresa (cnpj),
CONSTRAINT FOREIGN KEY (fk_informacao_contato_cadastro) REFERENCES Informacao_Contato_Cadastro (id_informacao_contato_cadastro),
CONSTRAINT FOREIGN KEY (fk_uf_sigla) REFERENCES Unidade_Federativa_Brasil (sigla),
PRIMARY KEY (id_funcionario, fk_cnpj, fk_informacao_contato_cadastro, fk_uf_sigla)
);

CREATE TABLE Usuario (
id_usuario INT PRIMARY KEY AUTO_INCREMENT,
fk_funcionario INT,
email VARCHAR(255) NOT NULL,
senha CHAR(12) NOT NULL,
permissao VARCHAR(45) NOT NULL,
CONSTRAINT chk_permissao CHECK (permissao IN ('Admin', 'Padrão')),
CONSTRAINT FOREIGN KEY (fk_funcionario) REFERENCES Funcionario (id_funcionario)
);

CREATE TABLE Historico_Contato (
id_historico_contato INT PRIMARY KEY AUTO_INCREMENT,
data_contato DATE NOT NULL,
anotacoes TEXT NOT NULL,
responsavel VARCHAR(255) NOT NULL,
fk_informacao_contato_cadastro INT,
CONSTRAINT FOREIGN KEY (fk_informacao_contato_cadastro) REFERENCES Informacao_Contato_Cadastro (id_informacao_contato_cadastro)
);

CREATE TABLE Log (
id_log INT AUTO_INCREMENT,
fk_fonte INT,
fk_log_categoria INT,
fk_etapa INT,
mensagem TEXT NOT NULL,
data_hora DATETIME NOT NULL,
quantidade_lida INT NULL,
quantidade_inserida INT NULL,
tabela_destino VARCHAR(45) NULL,
CONSTRAINT FOREIGN KEY (fk_fonte) REFERENCES Fonte_Dados (id_fonte_dados),
CONSTRAINT FOREIGN KEY (fk_log_categoria) REFERENCES Log_Categoria (id_log_categoria_ETL),
CONSTRAINT FOREIGN KEY (fk_etapa) REFERENCES Etapa (idEtapa),
PRIMARY KEY (id_log, fk_fonte, fk_log_categoria, fk_etapa)
);

CREATE TABLE Configuracao_Slack (
id_configuracao_slack INT AUTO_INCREMENT,
fk_usuario INT,
slack_user_id VARCHAR(45) NULL,
slack_username VARCHAR(255) NULL,
canal_padrao VARCHAR(255) NULL,
ativo CHAR(3) NOT NULL,
CONSTRAINT FOREIGN KEY (fk_usuario) REFERENCES Usuario (id_usuario),
CONSTRAINT chk_ativo CHECK (ativo IN ('Sim', 'Não')),
PRIMARY KEY (id_configuracao_slack, fk_usuario)
);

CREATE TABLE Tipo_notificacao_dados (
fk_log_categoria_ETL INT,
fk_configuracao_slack INT,
fk_usuario INT,
CONSTRAINT FOREIGN KEY (fk_log_categoria_ETL) REFERENCES Log_Categoria (id_log_categoria_ETL),
CONSTRAINT FOREIGN KEY (fk_configuracao_slack) REFERENCES Configuracao_Slack (id_configuracao_slack),
CONSTRAINT FOREIGN KEY (fk_usuario) REFERENCES Usuario (id_usuario),
PRIMARY KEY (fk_log_categoria_ETL, fk_configuracao_slack, fk_usuario)
);

CREATE TABLE Preferencias_Visualizacao_Dashboard (
id_Preferencias_Visualizacao_Dashboard INT AUTO_INCREMENT,
fk_usuario INT,
perfil_turista_ativo CHAR(3) NOT NULL,
temporadas_ativo CHAR(3) NOT NULL,
oportunidades_investimento_marketing_ativo CHAR(3) NOT NULL,
CONSTRAINT FOREIGN KEY (fk_usuario) REFERENCES Usuario (id_usuario),
CONSTRAINT chk_ativo_perfil_turista CHECK (perfil_turista_ativo IN ('Sim', 'Não')),
CONSTRAINT chk_ativo_temporadas CHECK (temporadas_ativo IN ('Sim', 'Não')),
CONSTRAINT chk_ativo_oportunidades CHECK (oportunidades_investimento_marketing_ativo IN ('Sim', 'Não')),
PRIMARY KEY (id_Preferencias_Visualizacao_Dashboard, fk_usuario)
);