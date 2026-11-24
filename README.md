
# CSV Gráfica Digital — Backend Web

API REST desenvolvida em **Java 21 e Spring Boot 4.1.1** para o sistema acadêmico CSV Gráfica Digital, projeto integrante do Trabalho Integrador da Universidade Nove de Julho (UNINOVE).

O Backend Web é responsável pelo gerenciamento dos clientes, consulta de produtos, criação de pedidos, validação de preços, processamento de pagamentos simulados e persistência dos dados no PostgreSQL.

## 1. Informações do projeto

| Informação | Descrição |
| --- | --- |
| Projeto | CSV Gráfica Digital |
| Módulo | Backend Web |
| Linguagem | Java 21 |
| Framework | Spring Boot 4.1.1 |
| Arquitetura | API REST em camadas |
| Banco de dados | PostgreSQL |
| Autenticação | Supabase Auth |
| Segurança | Spring Security + JWT |
| Persistência | Spring Data JPA / Hibernate |
| Gerenciador de dependências | Maven |
| Hospedagem | Render |
| Finalidade | Projeto acadêmico — UNINOVE |

### Links

- **API em produção:** https://csv-grafica-digital.onrender.com
- **Catálogo público:** https://csv-grafica-digital.onrender.com/v1/produtos
- **Frontend Web:** https://csv-sistema-digital.vercel.app
- **Repositório Backend:** https://github.com/CSV-Sistema-digital/sistema-grafica
- **Repositório Frontend:** https://github.com/CSV-Sistema-digital/front_end

> O Backend está hospedado no plano gratuito do Render. Após períodos de inatividade, a primeira requisição pode demorar enquanto a aplicação é inicializada.

## 2. Objetivo

Disponibilizar uma API REST para permitir que aplicações clientes realizem operações relacionadas ao processo de vendas de uma gráfica.

As responsabilidades principais são:

- Consultar produtos cadastrados.
- Pesquisar produtos pelo nome.
- Consultar detalhes de produtos ativos.
- Cadastrar e atualizar perfis de clientes autenticados.
- Calcular prévias de pedidos.
- Validar preços e quantidades.
- Criar pedidos no banco de dados.
- Registrar pagamentos simulados.
- Confirmar pagamentos.
- Consultar o histórico de pedidos.
- Restringir operações privadas ao usuário autenticado.

A API Web atende ao Frontend Web. O projeto Mobile possui uma implementação de Backend separada.

## 3. Tecnologias utilizadas

| Tecnologia | Aplicação |
| --- | --- |
| Java 21 | Linguagem de programação |
| Spring Boot 4.1.1 | Framework principal |
| Spring Web MVC | Desenvolvimento dos endpoints REST |
| Spring Data JPA | Persistência e acesso aos dados |
| Hibernate | Mapeamento objeto-relacional |
| PostgreSQL | Banco de dados relacional |
| Supabase | Hospedagem do banco e autenticação |
| Spring Security | Controle de acesso |
| OAuth2 Resource Server | Validação de JWT |
| Bean Validation | Validação dos dados recebidos |
| Lombok | Redução de código repetitivo |
| Maven | Build e dependências |
| Docker | Empacotamento e execução |
| Render | Hospedagem da API |
| Postman | Testes manuais dos endpoints |
| Git/GitHub | Versionamento e colaboração |

## 4. Arquitetura do Backend

O Backend utiliza arquitetura em camadas para separar as responsabilidades da aplicação.

```text
Frontend Web
     |
     | Requisições HTTP/HTTPS
     | JSON + JWT
     v
+---------------------------+
|      Spring Security      |
|   Autenticação e acesso   |
+---------------------------+
              |
              v
+---------------------------+
|        Controller         |
|    Endpoints REST         |
+---------------------------+
              |
              v
+---------------------------+
|         Service           |
|     Regras de negócio     |
+---------------------------+
              |
              v
+---------------------------+
|        Repository         |
|       Spring Data JPA     |
+---------------------------+
              |
              v
+---------------------------+
|       PostgreSQL          |
|         Supabase          |
+---------------------------+
```

### Controller

Recebe as requisições HTTP, valida os dados de entrada e encaminha as operações para a camada de serviço.

Controladores implementados:

- `ProdutoController`
- `ClienteController`
- `PedidoController`

### Service

Responsável pelas regras de negócio e pela coordenação das operações de persistência.

Serviços implementados:

- `ProdutoService`
- `ClienteService`
- `PedidoService`

### Repository

Responsável pela comunicação com o PostgreSQL por meio do Spring Data JPA.

Repositórios implementados:

- `ProdutoRepository`
- `ClienteRepository`
- `PedidoRepository`
- `ItemPedidoRepository`
- `PagamentoRepository`

### Model

Representa as entidades persistidas no banco de dados.

### DTO

Os Data Transfer Objects definem os dados recebidos e devolvidos pela API, evitando a exposição direta das entidades JPA.

### Exception

Centraliza o tratamento de determinadas exceções e os retornos de erro da aplicação.

### Config

Contém configurações relacionadas a segurança, JWT e CORS.

## 5. Estrutura de diretórios

```text
backend/
|
|-- Dockerfile
|-- pom.xml
|-- mvnw
|-- mvnw.cmd
|-- README.md
|
|-- src/
    |
    |-- main/
    |   |
    |   |-- java/br/com/grafica/backend/
    |   |   |
    |   |   |-- config/
    |   |   |   |-- CorsConfig.java
    |   |   |   |-- JwtConfig.java
    |   |   |   |-- SecurityConfig.java
    |   |   |
    |   |   |-- controller/
    |   |   |   |-- ClienteController.java
    |   |   |   |-- PedidoController.java
    |   |   |   |-- ProdutoController.java
    |   |   |
    |   |   |-- dto/
    |   |   |   |-- cliente/
    |   |   |   |-- itempedido/
    |   |   |   |-- pagamento/
    |   |   |   |-- pedido/
    |   |   |   |-- produto/
    |   |   |
    |   |   |-- enums/
    |   |   |-- exception/
    |   |   |-- model/
    |   |   |-- repository/
    |   |   |-- service/
    |   |   |
    |   |   |-- BackendApplication.java
    |   |
    |   |-- resources/
    |       |-- application.yaml
    |
    |-- test/
```

## 6. Banco de dados

O Backend utiliza PostgreSQL, hospedado no Supabase.

O acesso aos dados ocorre por meio do Spring Data JPA e Hibernate.

### Entidades

| Entidade | Descrição |
| --- | --- |
| Cliente | Informações cadastrais dos usuários |
| Produto | Produtos disponíveis para venda |
| Pedido | Compras realizadas pelos clientes |
| ItemPedido | Produtos e quantidades de cada pedido |
| Pagamento | Informações e status dos pagamentos |

### Relacionamentos

```text
CLIENTE
   |
   | 1:N
   v
PEDIDO
   |
   | 1:N
   v
ITEM_PEDIDO
   |
   | N:1
   v
PRODUTO

PEDIDO
   |
   | 1:1
   v
PAGAMENTO
```

### Identificadores

As entidades utilizam identificadores UUID.

### Validações de dados

- CPF com exatamente 11 dígitos numéricos.
- Quantidade de produtos entre 1 e 999 por item.
- Preços e valores monetários não negativos.
- Produtos inativos não podem ser utilizados em novos pedidos.
- Pedidos devem estar associados ao cliente autenticado.
- O Backend deve utilizar os preços atuais registrados no banco.

### Persistência

A configuração utilizada é:

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: none
```

Isso significa que o Hibernate não cria nem altera automaticamente as tabelas do banco de dados.

A estrutura do PostgreSQL deve estar previamente configurada.

## 7. Autenticação e segurança

A autenticação dos usuários é realizada pelo Supabase Auth.

O Spring Boot funciona como um OAuth2 Resource Server, validando os tokens JWT recebidos.

### Fluxo de autenticação

```text
1. Usuário realiza login no Frontend
                    |
                    v
2. Supabase Auth autentica o usuário
                    |
                    v
3. Supabase retorna um access_token
                    |
                    v
4. Frontend envia JWT no Authorization
                    |
                    v
5. Spring Security valida o token
                    |
                    v
6. API identifica o usuário autenticado
                    |
                    v
7. Operação autorizada é executada
```

### Cabeçalho de autenticação

As rotas protegidas exigem:

```http
Authorization: Bearer <access_token>
```

O token deve ser válido e emitido pelo projeto Supabase configurado na API.

### Permissões

| Funcionalidade | Autenticação |
| --- | --- |
| Listar produtos | Pública |
| Consultar produto | Pública |
| Pesquisar produtos | Pública |
| Consultar perfil | Obrigatória |
| Criar ou atualizar perfil | Obrigatória |
| Calcular prévia | Obrigatória |
| Criar pedido | Obrigatória |
| Consultar pedidos | Obrigatória |
| Confirmar pagamento | Obrigatória |

A API utiliza sessões HTTP stateless.

O Frontend não deve possuir credenciais privadas de banco de dados nem chaves `service_role`.

## 8. Endpoints disponíveis

**Base URL — Local**

```text
http://localhost:8080
```

**Base URL — Produção**

```text
https://csv-grafica-digital.onrender.com
```

### Produtos

| Método | Endpoint | Descrição |
| --- | --- | --- |
| GET | `/v1/produtos` | Listar produtos ativos |
| GET | `/v1/produtos/{id}` | Consultar produto pelo UUID |
| GET | `/v1/produtos/pesquisa?nome=...` | Pesquisar produtos por nome |

Essas rotas são públicas.

### Clientes

| Método | Endpoint | Descrição |
| --- | --- | --- |
| GET | `/v1/clientes/me` | Consultar perfil autenticado |
| PUT | `/v1/clientes/me` | Criar ou atualizar perfil |

Essas rotas exigem autenticação.

### Pedidos

| Método | Endpoint | Descrição |
| --- | --- | --- |
| POST | `/v1/pedidos/previa` | Calcular prévia |
| POST | `/v1/pedidos` | Criar pedido |
| GET | `/v1/pedidos` | Consultar histórico |
| GET | `/v1/pedidos/{id}` | Consultar pedido específico |
| POST | `/v1/pedidos/{id}/confirmar-pagamento` | Confirmar pagamento simulado |

Essas rotas exigem autenticação.

## 9. Exemplos de requisições

### 9.1. Listar produtos

```http
GET /v1/produtos
Accept: application/json
```

Exemplo com cURL:

```bash
curl https://csv-grafica-digital.onrender.com/v1/produtos
```

### 9.2. Pesquisar produtos

```http
GET /v1/produtos/pesquisa?nome=cartao
Accept: application/json
```

O parâmetro `nome` contém o termo a ser pesquisado.

### 9.3. Criar ou atualizar perfil

```http
PUT /v1/clientes/me
Authorization: Bearer <access_token>
Content-Type: application/json
```

Corpo:

```json
{
  "nome": "Cliente Exemplo",
  "cpf": "12345678901",
  "telefone": "11999999999"
}
```

O e-mail e a identificação de autenticação são obtidos do JWT, e não informados diretamente no corpo da requisição.

### 9.4. Consultar perfil

```http
GET /v1/clientes/me
Authorization: Bearer <access_token>
```

### 9.5. Calcular prévia de pedido

```http
POST /v1/pedidos/previa
Authorization: Bearer <access_token>
Content-Type: application/json
```

Corpo:

```json
{
  "itens": [
    {
      "produtoId": "550e8400-e29b-41d4-a716-446655440000",
      "quantidade": 2
    }
  ]
}
```

O UUID apresentado é ilustrativo e deve ser substituído pelo identificador real de um produto ativo.

A API retorna os valores unitários, subtotais e o valor total calculado com os dados atuais do banco.

### 9.6. Criar pedido

```http
POST /v1/pedidos
Authorization: Bearer <access_token>
Content-Type: application/json
```

Corpo:

```json
{
  "itens": [
    {
      "produtoId": "550e8400-e29b-41d4-a716-446655440000",
      "quantidade": 2
    }
  ],
  "formaPagamento": "PIX",
  "precosConfirmados": {
    "550e8400-e29b-41d4-a716-446655440000": 25.00
  }
}
```

O valor `25.00` é apenas um exemplo.

O preço enviado em `precosConfirmados` deve corresponder ao valor unitário calculado pelo Backend na prévia.

Caso exista divergência, a API retorna um conflito, impedindo a criação com um preço desatualizado.

### 9.7. Confirmar pagamento

```http
POST /v1/pedidos/{id}/confirmar-pagamento
Authorization: Bearer <access_token>
```

A operação realiza a confirmação simulada do pagamento do pedido correspondente ao cliente autenticado.

## 10. Regras de negócio

### Produtos

- Apenas produtos ativos aparecem no catálogo.
- Produtos podem ser consultados por UUID.
- A pesquisa utiliza o nome do produto.
- Produtos inativos não podem ser adicionados a novos pedidos pelo Backend.

### Clientes

- Cada perfil está associado a um usuário autenticado do Supabase.
- O usuário pode consultar e atualizar seu próprio perfil.
- A identificação do usuário é extraída do JWT.
- O cadastro exige nome, CPF e telefone.

### Pedidos

- O cliente precisa estar autenticado e possuir perfil cadastrado.
- O Backend calcula os valores oficiais.
- Produtos repetidos são agrupados na prévia.
- A quantidade total de um mesmo produto não pode ultrapassar 999.
- A criação de pedidos é transacional.
- O preço confirmado precisa ser igual ao preço atual.
- O pedido é criado inicialmente com status `PENDENTE`.
- Cada pedido possui itens e um pagamento associado.

### Pagamentos

Formas de pagamento:

- `PIX`
- `CARTAO`
- `BOLETO`

Status disponíveis:

- `PENDENTE`
- `CONFIRMADO`
- `CANCELADO`

O pagamento inicia com status `PENDENTE`.

A operação de confirmação altera o status para `CONFIRMADO`.

Caso o pedido ou pagamento esteja cancelado, a confirmação é recusada.

A confirmação repetida de um pagamento já confirmado mantém o estado confirmado.

### Status do pedido

- `PENDENTE`
- `EM_PRODUCAO`
- `PRONTO`
- `CANCELADO`

A confirmação do pagamento não modifica automaticamente o status de produção do pedido.

## 11. Tratamento de erros

A aplicação utiliza validações de entrada, exceções de negócio e tratamento de erros HTTP.

Exemplos:

| HTTP | Situação |
| --- | --- |
| 400 | Dados inválidos ou quantidade fora do limite |
| 401 | Ausência de autenticação válida |
| 403 | Requisição não autorizada pelas regras de acesso ou CORS |
| 404 | Cliente, produto, pedido ou pagamento não encontrado |
| 409 | Preço alterado, conflito de cadastro ou operação incompatível com o estado |

### Preço alterado

Quando o preço confirmado não corresponde ao preço atual, a API retorna:

```text
HTTP 409 Conflict
PRECO_ALTERADO
```

O Frontend deve solicitar uma nova prévia e exigir nova confirmação dos preços pelo usuário.

Os detalhes da resposta de erro dependem do tratamento implementado pela aplicação.

## 12. Executar o projeto localmente

### Pré-requisitos

- JDK 21.
- Git.
- Banco PostgreSQL configurado.
- Projeto Supabase Auth.
- IntelliJ IDEA ou outra IDE Java.

O repositório possui Maven Wrapper.

### Clonar o repositório

```bash
git clone https://github.com/CSV-Sistema-digital/sistema-grafica.git

cd sistema-grafica/backend
```

### Variáveis de ambiente

A aplicação utiliza as seguintes variáveis:

| Variável | Descrição |
| --- | --- |
| `DB_URL_CSV` | URL JDBC do PostgreSQL |
| `DB_USERNAME_CSV` | Usuário do banco |
| `DB_PASSWORD_CSV` | Senha do banco |
| `SUPABASE_URL` | URL do projeto Supabase |
| `FRONTEND_ORIGINS` | Origens permitidas no CORS |
| `PORT` | Porta HTTP da aplicação |

Exemplo:

```text
DB_URL_CSV=jdbc:postgresql://HOST:5432/postgres?sslmode=require

DB_USERNAME_CSV=USUARIO

DB_PASSWORD_CSV=SENHA

SUPABASE_URL=https://SEU-PROJETO.supabase.co

FRONTEND_ORIGINS=http://localhost:5500,http://127.0.0.1:5500

PORT=8080
```

As variáveis devem ser configuradas no ambiente de execução ou nas configurações da IDE.

Não é necessário alterar e publicar senhas no `application.yaml`.

### Executar com Maven Wrapper

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Linux/macOS:

```bash
./mvnw spring-boot:run
```

### Compilar

Windows:

```powershell
.\mvnw.cmd clean package
```

Linux/macOS:

```bash
./mvnw clean package
```

### Testar

```text
http://localhost:8080/v1/produtos
```

O endpoint deve retornar uma resposta JSON contendo os produtos ativos disponíveis no banco de dados.

## 13. Configurações da aplicação

O arquivo de configuração está localizado em:

```text
backend/src/main/resources/application.yaml
```

### Banco de dados

```yaml
spring:
  datasource:
    url: ${DB_URL_CSV}
    username: ${DB_USERNAME_CSV}
    password: ${DB_PASSWORD_CSV}
    driver-class-name: org.postgresql.Driver
```

### Pool de conexões

```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 5
      minimum-idle: 1
      idle-timeout: 60000
      connection-timeout: 30000
```

O pool HikariCP limita a quantidade de conexões simultâneas utilizadas por essa instância da aplicação.

### Validação JWT

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: ${SUPABASE_URL}/auth/v1
          jwk-set-uri: ${SUPABASE_URL}/auth/v1/.well-known/jwks.json
```

### CORS

```yaml
app:
  cors:
    allowed-origins: ${FRONTEND_ORIGINS:http://localhost:5500,http://127.0.0.1:5500}
```

Os métodos HTTP configurados no CORS são:

- GET
- POST
- PUT
- OPTIONS

Os cabeçalhos autorizados incluem `Authorization`, `Content-Type` e `Accept`.

## 14. Deploy no Render

A API está publicada em:

https://csv-grafica-digital.onrender.com

### Configurações utilizadas

| Campo | Valor |
| --- | --- |
| Service Name | CSV-Grafica-digital |
| Repository | CSV-Sistema-digital/sistema-grafica |
| Branch | main |
| Root Directory | backend |
| Runtime | Docker |
| Dockerfile | ./Dockerfile |
| Região | Virginia |
| Plano | Free |

### Docker

O projeto utiliza um Dockerfile com duas etapas:

1. Compilação com Maven e JDK 21.
2. Execução do arquivo JAR com Java 21.

O processo de inicialização executa o JAR gerado pelo Maven.

### Variáveis no Render

```text
DB_URL_CSV
DB_USERNAME_CSV
DB_PASSWORD_CSV
SUPABASE_URL
FRONTEND_ORIGINS
```

O domínio autorizado do Frontend em produção é:

```text
https://csv-sistema-digital.vercel.app
```

A configuração correta de CORS é necessária para permitir as requisições feitas pelo navegador.

### Limitações do plano gratuito

O serviço pode entrar em repouso após um período de inatividade.

Por esse motivo, a primeira requisição pode levar mais tempo.

Durante a apresentação acadêmica, recomenda-se abrir o endpoint público do catálogo antes de iniciar a demonstração do Frontend.

## 15. Testes da API

Os endpoints podem ser testados utilizando o Postman, Insomnia ou ferramentas equivalentes.

### Testes recomendados

1. Consultar produtos sem autenticação.
2. Pesquisar produtos pelo nome.
3. Consultar produto por UUID.
4. Tentar acessar rota protegida sem JWT.
5. Consultar perfil com JWT válido.
6. Criar ou atualizar perfil.
7. Solicitar prévia de pedido.
8. Criar pedido com preços confirmados.
9. Consultar histórico de pedidos.
10. Confirmar pagamento simulado.
11. Verificar persistência no PostgreSQL.

### Comportamentos importantes

- Requisições protegidas exigem autenticação.
- Usuários só podem consultar seus próprios pedidos.
- A API recalcula os preços antes de criar um pedido.
- A criação do pedido deve persistir pedido, itens e pagamento.
- A confirmação do pagamento modifica o status correspondente.

## 16. Escopo acadêmico

A API Web foi desenvolvida para demonstrar conceitos de desenvolvimento Backend e integração de sistemas.

O projeto inclui:

- API REST.
- Arquitetura em camadas.
- Programação Orientada a Objetos.
- DTOs.
- Validação de dados.
- Tratamento de erros.
- Persistência relacional.
- Autenticação JWT.
- Regras de negócio.
- Transações de banco de dados.
- Hospedagem em nuvem.

### Limitações do escopo

- Não existe integração com gateway de pagamento real.
- Não há controle de estoque no MVP.
- O carrinho é administrado pelo Frontend.
- A API Web não possui endpoint DELETE.
- A administração de produtos não está disponível nos controllers da API Web atual.
- O módulo Mobile possui Backend separado.
- A aplicação não foi dimensionada ou validada para uso comercial em larga escala.

## 17. Roteiro técnico de apresentação

Para demonstrar o funcionamento do Backend:

1. Abrir o endpoint `/v1/produtos`.
2. Mostrar a resposta JSON do catálogo.
3. Explicar as camadas Controller, Service e Repository.
4. Demonstrar uma requisição autenticada.
5. Mostrar o cadastro ou atualização do perfil.
6. Solicitar uma prévia de pedido.
7. Criar um pedido.
8. Confirmar o pagamento simulado.
9. Consultar o pedido recém-criado.
10. Verificar o registro no PostgreSQL.

Esse fluxo demonstra a comunicação entre o Frontend, a API REST e o banco de dados.

## 18. Versionamento

O desenvolvimento utiliza Git e GitHub.

Fluxo recomendado:

```bash
git switch main

git pull origin main

git switch -c feature/nova-funcionalidade

git add .

git commit -m "feat: adiciona nova funcionalidade"

git push -u origin feature/nova-funcionalidade
```

Após enviar a branch, deve ser criado um Pull Request para revisão e integração na `main`.

## 19. Considerações finais

O Backend Web do CSV Gráfica Digital implementa o processamento das principais operações de vendas da aplicação acadêmica.

A utilização de Java, Spring Boot, PostgreSQL, Spring Security e Supabase Auth demonstra a integração entre tecnologias de desenvolvimento Backend, persistência relacional e autenticação.

A hospedagem no Render permite que a API seja consumida remotamente pelo Frontend publicado na Vercel.

**Projeto desenvolvido exclusivamente para fins acadêmicos — Universidade Nove de Julho (UNINOVE).**
