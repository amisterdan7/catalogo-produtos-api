# Catálogo de Produtos API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.5-green)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

> Projeto de API RESTful para gerenciar um catálogo de produtos, com funcionalidades completas de CRUD e validação.

---

### 📋 Tabela de Conteúdos
1.  [Sobre o Projeto](#-sobre-o-projeto)
2.  [Funcionalidades](#-funcionalidades)
3.  [Tecnologias](#-tecnologias)
4.  [Referência da API](#-referência-da-api)
5.  [Como Executar](#-como-executar)
6.  [Deployment](#-deployment)

---

### 📖 Sobre o Projeto
Este projeto é uma API RESTful completa desenvolvida com Java e Spring Boot. O seu principal objetivo é fornecer um serviço para criar, ler, atualizar e deletar produtos num catálogo. A aplicação está conectada a um banco de dados PostgreSQL e inclui validação de dados para garantir a integridade das informações.

---

### ✨ Funcionalidades
- **CRUD Completo:** Operações de Criar, Ler, Atualizar e Deletar para os produtos.
- **Validação de Dados:** Garante que dados como nome e preço sejam válidos antes de serem salvos.
- **Tratamento de Erros:** Fornece respostas claras para erros de validação.
- **Persistência de Dados:** Conectado a um banco de dados PostgreSQL.

---

### 🚀 Tecnologias
As seguintes tecnologias foram usadas na construção do projeto:
* **Backend:** Java 17, Spring Boot, Spring Web, Spring Data JPA
* **Banco de Dados:** PostgreSQL
* **Build:** Maven

---

### ⇔ Referência da API
A URL base para todos os endpoints é `/produtos`.

#### Criar um novo produto
```http
  POST /produtos
```
**Corpo da Requisição (JSON):**
```json
{
  "nome": "Laptop",
  "descricao": "Laptop de última geração",
  "preco": 7500.00
}
```

#### Listar todos os produtos
```http
  GET /produtos
```

#### Buscar um produto por ID
```http
  GET /produtos/{id}
```

#### Atualizar um produto
```http
  PUT /produtos/{id}
```
**Corpo da Requisição (JSON):**
```json
{
  "nome": "Laptop Pro",
  "descricao": "Laptop de última geração com mais memória",
  "preco": 8200.00
}
```

#### Deletar um produto
```http
  DELETE /produtos/{id}
```

---

### 💻 Como Executar
1.  Clone o repositório.
2.  Configure as variáveis de ambiente para o banco de dados no seu ficheiro `application.properties`.
3.  Execute a aplicação com o Maven: `./mvnw spring-boot:run`.

---

### ☁️ Deployment
A aplicação está configurada para ser implantada em plataformas de nuvem como o Railway, utilizando variáveis de ambiente para a conexão com o banco de dados.