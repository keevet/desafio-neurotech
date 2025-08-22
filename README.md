# 📘 Neurotech Desafio API

API desenvolvida em **Spring Boot 3** com **Java 17**, para gerenciar produtos com autenticação via **JWT**, documentação interativa via **Swagger/OpenAPI** e suporte a múltiplos ambientes (**H2** em memória para testes/dev e **MySQL** em produção via RDS**).

---

## 🚀 Link do Projeto em Produção

A aplicação está **deployada em uma instância EC2** na AWS, utilizando **MySQL em um banco RDS**.  
O Swagger UI pode ser acessado em:

👉 [**Swagger UI - Neurotech Desafio**](http://ec2-56-124-36-169.sa-east-1.compute.amazonaws.com/swagger-ui/index.html)

> Esse é o ponto de entrada principal para explorar todos os endpoints da API em produção.

---

## ✨ Funcionalidades

- CRUD completo de **Produtos**:
  - Listagem paginada, com filtro por nome e ordenação por preço.
  - Busca por ID.
  - Criação com validações (nome obrigatório, preço > 0, estoque ≥ 0).
  - Atualização parcial (campos não enviados não são alterados).
  - Exclusão com retorno `204 No Content`.

- **Autenticação JWT**:
  - Login em `/auth/login` com usuário/senha.
  - Geração de token JWT assinado.
  - Endpoints de produtos protegidos (uso de `Bearer <token>`).

- **Validações e Exceptions customizadas**:
  - `400` → JSON inválido ou parâmetros incorretos.
  - `401` → Não autenticado.
  - `404` → Produto não encontrado ou rota inexistente.
  - `409` → Violação de integridade.
  - `422` → Violação de validação.

- **Swagger UI** integrado:
  - Documentação em `/swagger-ui`.
  - Redirecionamento automático de `/` → Swagger UI.

---

## 🛠️ Tecnologias

- **Java 17**
- **Spring Boot 3**
  - Spring Web
  - Spring Data JPA
  - Spring Security (JWT)
  - Spring Validation
- **Banco de Dados**
  - H2 (test/dev)
  - MySQL (AWS RDS em produção)
- **JWT** (`jjwt`)
- **Swagger/OpenAPI** (`springdoc-openapi`)

---

## 📝 Padrão de Commits

Este projeto segue o padrão descrito em:  
�� **[https://github.com/iuricode/padroes-de-commits](https://github.com/iuricode/padroes-de-commits)**

**Exemplos**

- `feat: adicionar criação de produto`
    
- `fix: corrigir validação de preço`
    
- `docs: atualizar README`
    
- `refactor: reorganizar ProdutoService`
    
- `chore: ajustar docker-compose`

## 👤 Usuário de Acesso (In-Memory)

Para autenticar e gerar um **JWT** no endpoint `/auth/login`, utilize as credenciais abaixo:

```plaintext
Username: admin
Password: neurotech

```
