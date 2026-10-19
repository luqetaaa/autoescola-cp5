# Checkpoint 5 - API REST Autoescola

API REST desenvolvida em Java com Spring Boot para gerenciar alunos, instrutores, usuários e agendamentos de instruções de uma autoescola.

## Integrantes

- Lucas Rodrigues de Queiroz - RM556323 (líder)
- Victor Hugo de Paula - RM554787
- Otavio Santos de Lima Ferrao - RM556452
- Felipe Carioba - RM558447
- Djalma Andrade - RM555530

## Tecnologias

- Java 17
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Data JPA
- Spring Security, JWT e BCrypt
- MySQL e Flyway
- Maven
- Swagger / OpenAPI
- ViaCEP
- JUnit 5

## Funcionalidades dos Checkpoints

- CRUD de alunos e instrutores;
- autenticação por token JWT;
- senhas armazenadas com BCrypt;
- CRUD de usuários restrito ao perfil `ADMIN`;
- alteração da própria senha por qualquer usuário autenticado;
- agendamento de instruções com validações de negócio;
- cancelamento com motivo obrigatório e antecedência mínima de 24 horas;
- instruções canceladas deixam de ocupar o horário do instrutor.
- consumo do WebService externo ViaCEP para consulta de endereços;
- documentação automática e interface de testes com Swagger UI;
- autenticação JWT integrada ao Swagger;
- CORS configurado para aplicações locais;
- testes automatizados para Aluno, Instrutor, Usuário e Instrução.

## Configuração

Crie a base no MySQL:

```sql
CREATE DATABASE autoescola3esph;
```

Valores padrão da aplicação:

```properties
DB_URL=jdbc:mysql://localhost:3306/autoescola3esph
DB_USERNAME=root
DB_PASSWORD=fiap
JWT_SECRET=12345678
ADMIN_LOGIN=admin
ADMIN_PASSWORD=admin123
```

Os valores podem ser alterados por variáveis de ambiente. Na primeira inicialização, um administrador é criado automaticamente. As tabelas são criadas pelo Flyway.

## Execução

No Windows: `mvnw.cmd spring-boot:run`

No Linux/macOS: `./mvnw spring-boot:run`

A API executa em `http://localhost:8085`.

Para executar os testes automatizados:

```bash
./mvnw test
```

No Windows:

```bash
mvnw.cmd test
```

## Swagger / OpenAPI

Com a aplicação em execução, acesse:

```text
http://localhost:8085/swagger-ui.html
```

O documento OpenAPI em JSON está disponível em:

```text
http://localhost:8085/v3/api-docs
```

Para testar endpoints protegidos, faça login, copie o token JWT, clique em **Authorize** no Swagger e informe somente o token.

## Autenticação

Faça login em `POST /login`:

```json
{
  "login": "admin",
  "senha": "admin123"
}
```

Envie o token nas demais requisições: `Authorization: Bearer SEU_TOKEN`.

## Endpoints principais

| Método | Endpoint | Permissão | Descrição |
|---|---|---|---|
| POST | `/login` | Público | Autenticar e gerar JWT |
| POST | `/usuarios` | ADMIN | Cadastrar usuário |
| GET | `/usuarios` | ADMIN | Listar usuários |
| PUT | `/usuarios` | ADMIN | Atualizar perfil |
| DELETE | `/usuarios/{id}` | ADMIN | Excluir usuário |
| PUT | `/usuarios/minha-senha` | Autenticado | Alterar a própria senha |
| POST | `/alunos` | ADMIN | Cadastrar aluno |
| GET | `/alunos` | Autenticado | Listar alunos |
| GET | `/alunos/{id}` | Autenticado | Detalhar aluno |
| PUT | `/alunos` | ADMIN | Atualizar aluno |
| DELETE | `/alunos/{id}` | ADMIN | Inativar aluno |
| POST | `/instrutores` | ADMIN | Cadastrar instrutor |
| GET | `/instrutores` | ADMIN/USER | Listar instrutores |
| POST | `/instrucoes` | Autenticado | Agendar instrução |
| DELETE | `/instrucoes/{id}` | Autenticado | Cancelar instrução |
| GET | `/enderecos/cep/{cep}` | Autenticado | Consultar endereço no ViaCEP |

## API externa - ViaCEP

Exemplo:

```http
GET /enderecos/cep/01001000
Authorization: Bearer SEU_TOKEN
```

A resposta contém CEP, logradouro, bairro, cidade, UF, região, IBGE e DDD. O serviço valida o formato do CEP e retorna uma mensagem adequada quando o CEP não existe ou o ViaCEP está indisponível.

## CORS

A API aceita requisições de aplicações locais executadas em `localhost` ou `127.0.0.1`, em qualquer porta, usando os métodos `GET`, `POST`, `PUT`, `DELETE` e `OPTIONS`.

## Testes automatizados

Os exemplos estão em `src/test/java` e cobrem:

- cadastro e inativação de aluno;
- cadastro e inativação de instrutor;
- alteração de perfil e senha de usuário;
- criação e cancelamento de instrução.

## Exemplos de requisições

Cadastrar usuário:

```json
{
  "login": "usuario1",
  "senha": "senha123",
  "perfil": "USER"
}
```

Alterar a própria senha:

```json
{
  "senhaAtual": "senha123",
  "novaSenha": "novaSenha123"
}
```

Cadastrar aluno:

```json
{
  "nome": "João da Silva",
  "email": "joao@email.com",
  "telefone": "11999999999",
  "cpf": "12345678901",
  "endereco": {
    "logradouro": "Avenida Paulista",
    "numero": "1000",
    "complemento": "Apto 10",
    "bairro": "Bela Vista",
    "cidade": "São Paulo",
    "uf": "SP",
    "cep": "01310-100"
  }
}
```

Agendar instrução:

```json
{
  "id_aluno": 1,
  "id_instrutor": 1,
  "data_hora": "20/09/2026 - 10:00"
}
```

Cancelar instrução em `DELETE /instrucoes/1`:

```json
{
  "motivo": "ALUNO_DESISTIU"
}
```

Motivos aceitos: `ALUNO_DESISTIU`, `INSTRUTOR_CANCELOU` e `OUTROS`.
