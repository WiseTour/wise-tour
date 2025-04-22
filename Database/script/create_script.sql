CREATE DATABASE WiseTour;

USE WiseTour;

CREATE TABLE Etapa (
idEtapa INT PRIMARY KEY AUTO_INCREMENT,
etapa VARCHAR(45),
CONSTRAINT chk_etapa CHECK (etapa IN ('Extração', 'Tratamento', 'Carregamento'))
);

CREATE TABLE Log_Categoria (
id_log_categoria_ETL INT PRIMARY KEY AUTO_INCREMENT,
categoria VARCHAR(45),
CONSTRAINT chk_categoria CHECK (categoria IN('Erro', 'Aviso', 'Sucesso'))
);

CREATE TABLE Fonte (
id_fonte INT PRIMARY KEY AUTO_INCREMENT,
titulo_arquivo_fonte VARCHAR(255) UNIQUE,
edicao VARCHAR(45),
orgao_emissor VARCHAR(45),
url_origem TEXT,
data_coleta DATE,
observacoes TEXT
);

CREATE TABLE Pais (
idPais INT PRIMARY KEY AUTO_INCREMENT,
pais VARCHAR(100)
);

CREATE TABLE Continente (
idContinente INT PRIMARY KEY AUTO_INCREMENT,
continente VARCHAR(45) UNIQUE,
CONSTRAINT chk_continente CHECK (continente IN ('América', 'Oceania', 'Ásia', 'África', 'Europa'))
);

CREATE TABLE Unidade_Federativa_Brasil (
sigla CHAR(2) PRIMARY KEY,
unidade_federativa VARCHAR(45) UNIQUE,
regiao VARCHAR(45),
CONSTRAINT chk_regiao CHECK (regiao IN ('Norte', 'Sul', 'Sudeste', 'Centro-Oeste', 'Nordeste'))
);

CREATE TABLE Informacao_Contato_Cadastro (
id_informacao_contato_cadastro INT PRIMARY KEY AUTO_INCREMENT,
email VARCHAR(255),
telefone VARCHAR(11),
nome VARCHAR(255),
fidelizado VARCHAR(255),
CONSTRAINT chk_fidelizado CHECK (fidelizado IN ('Sim', 'Não'))
);

CREATE TABLE Usuario (
id_usuario INT PRIMARY KEY AUTO_INCREMENT,
email VARCHAR(255),
senha CHAR(12),
permissao VARCHAR(45),
CONSTRAINT chk_permissao CHECK (permissao IN ('Admin', 'Padrão'))
);

CREATE TABLE Log (
id_log INT PRIMARY KEY AUTO_INCREMENT,
fk_fonte INT,
fk_log_categoria INT,
fk_etapa INT,
mensagem TEXT,
data_hora DATETIME,
quantidade_lida INT,
quantidade_inserida INT,
tabela_destino VARCHAR(45),
CONSTRAINT FOREIGN KEY (fk_fonte) REFERENCES Fonte (id_fonte),
CONSTRAINT FOREIGN KEY (fk_log_categoria) REFERENCES Log_Categoria (id_log_categoria_ETL),
CONSTRAINT FOREIGN KEY (fk_etapa) REFERENCES Etapa (idEtapa)
);

CREATE TABLE Pais_Continente (
fk_continente INT,
fk_pais INT,
CONSTRAINT FOREIGN KEY (fk_continente) REFERENCES Continente (idContinente),
CONSTRAINT FOREIGN KEY (fk_pais) REFERENCES Pais (idPais)
);

CREATE TABLE Chegadas_Turistas_Internacionais_Brasil_Mensal (
id_chegadas_turistas_internacionais_brasil_mensal INT PRIMARY KEY AUTO_INCREMENT,
mes INT,
ano INT,
chegadas INT,
via_acesso VARCHAR(45),
fk_uf_sigla CHAR(2),
fk_fonte INT,
fk_pais INT,
CONSTRAINT FOREIGN KEY (fk_uf_sigla) REFERENCES Unidade_Federativa_Brasil (sigla),
CONSTRAINT FOREIGN KEY (fk_fonte) REFERENCES Fonte (id_fonte),
CONSTRAINT FOREIGN KEY (fk_pais) REFERENCES Pais (idPais)
);

CREATE TABLE Chegadas_Turistas_Internacionais_Brasil_Anual (
id_chegadas_turistas_internacionais_brasil INT PRIMARY KEY AUTO_INCREMENT,
ano INT,
chegadas INT,
fk_uf_sigla CHAR(2),
fk_fonte INT,
fk_pais INT,
CONSTRAINT FOREIGN KEY (fk_uf_sigla) REFERENCES Unidade_Federativa_Brasil (sigla),
CONSTRAINT FOREIGN KEY (fk_fonte) REFERENCES Fonte (id_fonte),
CONSTRAINT FOREIGN KEY (fk_pais) REFERENCES Pais (idPais)
);

CREATE TABLE Perfil_Estimado_Turistas (
id_perfil_estimado_turistas INT PRIMARY KEY AUTO_INCREMENT,
fk_fonte INT,
genero VARCHAR(45),
faixa_etaria VARCHAR(45),
composicao_grupo_familiar VARCHAR(45),
fonte_informacao_viagem VARCHAR(45),
servico_agencia_turismo INT,
motivo_viagem VARCHAR(45),
permanencia_media DOUBLE,
gasto_media_percapita_em_reais DOUBLE,
ano INT,
fk_total_chegadas_turistas_internacionais_brasil_mensal INT,
fk_uf_destino CHAR(2),
fk_fonte_chegadas_turistas_brasil_anual INT,
fk_pais_origem INT,
CONSTRAINT FOREIGN KEY (fk_total_chegadas_turistas_internacionais_brasil_mensal) REFERENCES Chegadas_Turistas_Internacionais_Brasil_Mensal (id_chegadas_turistas_internacionais_brasil_mensal),
CONSTRAINT FOREIGN KEY (fk_uf_destino) REFERENCES Unidade_Federativa_Brasil (sigla),
CONSTRAINT FOREIGN KEY (fk_fonte_chegadas_turistas_brasil_anual) REFERENCES Chegadas_Turistas_Internacionais_Brasil_Anual (id_chegadas_turistas_internacionais_brasil),
CONSTRAINT FOREIGN KEY (fk_pais_origem) REFERENCES Pais (idPais)
);

CREATE TABLE Historico_Contato (
id_historico_contato INT PRIMARY KEY AUTO_INCREMENT,
data_contato DATE,
anotacoes TEXT,
responsavel VARCHAR(255),
fk_informacao_contato_cadastro INT,
CONSTRAINT FOREIGN KEY (fk_informacao_contato_cadastro) REFERENCES Informacao_Contato_Cadastro (id_informacao_contato_cadastro)
);

CREATE TABLE Empresa_Endereco (
id_empresa_endereco INT PRIMARY KEY AUTO_INCREMENT,
cep CHAR(8),
tipo_logradouro VARCHAR(50),
nome_logradouro VARCHAR(100),
numero INT,
complemento TEXT,
bairro VARCHAR(100),
cidade VARCHAR(100),
fk_uf_sigla CHAR(2),
CONSTRAINT FOREIGN KEY (fk_uf_sigla) REFERENCES Unidade_Federativa_Brasil (sigla)
);

CREATE TABLE Empresa (
cnpj CHAR(14) PRIMARY KEY,
nome_fantasia VARCHAR(255),
razao_social VARCHAR(255),
fk_informacao_contato_cadastro INT,
fk_empresa_endereco INT,
fk_uf_sigla CHAR(2),
CONSTRAINT FOREIGN KEY (fk_informacao_contato_cadastro) REFERENCES Informacao_Contato_Cadastro (id_informacao_contato_cadastro),
CONSTRAINT FOREIGN KEY (fk_empresa_endereco) REFERENCES Empresa_Endereco (id_empresa_endereco),
CONSTRAINT FOREIGN KEY (fk_uf_sigla) REFERENCES Unidade_Federativa_Brasil (sigla)
);

CREATE TABLE Funcionario (
id_funcionario INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(255),
cargo VARCHAR(70),
telefone VARCHAR(11),
fk_cnpj CHAR(14),
fk_informacao_contato_cadastro INT,
fk_empresa_endereco INT,
fk_uf_sigla CHAR(2),
fk_login INT,
CONSTRAINT FOREIGN KEY (fk_cnpj) REFERENCES Empresa (cnpj),
CONSTRAINT FOREIGN KEY (fk_informacao_contato_cadastro) REFERENCES Informacao_Contato_Cadastro (id_informacao_contato_cadastro),
CONSTRAINT FOREIGN KEY (fk_empresa_endereco) REFERENCES Empresa_Endereco (id_empresa_endereco),
CONSTRAINT FOREIGN KEY (fk_uf_sigla) REFERENCES Unidade_Federativa_Brasil (sigla),
CONSTRAINT FOREIGN KEY (fk_login) REFERENCES Funcionario (id_funcionario)
);

CREATE TABLE Configuracao_Slack (
id_configuracao_slack INT PRIMARY KEY AUTO_INCREMENT,
fk_usuario INT,
slack_user_id VARCHAR(45),
slack_username VARCHAR(255),
canal_padrao VARCHAR(255),
ativo VARCHAR(3),
tipo_notificacao VARCHAR(45),
CONSTRAINT FOREIGN KEY (fk_usuario) REFERENCES Usuario (id_usuario),
CONSTRAINT chk_ativo CHECK (ativo IN ('Sim', 'Não')),
CONSTRAINT chk_tipo_notificacao CHECK (tipo_notificacao IN ('Erro', 'Sucesso', 'Aviso', 'Todos'))
);

CREATE TABLE Preferencias_Visualizacao_Dashboard (
id_Preferencias_Visualizacao_Dashboard INT PRIMARY KEY AUTO_INCREMENT,
fk_usuario INT,
perfil_turista_ativo TINYINT DEFAULT 1,
temporadas_ativo TINYINT DEFAULT 1,
oportunidades_investimento_marketing_ativo TINYINT DEFAULT 1,
CONSTRAINT FOREIGN KEY (fk_usuario) REFERENCES Usuario (id_usuario)
);



