
--------------------------
-- Criar banco de dados --
--------------------------

CREATE DATABASE ecommerce;


-------------------
-- Criar tabelas --
-------------------

CREATE TABLE categoria (
  id bigserial PRIMARY KEY,
  criacao timestamp NOT NULL DEFAULT now(),
  alteracao timestamp NOT NULL DEFAULT now(),
  nome varchar(30) NOT NULL,
  descricao varchar(255)
);

CREATE TABLE produto (
  id bigserial PRIMARY KEY,
  criacao timestamp NOT NULL DEFAULT now(),
  alteracao timestamp NOT NULL DEFAULT now(),
  nome varchar(30) NOT NULL,
  descricao TEXT,
  categoria_id bigint NOT NULL REFERENCES categoria(id),
  preco numeric(19, 2) NOT NULL,
  lote int NOT NULL
);

CREATE TABLE endereco (
  id bigserial PRIMARY KEY,
  criacao timestamp NOT NULL DEFAULT now(),
  alteracao timestamp NOT NULL DEFAULT now(),
  cliente_id bigint NOT NULL, -- Criar a FOREIGN KEY posteriormente
  tipo varchar(30) NOT NULL,
  cep varchar(10) NOT NULL,
  logradouro varchar(255) NOT NULL,
  numero varchar(6) NOT NULL,
  complemento varchar(50),
  bairro varchar(100) NOT NULL,
  cidade varchar(50) NOT NULL,
  uf varchar(2) NOT NULL
);

CREATE TABLE cliente (
  id bigserial PRIMARY KEY,
  criacao timestamp NOT NULL DEFAULT now(),
  alteracao timestamp NOT NULL DEFAULT now(),
  nome varchar(50) NOT NULL,
  documento varchar(18) NOT NULL,
  endereco_padrao_id bigint REFERENCES endereco(id)
);

ALTER TABLE endereco ADD CONSTRAINT endereco_cliente_id_fkey FOREIGN KEY (cliente_id) REFERENCES cliente(id);

CREATE TABLE pedido (
  id bigserial PRIMARY KEY,
  criacao timestamp NOT NULL DEFAULT now(),
  alteracao timestamp NOT NULL DEFAULT now(),
  numero_pedido varchar(255) NOT NULL,
  cliente_id bigint NOT NULL REFERENCES cliente(id),
  endereco_id bigint NOT NULL REFERENCES endereco(id),
  status varchar(2) NOT NULL DEFAULT 'PE' CHECK (status IN ('PE', 'PA', 'CA', 'PP', 'ET', 'AR', 'EN')), -- PEndente / PAgo / CAncelado / Preparando Pedido / Em Transito / Aguardando Retirada / ENtregue
  total_itens  numeric(19,2) NOT NULL,
  total_frete  numeric(19,2) NOT NULL,
  total_pedido numeric(19,2) NOT NULL
);

CREATE TABLE pedido_item (
  id bigserial PRIMARY KEY,
  pedido_id bigint NOT NULL REFERENCES pedido(id),
  produto_id bigint NOT NULL REFERENCES produto(id),
  quantidade int NOT NULL,
  valor_unitario numeric(19,2) NOT NULL,
  valor_total numeric(19,2) NOT NULL
);
