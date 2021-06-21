CREATE TABLE lancamento (
	codigo SERIAL PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento Date NOT NULL,
	data_pagamento DATE,
	valor Decimal(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria INT NOT NULL,
	codigo_pessoa INT NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria (codigo),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa (codigo)
);


INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) 
VALUES ('Salário Mensal', '2017-06-01', null, '6500.00', 'Distribuição de Lucros', 'RECEITA', 1, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) 
VALUES ('Bahamas', '2017-06-06', '2017-06-10', '100.00', 'null', 'RECEITA', 3, 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) 
VALUES ('Top Club', '2018-12-06', '2018-12-20', '1511.00', 'Geração', 'DESPESA', 4, 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) 
VALUES ('Café', '2019-02-11', '2019-03-15', '1000.00', null, 'DESPESA', 1, 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) 
VALUES ('Eletrônicos', '2019-05-15', '2019-05-15', '2640.00', null, 'DESPESA', 2, 3);