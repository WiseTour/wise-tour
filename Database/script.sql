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
titulo_arquivo_fonte VARCHAR(255),
edicao VARCHAR(45),
orgao_emissor VARCHAR(45),
url_origem TEXT,
data_coleta DATE,
observacoes TEXT
);

CREATE TABLE Pais (
sigla CHAR(3) PRIMARY KEY,
pais VARCHAR(100)
);

CREATE TABLE Continente (
idContinente INT PRIMARY KEY AUTO_INCREMENT,
continente VARCHAR(45),
CONSTRAINT chk_continente CHECK (continente IN ('América', 'Oceania', 'Ásia', 'África', 'Europa'))
);

CREATE TABLE Unidade_Federativa_Brasil (
sigla CHAR(2) PRIMARY KEY,
unidade_federativa VARCHAR(45),
regiao VARCHAR(45),
CONSTRAINT chk_sigla CHECK (sigla IN('AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 
'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO')),
CONSTRAINT chk_unidade_federativa CHECK (unidade_federativa IN ('Acre', 'Alagoas', 'Amapá', 'Amazonas', 'Bahia', 'Ceará', 'Distrito Federal', 'Espírito Santo', 'Goiás', 
'Maranhão', 'Mato Grosso', 'Mato Grosso do Sul', 'Minas Gerais', 'Pará', 'Paraíba', 'Paraná', 'Pernambuco', 'Piauí', 'Rio de Janeiro', 'Rio Grande do Norte', 
'Rio Grande do Sul', 'Rondônia', 'Roraima', 'Santa Catarina', 'São Paulo', 'Sergipe', 'Tocantins')),
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
fk_pais CHAR(3),
CONSTRAINT FOREIGN KEY (fk_continente) REFERENCES Continente (idContinente),
CONSTRAINT FOREIGN KEY (fk_pais) REFERENCES Pais (sigla)
);

CREATE TABLE Chegadas_Turistas_Internacionais_Brasil_Mensal (
id_chegadas_turistas_internacionais_brasil_mensal INT PRIMARY KEY AUTO_INCREMENT,
mes INT,
ano INT,
chegadas INT,
via_acesso VARCHAR(45),
fk_uf_sigla CHAR(2),
fk_fonte INT,
fk_pais CHAR(3),
CONSTRAINT FOREIGN KEY (fk_uf_sigla) REFERENCES Unidade_Federativa_Brasil (sigla),
CONSTRAINT FOREIGN KEY (fk_fonte) REFERENCES Fonte (id_fonte),
CONSTRAINT FOREIGN KEY (fk_pais) REFERENCES Pais (sigla)
);

CREATE TABLE Chegadas_Turistas_Internacionais_Brasil_Anual (
id_chegadas_turistas_internacionais_brasil INT PRIMARY KEY AUTO_INCREMENT,
ano INT,
chegadas INT,
via_acesso VARCHAR(45),
fk_uf_sigla CHAR(2),
fk_fonte INT,
fk_pais CHAR(3),
CONSTRAINT FOREIGN KEY (fk_uf_sigla) REFERENCES Unidade_Federativa_Brasil (sigla),
CONSTRAINT FOREIGN KEY (fk_fonte) REFERENCES Fonte (id_fonte),
CONSTRAINT FOREIGN KEY (fk_pais) REFERENCES Pais (sigla)
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
fk_pais_origem CHAR(3),
CONSTRAINT FOREIGN KEY (fk_total_chegadas_turistas_internacionais_brasil_mensal) REFERENCES Chegadas_Turistas_Internacionais_Brasil_Mensal (id_chegadas_turistas_internacionais_brasil_mensal),
CONSTRAINT FOREIGN KEY (fk_uf_destino) REFERENCES Unidade_Federativa_Brasil (sigla),
CONSTRAINT FOREIGN KEY (fk_fonte_chegadas_turistas_brasil_anual) REFERENCES Chegadas_Turistas_Internacionais_Brasil_Anual (id_chegadas_turistas_internacionais_brasil),
CONSTRAINT FOREIGN KEY (fk_pais_origem) REFERENCES Pais (sigla)
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

INSERT INTO Continente (continente) 
VALUES 
('América'),
('Oceania'),
('Ásia'),
('África'),
('Europa');

INSERT INTO Pais (sigla, pais) 
VALUES 
('AFG', 'Afeganistão'),
('ALB', 'Albânia'),
('DZA', 'Argélia'),
('AND', 'Andorra'),
('AGO', 'Angola'),
('ATG', 'Antígua e Barbuda'),
('ARG', 'Argentina'),
('ARM', 'Armênia'),
('AUS', 'Austrália'),
('AUT', 'Áustria'),
('AZE', 'Azerbaijão'),
('BHS', 'Bahamas'),
('BHR', 'Bahrein'),
('BGD', 'Bangladesh'),
('BRB', 'Barbados'),
('BLR', 'Bielorrússia'),
('BEL', 'Bélgica'),
('BLZ', 'Belize'),
('BEN', 'Benim'),
('BHU', 'Butão'),
('BOL', 'Bolívia'),
('BIH', 'Bósnia e Herzegovina'),
('BWA', 'Botsuana'),
('BRA', 'Brasil'),
('BRN', 'Brunei'),
('BGR', 'Bulgária'),
('BFA', 'Burquina Faso'),
('BDI', 'Burundi'),
('CPV', 'Cabo Verde'),
('KHM', 'Camboja'),
('CMR', 'Camarões'),
('CAN', 'Canadá'),
('CAF', 'República Centro-Africana'),
('TCD', 'Chade'),
('CHL', 'Chile'),
('CHN', 'China'),
('COL', 'Colômbia'),
('COM', 'Comores'),
('COG', 'República do Congo'),
('COD', 'República Democrática do Congo'),
('COK', 'Ilhas Cook'),
('CRI', 'Costa Rica'),
('CIV', 'Costa do Marfim'),
('HRV', 'Croácia'),
('CUB', 'Cuba'),
('CYP', 'Chipre'),
('CZE', 'República Tcheca'),
('DNK', 'Dinamarca'),
('DJI', 'Djibuti'),
('DMA', 'Dominica'),
('DOM', 'República Dominicana'),
('ECU', 'Equador'),
('EGY', 'Egito'),
('SLV', 'El Salvador'),
('GNQ', 'Guiné Equatorial'),
('ERI', 'Eritreia'),
('EST', 'Estônia'),
('SWZ', 'Essuatíni'),
('ETH', 'Etiópia'),
('FJI', 'Fiji'),
('FIN', 'Finlândia'),
('FRA', 'França'),
('GAB', 'Gabão'),
('GMB', 'Gâmbia'),
('GEO', 'Geórgia'),
('DEU', 'Alemanha'),
('GHA', 'Gana'),
('GRC', 'Grécia'),
('GRD', 'Granada'),
('GTM', 'Guatemala'),
('GIN', 'Guiné'),
('GNB', 'Guiné-Bissau'),
('GUY', 'Guiana'),
('HTI', 'Haiti'),
('HND', 'Honduras'),
('HUN', 'Hungria'),
('ISL', 'Islândia'),
('IND', 'Índia'),
('IDN', 'Indonésia'),
('IRN', 'Irã'),
('IRQ', 'Iraque'),
('IRL', 'Irlanda'),
('ISR', 'Israel'),
('ITA', 'Itália'),
('JAM', 'Jamaica'),
('JPN', 'Japão'),
('JOR', 'Jordânia'),
('KAZ', 'Cazaquistão'),
('KEN', 'Quênia'),
('KIR', 'Quiribati'),
('KOS', 'Kosovo'),
('KWT', 'Kuwait'),
('KGZ', 'Quirguistão'),
('LAO', 'Laos'),
('LVA', 'Letônia'),
('LBN', 'Líbano'),
('LSO', 'Lesoto'),
('LBR', 'Libéria'),
('LBY', 'Líbia'),
('LIE', 'Liechtenstein'),
('LTU', 'Lituânia'),
('LUX', 'Luxemburgo'),
('MDG', 'Madagascar'),
('MWI', 'Malawi'),
('MYS', 'Malásia'),
('MDV', 'Maldivas'),
('MLI', 'Mali'),
('MLT', 'Malta'),
('MHL', 'Ilhas Marshall'),
('MRT', 'Mauritânia'),
('MUS', 'Maurício'),
('MEX', 'México'),
('FSM', 'Estados Federados da Micronésia'),
('MDA', 'Moldávia'),
('MCO', 'Mônaco'),
('MNG', 'Mongólia'),
('MNE', 'Montenegro'),
('MOZ', 'Moçambique'),
('MMR', 'Mianmar'),
('NAM', 'Namíbia'),
('NRU', 'Nauru'),
('NPL', 'Nepal'),
('NLD', 'Países Baixos'),
('NZL', 'Nova Zelândia'),
('NIC', 'Nicarágua'),
('NER', 'Níger'),
('NGA', 'Nigéria'),
('PRK', 'Coreia do Norte'),
('MNP', 'Ilhas Marianas do Norte'),
('NOR', 'Noruega'),
('OMN', 'Omã'),
('PAK', 'Paquistão'),
('PLW', 'Palau'),
('PAN', 'Panamá'),
('PNG', 'Papua-Nova Guiné'),
('PRY', 'Paraguai'),
('PER', 'Peru'),
('PHL', 'Filipinas'),
('POL', 'Polônia'),
('PRT', 'Portugal'),
('QAT', 'Catar'),
('ROU', 'Romênia'),
('RUS', 'Rússia'),
('RWA', 'Ruanda'),
('REU', 'Reunião'),
('BLM', 'São Bartolomeu'),
('KNA', 'São Cristóvão e Névis'),
('SPM', 'São Pedro e Miquelão'),
('VCT', 'São Vicente e Granadinas'),
('WSM', 'Samoa'),
('SMR', 'San Marino'),
('STP', 'São Tomé e Príncipe'),
('SAU', 'Arábia Saudita'),
('SEN', 'Senegal'),
('SYC', 'Seicheles'),
('SLE', 'Serra Leoa'),
('SGP', 'Singapura'),
('SVK', 'Eslováquia'),
('SVN', 'Eslovênia'),
('SLB', 'Ilhas Salomão'),
('SOM', 'Somália'),
('ZAF', 'África do Sul'),
('SGS', 'Geórgia do Sul e Ilhas Sandwich do Sul'),
('SSD', 'Sudão do Sul'),
('ESP', 'Espanha'),
('LKA', 'Sri Lanka'),
('SDN', 'Sudão'),
('SUR', 'Suriname'),
('SJM', 'Ilhas Svalbard e Jan Mayen'),
('SWZ', 'Essuatíni'),
('SWE', 'Suécia'),
('CHE', 'Suíça'),
('SYR', 'Síria'),
('TWN', 'Taiwan'),
('TJK', 'Tajiquistão'),
('TAN', 'Tanzânia'),
('THA', 'Tailândia'),
('TGO', 'Togo'),
('TTO', 'Trinidad e Tobago'),
('TUN', 'Tunísia'),
('TUR', 'Turquia'),
('TKM', 'Turcomenistão'),
('TUV', 'Tuvalu'),
('TCA', 'Ilhas Turcas e Caicos'),
('UGA', 'Uganda'),
('UKR', 'Ucrânia'),
('ARE', 'Emirados Árabes Unidos'),
('GBR', 'Reino Unido'),
('USA', 'Estados Unidos'),
('URY', 'Uruguai'),
('UZB', 'Uzbequistão'),
('VUT', 'Vanuatu'),
('VEN', 'Venezuela'),
('VNM', 'Vietnã'),
('WLF', 'Wallis e Futuna'),
('ESH', 'Saara Ocidental'),
('YEM', 'Iémen'),
('ZMB', 'Zâmbia'),
('ZWE', 'Zimbábue');

INSERT INTO Unidade_Federativa_Brasil (sigla, unidade_federativa, regiao)
VALUES
('AC', 'Acre', 'Norte'),
('AL', 'Alagoas', 'Nordeste'),
('AP', 'Amapá', 'Norte'),
('AM', 'Amazonas', 'Norte'),
('BA', 'Bahia', 'Nordeste'),
('CE', 'Ceará', 'Nordeste'),
('DF', 'Distrito Federal', 'Centro-Oeste'),
('ES', 'Espírito Santo', 'Sudeste'),
('GO', 'Goiás', 'Centro-Oeste'),
('MA', 'Maranhão', 'Nordeste'),
('MT', 'Mato Grosso', 'Centro-Oeste'),
('MS', 'Mato Grosso do Sul', 'Centro-Oeste'),
('MG', 'Minas Gerais', 'Sudeste'),
('PA', 'Pará', 'Norte'),
('PB', 'Paraíba', 'Nordeste'),
('PR', 'Paraná', 'Sul'),
('PE', 'Pernambuco', 'Nordeste'),
('PI', 'Piauí', 'Nordeste'),
('RJ', 'Rio de Janeiro', 'Sudeste'),
('RN', 'Rio Grande do Norte', 'Nordeste'),
('RS', 'Rio Grande do Sul', 'Sul'),
('RO', 'Rondônia', 'Norte'),
('RR', 'Roraima', 'Norte'),
('SC', 'Santa Catarina', 'Sul'),
('SP', 'São Paulo', 'Sudeste'),
('SE', 'Sergipe', 'Nordeste'),
('TO', 'Tocantins', 'Norte');

