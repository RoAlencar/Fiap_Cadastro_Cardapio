# 🍽️ Cadastro de Itens do Cardápio API

Projeto desenvolvido para a **Fase 2 de avaliação**, com foco exclusivo na construção de uma **API REST para o cadastro e gerenciamento de itens do cardápio de restaurantes**, atuando como um serviço de **back-end**.

Este projeto contempla apenas o domínio de **Itens do Cardápio**, utilizando **Spring Boot**, **Clean Architecture** e **Docker**, conforme solicitado na especificação do trabalho.

---

## 📌 Objetivo do Projeto

Desenvolver um **CRUD completo para os itens vendidos no restaurante**, permitindo o gerenciamento das informações necessárias para composição de um cardápio digital.

Como se trata de um serviço de back-end, **as imagens dos pratos não são armazenadas**, sendo salvo apenas o **caminho onde a imagem estaria localizada**.

---

## 🧾 Requisitos Funcionais

### 📦 Cadastro de Itens do Cardápio

Cada item do cardápio possui os seguintes campos obrigatórios:

- **Nome** do item
- **Descrição**
- **Preço** (armazenado com `BigDecimal` para garantir precisão monetária)
- **Disponibilidade para consumo apenas no restaurante** (boolean)
- **Caminho da foto do prato** (string)

Funcionalidades disponíveis:
- Criar item do cardápio
- Listar itens do cardápio
- Buscar item por ID
- Atualizar item
- Remover item

---

## 🏗️ Arquitetura do Projeto (Clean Architecture)

O projeto foi estruturado seguindo os princípios da **Clean Architecture**, garantindo separação de responsabilidades e facilidade de manutenção.

```text
src/main/java
└── br.com.fiap.app.cardapio
    ├── domain
    │   ├── entity
    │   ├── repository
    │   └── valueobject
    ├── application
    │   ├── usecase
    │   └── service
    ├── infrastructure
    │   ├── controller
    │   ├── repository
    │   ├── config
    │   └── persistence
    └── CadastroDeCardapioApplication.java
```

### 🔹 Camadas

- **Domain**: Entidades e regras de negócio do Item do Cardápio
- **Application**: Casos de uso e serviços de aplicação
- **Infrastructure**: Controllers REST, persistência, banco de dados e configurações

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Docker & Docker Compose**
- **JUnit 5**
- **Mockito**
- **Postman**

---

## 🚀 Como Executar o Projeto

### 📋 Pré-requisitos

- Docker
- Docker Compose
- Java 21+
- Maven

---

### 🐳 Subindo o Banco de Dados

```bash
docker-compose up -d
```

O Docker Compose irá subir um container com **PostgreSQL** e criar automaticamente o banco de dados necessário.

---

### ▶️ Executando a Aplicação

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```

---

## 🔌 Endpoints da API

### 📌 Itens do Cardápio

- `POST /api/itens` — Criar item do cardápio
- `GET /api/itens` — Listar itens
- `GET /api/itens/{id}` — Buscar item por ID
- `PUT /api/itens/{id}` — Atualizar item
- `DELETE /api/itens/{id}` — Remover item

---

## 🧪 Testes

### ✅ Testes Unitários

- Desenvolvidos com **JUnit 5** e **Mockito**
- Cobertura mínima de **80%** do código

### ✅ Testes de Integração

- Testes com contexto real da aplicação
- Integração com banco PostgreSQL via Docker

---

## 📦 Collections para Teste

O projeto disponibiliza uma **collection do Postman** para validação dos endpoints do Item do Cardápio:

```text
/postman/Cadastro-Itens-Cardapio.postman_collection.json
```

---

## 🐳 Docker Compose

O projeto possui um arquivo `docker-compose.yml` configurado para subir:
- A aplicação Java
- O banco de dados PostgreSQL

---

## 📁 Repositório de Código

Repositório público para avaliação:

```text
https://github.com/seu-usuario/cadastro-itens-cardapio
```

---

## ✅ Critérios de Avaliação Atendidos

✔ CRUD completo de Itens do Cardápio  
✔ Endpoints funcionando conforme especificação  
✔ Clean Architecture aplicada  
✔ Código organizado e documentado  
✔ Docker Compose configurado  
✔ Testes unitários com cobertura ≥ 80%  
✔ Testes de integração  
✔ Documentação clara e objetiva  
✔ Collection de testes disponível  

---

## 👨‍💻 Autor

Projeto desenvolvido para fins acadêmicos, com foco em boas práticas de desenvolvimento backend com Java e Spring Boot.

---

🚀 **Projeto pronto para entrega e avaliação**

