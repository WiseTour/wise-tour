var usuarioModel = require("../models/usuarioModel");

function autenticar(req, res) {
    var email = req.body.emailServer;
    var senha = req.body.senhaServer;

    if (email == undefined) {
        res.status(400).send("Seu email está undefined!");
    } else if (senha == undefined) {
        res.status(400).send("Sua senha está indefinida!");
    } else {
        usuarioModel.autenticar(email, senha)
            .then(function (resultadoAutenticar) {
                console.log(`\nResultados encontrados: ${resultadoAutenticar.length}`);
                console.log(`Resultados: ${JSON.stringify(resultadoAutenticar)}`);

                if (resultadoAutenticar.length == 1) {
                    console.log(resultadoAutenticar);

                    res.json({
                        id: resultadoAutenticar[0].idLogin,
                        email: resultadoAutenticar[0].email
                        // senha: resultadoAutenticar[0].senha ← evitar retornar
                        // aquarios removido
                    });                                       

                } else if (resultadoAutenticar.length == 0) {
                    res.status(403).send("Email e/ou senha inválido(s)");
                } else {
                    res.status(403).send("Mais de um usuário com o mesmo login e senha!");
                }
            }).catch(function (erro) {
                console.log(erro);
                console.log("\nHouve um erro ao realizar o login! Erro: ", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            });
    }
}

function cadastrar(req, res) {
    var nome = req.body.nomeServer;
    var email = req.body.emailServer;
    var numeroUsuario = req.body.numeroUsuarioServer;

    if (nome == undefined) {
        res.status(400).send("Seu nome está undefined!");
    } else if (email == undefined) {
        res.status(400).send("Seu email está undefined!");
    } else if (numeroUsuario == undefined) {
        res.status(400).send("Seu numero está undefined!");
    } else {
        usuarioModel.cadastrar(nome, email, numeroUsuario)
            .then(function (resultado) {
                res.json(resultado);
            }).catch(function (erro) {
                console.log(erro);
                console.log("\nHouve um erro ao realizar o cadastro! Erro: ", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            });
    }
}

function cadastrarEmpresa(req, res) {
    console.log ("cheguei cadastrar empresa")
    var cnpj = req.body.cnpjServer;
    var nomeFantasia = req.body.nomeFantasiaServer;
    var razaoSocial = req.body.razaoSocialServer;
    var idInformacaoCadastro = req.body.idInformacaoCadastroServer;
    var cep = req.body.cepServer;
    var tipoLogradouro = req.body.tipoLogradouroServer;
    var nomeLogradouro = req.body.nomeLogradouroServer;
    var numero = req.body.numeroServer;
    var complemento = req.body.complementoServer;
    var bairro = req.body.bairroServer;
    var cidade = req.body.cidadeServer;
    var siglaUf = req.body.siglaUfServer;    

    if (cnpj == undefined) {
        res.status(400).send("O CNPJ está undefined!");
    } else if (nomeFantasia == undefined) {
        res.status(400).send("O nome fantasia está undefined!");
    } else if (razaoSocial == undefined) {
        res.status(400).send("A razão social está undefined!");
    } else if (idInformacaoCadastro == undefined) {
        res.status(400).send("O ID da Informacao Cadastro está undefined!");
    } else if (cep == undefined) {
        res.status(400).send("O CEP está undefined!");
    } else if (tipoLogradouro == undefined) {
        res.status(400).send("O tipo de logradouro está undefined!");
    } else if (nomeLogradouro == undefined) {
        res.status(400).send("O nome do logradouro está undefined!");
    } else if (numero == undefined) {
        res.status(400).send("O número está undefined!");
    } else if (complemento == undefined) {
        res.status(400).send("O complemento está undefined!");
    } else if (bairro == undefined) {
        res.status(400).send("O bairro está undefined!");
    } else if (cidade == undefined) {
        res.status(400).send("A cidade está undefined!");
    } else if (siglaUf == undefined) {
        res.status(400).send("A sigla UF está undefined!");
    } else {
        usuarioModel.cadastrarEmpresa(
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
        )
        .then(function (resultado) {
            res.json(resultado);
        })
        .catch(function (erro) {
            console.log(erro);
            console.log("\nHouve um erro ao realizar o cadastro da empresa! Erro: ", erro.sqlMessage);
            res.status(500).json(erro.sqlMessage);
        });
    }    
}

function cadastrarResponsavel(req, res) {
    var nome = req.body.nomeServer;
    var cargo = req.body.cargoServer;
    var telefone = req.body.telefoneServer;
    var cnpjEmpresa = req.body.cnpjEmpresaServer;
    var idInformacao = req.body.idInformacaoServer;
    var siglaUf = req.body.siglaUfServer;

    if (nome == undefined) {
        res.status(400).send("O nome está undefined!");
    } else if (cargo == undefined) {
        res.status(400).send("O cargo está undefined!");
    } else if (telefone == undefined) {
        res.status(400).send("O telefone está undefined!");
    } else if (cnpjEmpresa == undefined) {
        res.status(400).send("O CNPJ da empresa está undefined!");
    } else if (idInformacao == undefined) {
        res.status(400).send("O ID da informação de contato está undefined!");
    } else if (siglaUf == undefined) {
        res.status(400).send("A sigla UF está undefined!");
    } else {
        usuarioModel.cadastrarResponsavel(
            nome,
            cargo,
            telefone,
            cnpjEmpresa,
            idInformacao,
            siglaUf
        )
        .then(function (resultado) {
            res.json(resultado);
        })
        .catch(function (erro) {
            console.log(erro);
            console.log("\nHouve um erro ao realizar o cadastro do responsável! Erro: ", erro.sqlMessage);
            res.status(500).json(erro.sqlMessage);
        });
    }
}


function cadastrarUsuario(req, res) {
    var email = req.body.emailServer;
    var senha = req.body.senhaServer;
    var permissao = req.body.permissaoServer;

    if (email == undefined) {
        res.status(400).send("O email está undefined!");
    } else if (senha == undefined) {
        res.status(400).send("A senha está undefined!");
    } else if (permissao == undefined) {
        res.status(400).send("A permissão está undefined!");
    } else {
        usuarioModel.cadastrarUsuario(
            email,
            senha,
            permissao
        )
        .then(function (resultado) {
            res.json(resultado);
        })
        .catch(function (erro) {
            console.log(erro);
            console.log("\nHouve um erro ao realizar o cadastro do usuário! Erro: ", erro.sqlMessage);
            res.status(500).json(erro.sqlMessage);
        });
    }
}


module.exports = {
    autenticar,
    cadastrar,
    cadastrarEmpresa,
    cadastrarResponsavel,
    cadastrarUsuario
};
