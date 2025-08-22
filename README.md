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


▶️ Executando o Projeto 

### Pré-requisitos
- **Java 17**
- **Maven 3.9+**
- **Docker** **Engine**
- Banco de dados:
  - **H2 em memória** (default, para testes/dev)  
  - ou **MySQL** (para execução em modo prod)
### Passos
1. **Clonar o repositório
   ```bash
   git clone https://github.com/seu-usuario/neurotech-desafio.git
   cd neurotech-desafio
   ```
2. **Alterar o compose para test**

```yaml
version: "3.9"

services:

  api:

    build:

      context: .

      dockerfile: Dockerfile

    image: neurotech/desafio-api:latest

    container_name: desafio-api

    ports:

      - "8080:8080"

    environment:

      SPRING_PROFILES_ACTIVE: test -- Altere aqui

      JWT_SECRET: ${JWT_SECRET:-e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855}

      JWT_EXPIRATION_MS: 3600000

      CORS_ORIGINS: "http://localhost:3000,http://localhost:5173"

      SERVER_PORT: 8080

      TZ: America/Recife

    restart: unless-stopped
```

3. E então build o projeto
```
docker compose build && docker compose up -d 
```