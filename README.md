# Sistema Gráfica — Trabalho Integrador UNINOVE

Sistema de gestão e vendas para uma gráfica, desenvolvido como Trabalho Integrador das disciplinas de **Aplicações Orientadas a Objetos** (Java/JSP/Servlets), **Computação Móvel** (Flutter) e **Programação para Banco de Dados** (SQL).

##  Sobre o projeto

O sistema permite que clientes visualizem o catálogo de produtos, façam cadastro/login, adicionem itens ao carrinho e realizem compras — tanto pela **Web** quanto pelo **Mobile**. O administrador da gráfica gerencia o catálogo (criar, editar, excluir, pesquisar produtos) através do painel Web.

##  Arquitetura

Uma única **API REST em Spring Boot** centraliza toda a regra de negócio e acesso ao banco de dados. Tanto o app Web (JSP/Servlets) quanto o app Mobile (Flutter) consomem essa mesma API.

```
                    ┌──────────────────┐
                    │   Spring Boot     │
                    │   API REST        │ ←→ Banco de Dados (PostgreSQL)
                    └────────┬──────────┘
                             │ JSON via HTTP
              ┌──────────────┼──────────────┐
              │                             │
      ┌───────▼────────┐           ┌────────▼────────┐
      │  Web (JSP/      │           │  Mobile (Flutter)│
      │  Servlets)      │           │                  │
      └─────────────────┘           └──────────────────┘
```

##  Tecnologias

| Camada | Tecnologia |
|---|---|
| Backend | Java + Spring Boot + Maven |
| Banco de Dados | PostgreSQL |
| Frontend Web | JSP + Servlets + JavaScript + CSS |
| Mobile | Flutter |
| Controle de versão | Git + GitHub |

##  Estrutura do repositório

```
projeto-grafica/
├── backend/                    # API REST (Spring Boot)
│   ├── src/main/java/...
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
├── database/
│   ├── scripts/                # Scripts DDL (criação de tabelas) e seeds
│   └── migrations/              # Migrations (se optarmos por Flyway)
│
├── frontend/                   # App Web (JSP/Servlets)
│   ├── src/main/webapp/
│   │   ├── WEB-INF/
│   │   ├── jsp/                 # Páginas .jsp
│   │   ├── css/
│   │   └── js/                  # Scripts que consomem a API
│   └── pom.xml
│
├── mobile/                     # App Mobile (Flutter)
│
├── docs/                       # Documentação obrigatória da Entrega 1
│   ├── 01-descricao-projeto.md
│   ├── 02-especificacao-requisitos.md
│   └── 03-modelagem-tecnica.md
│
├── .gitignore
└── README.md
```

##  Como rodar o projeto

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL instalado (ou Docker)
- Flutter SDK (para o mobile)
- Git

### 1. Clonar o repositório

```bash
git clone <URL_DO_REPOSITORIO>
cd projeto-grafica
```

### 2. Banco de dados

1. Crie um banco PostgreSQL local chamado `grafica_db`
2. Rode os scripts em `database/scripts/` na ordem indicada (criação de tabelas → seeds)

### 3. Backend (API)

```bash
cd backend
mvn spring-boot:run
```

A API sobe por padrão em `http://localhost:8080`.

Configure a conexão com o banco em `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/grafica_db
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

### 4. Frontend Web

O projeto Web roda como aplicação Java Web (WAR) em um servidor Tomcat. Configure o deploy no Tomcat local ou via sua IDE (Eclipse/IntelliJ), apontando para a pasta `frontend/`. A URL base da API deve estar configurada nos arquivos JS que fazem as chamadas fetch (`frontend/src/main/webapp/js/`).

### 5. Mobile (Flutter)

```bash
cd mobile
flutter pub get
flutter run
```

Configure a URL base da API no ponto de configuração do app Flutter (ex: `lib/config/api_config.dart`).

##  Fluxo de Git do grupo

- Nunca commitar direto na `main`
- Uma branch por tarefa: `area/nome-da-tarefa` (ex: `backend/crud-produto`, `mobile/tela-carrinho`)
- Abrir Pull Request para revisão antes de mergear na `main`
- Guia completo para quem não tem experiência com Git: veja a página **"Guia de Git para o grupo"** no Notion

##  Equipe

| Área | Responsáveis |
|---|---|
| Backend + Banco de Dados | Gabriel, Henrique |
| Frontend Web | Danilo, João |
| Mobile (Flutter) | Nicolas, Pablo, Guilherme |
| Documentação | Natasha |
| Testes/Integração | Cesar, Lucas |

##  Planejamento

O projeto está organizado em sprints. Veja o planejamento completo na página **"Sprint Planning"** no Notion.

##  Licença

Projeto acadêmico desenvolvido para a UNINOVE — Trabalho Integrador.
