--
-- PostgreSQL database dump
--

\restrict Up88Lg3p0wJEe57WCOa8Ka9XFEr69b4Yq8sbU3Gw3j2g1skrpY8ZDbwbotnqF8v

-- Dumped from database version 18.6
-- Dumped by pg_dump version 18.6

-- Started on 2026-09-03 09:17:16

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 872 (class 1247 OID 24747)
-- Name: tipo_forma_pagamento; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.tipo_forma_pagamento AS ENUM (
    'PIX',
    'CARTAO_CREDITO',
    'CARTAO_DEBITO',
    'BOLETO'
);


ALTER TYPE public.tipo_forma_pagamento OWNER TO postgres;

--
-- TOC entry 875 (class 1247 OID 24756)
-- Name: tipo_status_pagamento; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.tipo_status_pagamento AS ENUM (
    'PENDENTE',
    'APROVADO',
    'RECUSADO',
    'CANCELADO',
    'REEMBOLSADO'
);


ALTER TYPE public.tipo_status_pagamento OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 24589)
-- Name: clientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clientes (
    id_cliente integer NOT NULL,
    nome character varying(100) NOT NULL,
    cpf character varying(11) NOT NULL,
    email character varying(150) NOT NULL,
    telefone character varying(20),
    senha text NOT NULL
);


ALTER TABLE public.clientes OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24588)
-- Name: clientes_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.clientes ALTER COLUMN id_cliente ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.clientes_id_cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 228 (class 1259 OID 24799)
-- Name: item_pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_pedido (
    id_item_pedido integer NOT NULL,
    quantidade integer NOT NULL,
    preco_unitario numeric(10,2) NOT NULL,
    id_pedido integer NOT NULL,
    id_produto integer NOT NULL
);


ALTER TABLE public.item_pedido OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 24798)
-- Name: item_pedido_id_item_pedido_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.item_pedido ALTER COLUMN id_item_pedido ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.item_pedido_id_item_pedido_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 230 (class 1259 OID 24820)
-- Name: login_adm; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.login_adm (
    id_adm integer NOT NULL,
    nome character varying(100) NOT NULL,
    email character varying(150) NOT NULL,
    senha text NOT NULL
);


ALTER TABLE public.login_adm OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 24819)
-- Name: login_adm_id_adm_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.login_adm ALTER COLUMN id_adm ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.login_adm_id_adm_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 226 (class 1259 OID 24768)
-- Name: pagamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pagamento (
    id_pagamento integer NOT NULL,
    forma_pagamento character varying(100) NOT NULL,
    valor numeric(10,2) NOT NULL,
    status character varying(20) DEFAULT 'PENDENTE'::public.tipo_status_pagamento NOT NULL,
    data timestamp without time zone NOT NULL,
    id_pedido integer NOT NULL
);


ALTER TABLE public.pagamento OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24767)
-- Name: pagamento_id_pagamento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.pagamento ALTER COLUMN id_pagamento ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pagamento_id_pagamento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 224 (class 1259 OID 24658)
-- Name: pedidos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pedidos (
    id_pedido integer NOT NULL,
    data_pedido timestamp without time zone NOT NULL,
    valor_total numeric(10,2) NOT NULL,
    status character varying(50) NOT NULL,
    id_cliente integer NOT NULL
);


ALTER TABLE public.pedidos OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24657)
-- Name: pedidos_id_pedido_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.pedidos ALTER COLUMN id_pedido ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pedidos_id_pedido_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 24614)
-- Name: produtos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produtos (
    id_produto integer NOT NULL,
    nome_prod character varying(100) NOT NULL,
    descricao_prod text,
    preco numeric(10,2) NOT NULL,
    imagem character varying(225)
);


ALTER TABLE public.produtos OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24613)
-- Name: produtos_id_produto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.produtos ALTER COLUMN id_produto ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.produtos_id_produto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 5054 (class 0 OID 24589)
-- Dependencies: 220
-- Data for Name: clientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clientes (id_cliente, nome, cpf, email, telefone, senha) FROM stdin;
1	Henrique Amaral da Silva	12345667832	hamaraldasilva01@gmail.com	11950527614	rick0105
2	Nikolas José Silva de França	3184973221	nikolasdoscapas@gmail.com	1195053235	nobruapelao
3	Pablo Almeida da Silva	32152463235	pablogamer_22@gmail.com	11907188892	pablitojojas
4	Gabriel Olivera de Souza	3232754401	gabrielfarmaaura@gmail.com	115593239312	SeixSeten
\.


--
-- TOC entry 5062 (class 0 OID 24799)
-- Dependencies: 228
-- Data for Name: item_pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_pedido (id_item_pedido, quantidade, preco_unitario, id_pedido, id_produto) FROM stdin;
1	2	45.00	1	1
2	1	85.00	2	2
3	2	60.00	3	4
4	1	120.00	4	3
\.


--
-- TOC entry 5064 (class 0 OID 24820)
-- Dependencies: 230
-- Data for Name: login_adm; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.login_adm (id_adm, nome, email, senha) FROM stdin;
1	Carlos Almeida	carlos@grafica.com	Senha123
2	Juliana Santos	juliana@grafica.com	Senha456
3	Marcos Oliveira	marcos@grafica.com	Senha789
4	Fernanda Costa	fernanda@grafica.com	Senha321
\.


--
-- TOC entry 5060 (class 0 OID 24768)
-- Dependencies: 226
-- Data for Name: pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pagamento (id_pagamento, forma_pagamento, valor, status, data, id_pedido) FROM stdin;
1	PIX	90.00	Aprovado	2026-09-01 00:00:00	1
2	Cartão de Crédito	85.00	Aprovado	2026-09-01 00:00:00	2
3	Boleto	180.00	Pendente	2026-09-02 00:00:00	3
4	PIX	120.00	Aprovado	2026-09-03 00:00:00	4
\.


--
-- TOC entry 5058 (class 0 OID 24658)
-- Dependencies: 224
-- Data for Name: pedidos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pedidos (id_pedido, data_pedido, valor_total, status, id_cliente) FROM stdin;
1	2026-09-01 00:00:00	90.00	Pendente	1
2	2026-09-01 00:00:00	85.00	Pago	2
3	2026-09-02 00:00:00	180.00	Em produção	3
4	2026-09-03 00:00:00	120.00	Concluído	4
\.


--
-- TOC entry 5056 (class 0 OID 24614)
-- Dependencies: 222
-- Data for Name: produtos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.produtos (id_produto, nome_prod, descricao_prod, preco, imagem) FROM stdin;
1	Cartão de Visita	Cartão de visita personalizado, impressão frente e verso.	45.00	/imagens/cartao_visita.jpg
2	Banner 80x120	Banner em lona com impressão colorida de alta qualidade.	85.00	/imagens/banner.jpg
3	Panfleto A5	Panfleto tamanho A5, impressão colorida frente e verso.	120.00	/imagens/panfleto.jpg
4	Adesivo Personalizado	Adesivos personalizados em papel ou material vinílico.	60.00	/imagens/adesivo.jpg
\.


--
-- TOC entry 5070 (class 0 OID 0)
-- Dependencies: 219
-- Name: clientes_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clientes_id_cliente_seq', 4, true);


--
-- TOC entry 5071 (class 0 OID 0)
-- Dependencies: 227
-- Name: item_pedido_id_item_pedido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.item_pedido_id_item_pedido_seq', 4, true);


--
-- TOC entry 5072 (class 0 OID 0)
-- Dependencies: 229
-- Name: login_adm_id_adm_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.login_adm_id_adm_seq', 4, true);


--
-- TOC entry 5073 (class 0 OID 0)
-- Dependencies: 225
-- Name: pagamento_id_pagamento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pagamento_id_pagamento_seq', 4, true);


--
-- TOC entry 5074 (class 0 OID 0)
-- Dependencies: 223
-- Name: pedidos_id_pedido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pedidos_id_pedido_seq', 4, true);


--
-- TOC entry 5075 (class 0 OID 0)
-- Dependencies: 221
-- Name: produtos_id_produto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produtos_id_produto_seq', 4, true);


--
-- TOC entry 4889 (class 2606 OID 24600)
-- Name: clientes clientes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT clientes_pkey PRIMARY KEY (id_cliente);


--
-- TOC entry 4899 (class 2606 OID 24808)
-- Name: item_pedido item_pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT item_pedido_pkey PRIMARY KEY (id_item_pedido);


--
-- TOC entry 4901 (class 2606 OID 24830)
-- Name: login_adm login_adm_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.login_adm
    ADD CONSTRAINT login_adm_pkey PRIMARY KEY (id_adm);


--
-- TOC entry 4895 (class 2606 OID 24781)
-- Name: pagamento pagamento_id_pedido_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT pagamento_id_pedido_key UNIQUE (id_pedido);


--
-- TOC entry 4897 (class 2606 OID 24779)
-- Name: pagamento pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT pagamento_pkey PRIMARY KEY (id_pagamento);


--
-- TOC entry 4893 (class 2606 OID 24667)
-- Name: pedidos pedidos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedidos
    ADD CONSTRAINT pedidos_pkey PRIMARY KEY (id_pedido);


--
-- TOC entry 4891 (class 2606 OID 24623)
-- Name: produtos produtos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produtos
    ADD CONSTRAINT produtos_pkey PRIMARY KEY (id_produto);


--
-- TOC entry 4904 (class 2606 OID 24809)
-- Name: item_pedido fk_item_pedido_pedido; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (id_pedido) REFERENCES public.pedidos(id_pedido);


--
-- TOC entry 4905 (class 2606 OID 24814)
-- Name: item_pedido fk_item_pedido_produto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT fk_item_pedido_produto FOREIGN KEY (id_produto) REFERENCES public.produtos(id_produto);


--
-- TOC entry 4903 (class 2606 OID 24782)
-- Name: pagamento fk_pagamento_pedido; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT fk_pagamento_pedido FOREIGN KEY (id_pedido) REFERENCES public.pedidos(id_pedido);


--
-- TOC entry 4902 (class 2606 OID 24668)
-- Name: pedidos fk_pedido_cliente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedidos
    ADD CONSTRAINT fk_pedido_cliente FOREIGN KEY (id_cliente) REFERENCES public.clientes(id_cliente);


-- Completed on 2026-09-03 09:17:16

--
-- PostgreSQL database dump complete
--

\unrestrict Up88Lg3p0wJEe57WCOa8Ka9XFEr69b4Yq8sbU3Gw3j2g1skrpY8ZDbwbotnqF8v

