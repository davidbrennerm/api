-- Criar a tabela usuarios
CREATE TABLE usuarios (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(100) NOT NULL
);

-- Criar a tabela cursos
CREATE TABLE cursos (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    categoria VARCHAR(100) NOT NULL
);

-- Criar a tabela topicos
CREATE TABLE topicos (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    mensagem VARCHAR(255) NOT NULL,
    data_de_criacao TIMESTAMP,
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);
