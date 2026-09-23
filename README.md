# 🏦 Conta Bancária API

API REST desenvolvida em **Java 17 e Spring Boot 3** para gerenciamento de correntistas e contas bancárias.

Projeto desenvolvido com foco em prática de **Java, Spring Boot, APIs REST, JPA e banco de dados**.

## 🛠️ Tecnologias

* Java 17
* Spring Boot 3
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Postman

## 📂 Estrutura do Projeto

```text
src/main/java
├── controller
├── service
├── repository
├── entity
├── dto
└── exception
```

## ⚙️ Funcionalidades

* Cadastro de correntistas
* Listagem de correntistas
* Modelagem de contas bancárias
* Conta Corrente e Conta Poupança
* Relacionamento entre correntistas e contas
* Validação de dados
* Tratamento de exceções
* Persistência no MySQL

## 🚀 Como executar

### 1. Clone o projeto

```bash
git clone https://github.com/victormaximinodesouza/desafio-pactomais.git
cd desafio-pactomais
```

### 2. Configure o banco

Crie o banco no MySQL:

```sql
CREATE DATABASE pacto_mais;
```

Configure o arquivo:

```text
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pacto_mais?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
```

### 3. Execute a aplicação

Execute a classe:

```text
DesafioPactoMaisApplication.java
```

A API estará disponível em:

```text
http://localhost:8080
```

## 📌 Endpoints

### Correntistas

**POST** `/api/correntistas`

```json
{
  "nome": "Victor Maximino",
  "documento": "12345678900",
  "contato": "victor@email.com"
}
```

**GET** `/api/correntistas`

Retorna todos os correntistas cadastrados.

## 🧠 Conceitos praticados

* Programação Orientada a Objetos
* Herança e abstração
* API REST
* Spring Boot
* JPA/Hibernate
* Relacionamentos entre entidades
* Arquitetura em camadas
* DTOs
* Tratamento de exceções
* MySQL

## 🧪 Testes

Os endpoints foram testados utilizando **Postman**.


## 🔜 Próximos passos

- [ ] Implementar operações financeiras
- [ ] Swagger/OpenAPI
- [ ] Testes com JUnit e Mockito
- [ ] Docker
- [ ] Deploy na AWS (EC2)
- [ ] Banco de dados MySQL utilizando Amazon RDS
