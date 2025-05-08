var database = require("../database/config")

function autenticar(email, senha) {
    console.log("ACESSEI O USUARIO MODEL \n \n\t\t >> Se aqui der erro de 'Error: connect ECONNREFUSED',\n \t\t >> verifique suas credenciais de acesso ao banco\n \t\t >> e se o servidor de seu BD está rodando corretamente. \n\n function entrar(): ", email, senha)
    var instrucaoSql = `
        SELECT idLogin, email, senha FROM login WHERE email = '${email}' AND senha = '${senha}';
    `;
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

// Coloque os mesmos parâmetros aqui. Vá para a var instrucaoSql
function cadastrar(nome, email, numeroUsuario) {
    console.log("ACESSEI O USUARIO MODEL \n \n\t\t >> Se aqui der erro de 'Error: connect ECONNREFUSED',\n \t\t >> verifique suas credenciais de acesso ao banco\n \t\t >> e se o servidor de seu BD está rodando corretamente. \n\n function cadastrar():", nome, email, numeroUsuario);
    
    // Insira exatamente a query do banco aqui, lembrando da nomenclatura exata nos valores
    //  e na ordem de inserção dos dados.
    var instrucaoSql = `
        INSERT INTO cadastro (nome, email, numeroUsuario) VALUES ('${nome}', '${email}', '${numeroUsuario}');
    `;
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function cadastrarEmpresa(
    cnpj,
    nomeFantasia,
    razaoSocial,
    idInformacaoCadastro,
    cep,
    tipoLogradouro,
    nomeLogradouro,
    numero,
    complemento,
    bairro,
    cidade,
    siglaUf
) {
    console.log("ACESSEI O EMPRESA MODEL");

    var instrucaoSql = `
        INSERT INTO empresa (
            cnpj, nome_fantasia, razao_social, fk_informacao_contato_cadastro, cep, tipo_logradouro,
            nome_logradouro, numero, complemento, bairro, cidade, fk_uf_sigla
        ) VALUES (
            '${cnpj}', '${nomeFantasia}', '${razaoSocial}', '${idInformacaoCadastro}', '${cep}', '${tipoLogradouro}',
            '${nomeLogradouro}', '${numero}', '${complemento}', '${bairro}', '${cidade}', '${siglaUf}'
        );
    `;
    console.log("Executando a instrução SQL:\n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function cadastrarResponsavel(
    nome,
    cargo,
    telefone,
    cnpjEmpresa,
    idInformacao,
    siglaUf
) {
    console.log("ACESSEI O RESPONSÁVEL MODEL");

    var instrucaoSql = `
        INSERT INTO funcionario (
            nome, cargo, telefone, fk_cnpj, fk_informacao_contato_cadastro, fk_uf_sigla
        ) VALUES (
            '${nome}', '${cargo}', '${telefone}', '${cnpjEmpresa}', '${idInformacao}', '${siglaUf}'
        );
    `;
    console.log("Executando a instrução SQL:\n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function cadastrarUsuario(email, senha, permissao) {
    console.log("ACESSEI O USUÁRIO MODEL");

    var instrucaoSql = `
        INSERT INTO usuario (
            email, senha, permissao
        ) VALUES (
            '${email}', '${senha}', '${permissao}'
        );
    `;
    console.log("Executando a instrução SQL:\n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

module.exports = {
    autenticar,
    cadastrar,
    cadastrarEmpresa,
    cadastrarResponsavel,
    cadastrarUsuario
};