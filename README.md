# Conta Bancária - API RESTful para Cooperativa de Crédito

API RESTful desenvolvida em Java com Spring Boot para o gerenciamento de correntistas, contas bancárias (Conta Corrente e Conta Poupança) e operações financeiras.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** MySQL
* **Gerenciador de Dependências:** Maven

---

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos
* Java 17 (ou superior) instalado
* Maven instalado
* Servidor MySQL em execução na porta `3306`

### Passos
1. **Clonar o repositório:**
   ```bash
   git clone [https://github.com/victormaximinodesouza/desafio-pactomais.git](https://github.com/victormaximinodesouza/desafio-pactomais.git)
   cd desafio-pactomais****

* ** Testes de Endpoints: Correntistas
Cadastrar Correntista (POST /api/correntistas)

Exemplo de JSON (Body):

JSON
{
  "nome": "Victor Maximino",
  "documento": "12345678900",
  "contato": "victor@email.com"
}
Listar Correntistas (GET /api/correntistas)

Retorna a lista de todos os correntistas registados. **
