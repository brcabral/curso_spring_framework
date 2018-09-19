CREATE TABLE cliente (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(80) NOT NULL,
	tipo_pessoa VARCHAR(15) NOT NULL,
	cpf_cnpj VARCHAR(18),
	telefone VARCHAR(15),
	email VARCHAR(50) NOT NULL,
	logradouro VARCHAR(50),
	numero VARCHAR(5),
	complemento VARCHAR(25),
	cep VARCHAR(10),
	codigo_cidade BIGINT(20),
	FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;