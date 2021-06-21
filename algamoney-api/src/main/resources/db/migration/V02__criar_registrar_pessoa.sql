	CREATE TABLE pessoa (
		codigo SERIAL PRIMARY KEY,
		nome VARCHAR(50) NOT NULL,
		ativo BOOLEAN NOT NULL,
		logradouro VARCHAR(50),
		numero VARCHAR(4),
		complemento VARCHAR(50),
		bairro VARCHAR(15),
		cep VARCHAR(8),
		cidade VARCHAR(15),
		estado VARCHAR(2)
	);
	
	
	INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	     VALUES ('Astroldo Silva', true, 'Rua Conceição', '67', 'Ed. Terin', 'Zona Norte', '20387963', 'São Paulo', 'SP');	
	INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	     VALUES ('Kacilda Matos', true, 'Rua Almeidão', '16', 'Ed. Fil', 'Amoedo', '20331326', 'Rio de Janeiro', 'RJ');
	INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	     VALUES ('Vanessa Azevedo', true, 'Rua Amazonas', '1259', 'Ed. Sereia', 'Pituba', '20351561', 'Salvador', 'BA');
	INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	     VALUES ('Tizil Souza', false, 'Rua Antoni', '651', '', 'Tupi', '20515651', 'Belo Horizonte', 'MG');
	     
	     