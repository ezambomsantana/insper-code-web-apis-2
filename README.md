# Web & APIs

**Data:** 06/10  
**Tema:** Web & APIs

## Objetivos da aula

Ao final da aula, você deverá ser capaz de:

- Explicar a comunicação entre cliente e servidor;
- Reconhecer o papel de DNS, TCP/IP, HTTP e HTTPS em uma requisição web;
- Interpretar requisições e respostas HTTP;
- Usar os conceitos básicos de APIs REST e JSON;
- Identificar parâmetros de caminho, *query parameters*, *headers* e *body*;
- Diferenciar autenticação de autorização;
- Entender, em nível introdutório, tokens, JWT e CORS;
- Compreender a separação de responsabilidades em uma arquitetura em camadas.

---

## 1. Cliente e servidor

Uma aplicação web normalmente funciona no modelo **cliente-servidor**:

- O **cliente** é quem solicita algo: navegador, aplicativo móvel, front-end ou Postman.
- O **servidor** recebe a solicitação, executa a lógica necessária e devolve uma resposta.
- A **API** é a interface pela qual o cliente conversa com o servidor.

Exemplo: ao abrir a tela de alunos em um sistema, o front-end pode pedir à API a lista de alunos. A API consulta os dados e devolve uma resposta em JSON.

---

## 2. Como a requisição encontra o servidor?

Antes de falar de APIs REST, vale lembrar rapidamente os componentes envolvidos na comunicação.

1. **DNS:** traduz um nome, como `api.exemplo.com`, para um endereço IP.
2. **TCP/IP:** permite que computadores troquem dados pela rede. O TCP ajuda a tornar essa comunicação confiável.
3. **HTTP:** define o formato das mensagens de requisição e resposta entre cliente e servidor.
4. **HTTPS:** é HTTP protegido por TLS, que criptografa a comunicação e ajuda a garantir a identidade do servidor.

Em resumo:

> DNS encontra o servidor; TCP/IP transporta os dados; HTTP organiza a conversa; HTTPS protege essa conversa.

---

## 3. HTTP na prática

Uma requisição HTTP possui, entre outros elementos:

- **Método:** a ação desejada, como `GET` ou `POST`;
- **URL:** o endereço do recurso;
- **Headers:** metadados da requisição;
- **Body:** dados enviados ao servidor, quando necessário.

Exemplo:

```http
POST /alunos HTTP/1.1
Host: api.exemplo.com
Content-Type: application/json

{
  "nome": "Ana",
  "email": "ana@exemplo.com"
}
```

Uma resposta também possui status, headers e, frequentemente, um body:

```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "id": 42,
  "nome": "Ana",
  "email": "ana@exemplo.com"
}
```

### Métodos HTTP mais comuns

| Método | Uso típico |
|---|---|
| `GET` | Buscar dados |
| `POST` | Criar um novo recurso |
| `PUT` | Substituir um recurso existente |
| `PATCH` | Alterar parcialmente um recurso |
| `DELETE` | Remover um recurso |

### Status codes importantes

| Código | Significado | Exemplo |
|---|---|---|
| `200 OK` | Requisição atendida com sucesso | Consulta realizada |
| `201 Created` | Recurso criado | Novo aluno cadastrado |
| `204 No Content` | Sucesso sem corpo de resposta | Exclusão realizada |
| `400 Bad Request` | Dados inválidos enviados pelo cliente | Campo obrigatório ausente |
| `401 Unauthorized` | Não autenticado | Token ausente ou inválido |
| `403 Forbidden` | Autenticado, mas sem permissão | Usuário comum tentando acessar área administrativa |
| `404 Not Found` | Recurso não encontrado | Aluno inexistente |
| `500 Internal Server Error` | Erro inesperado no servidor | Falha não tratada pela aplicação |

---

## 4. APIs REST

**REST não é um protocolo.** É um estilo arquitetural usado, em geral, para organizar APIs que utilizam HTTP.

Em uma API REST, pensamos em **recursos**. Para uma aplicação que administra alunos, `alunos` é um recurso.

| Operação | Endpoint | Significado |
|---|---|---|
| `GET` | `/alunos` | Listar alunos |
| `GET` | `/alunos/42` | Buscar o aluno de id 42 |
| `POST` | `/alunos` | Criar um aluno |
| `PATCH` | `/alunos/42` | Alterar dados do aluno 42 |
| `DELETE` | `/alunos/42` | Excluir o aluno 42 |

### Path parameter

Identifica um recurso específico na própria URL.

```text
GET /alunos/42
```

Nesse caso, `42` é o identificador do aluno.

### Query parameter

Permite filtrar, ordenar ou paginar resultados.

```text
GET /alunos?curso=Ciencia-da-Computacao&pagina=2
```

Aqui, `curso` e `pagina` são *query parameters*.

### Body

É o conteúdo enviado ao servidor, normalmente para criar ou alterar dados.

```json
{
  "nome": "Ana",
  "email": "ana@exemplo.com"
}
```

### Headers

São metadados enviados junto da mensagem. Exemplos comuns:

```http
Content-Type: application/json
Accept: application/json
Authorization: Bearer <token>
```

---

## 5. JSON e o contrato da API

**JSON** é um formato textual muito usado para representar dados trocados entre cliente e servidor.

```json
{
  "id": 42,
  "nome": "Ana",
  "ativo": true
}
```

O JSON não é REST: ele é apenas uma forma comum de representar os dados de uma API REST.

O **contrato da API** define o que o cliente pode enviar e o que pode esperar receber: endpoints, métodos, parâmetros, estrutura do JSON e códigos de resposta.

---

## 6. Autenticação e autorização

- **Autenticação:** responde à pergunta “quem é você?”.
- **Autorização:** responde à pergunta “o que você pode fazer?”.

Exemplo: uma pessoa faz login e prova sua identidade (autenticação). Depois, o sistema verifica se ela possui permissão para excluir um aluno (autorização).

Existem vários tipos de autenticação, entre elas a `Basic` e a baseada em `token`.

---

## 7. CORS

**CORS** (*Cross-Origin Resource Sharing*) é um mecanismo do navegador que controla se um site de uma origem pode chamar uma API de outra origem.

Por exemplo, um front-end em `http://localhost:3000` tentando chamar uma API em `http://localhost:8080` está fazendo uma requisição entre origens diferentes.

Para permitir isso, a API pode responder com um header como:

```http
Access-Control-Allow-Origin: http://localhost:3000
```

Importante: CORS é uma regra aplicada principalmente pelos navegadores. Ele não substitui autenticação, autorização ou outras medidas de segurança da API.

---

## 8. Arquitetura em camadas

Uma forma comum de organizar uma aplicação Java/Spring é separar responsabilidades em camadas:

| Camada | Responsabilidade principal |
|---|---|
| **Controller** | Recebe requisições HTTP e constrói respostas HTTP |
| **Service** | Implementa regras de negócio |
| **Repository** | Acessa e persiste dados no banco |

Fluxo típico de uma requisição:

```text
Cliente → Controller → Service → Repository → Banco de dados
Cliente ← Controller ← Service ← Repository ← Banco de dados
```

Essa divisão ajuda a manter o código mais organizado, testável e fácil de evoluir.
