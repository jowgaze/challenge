# God Level Coder Challenge

Uma aplicação desenvolvida para o desafio da Nola.

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-%23F7B93E.svg?&style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-%23A6B5E4.svg?&style=for-the-badge&logo=springboot&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-%232496ED.svg?&style=for-the-badge&logo=docker&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-%23336791.svg?&style=for-the-badge&logo=postgresql&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-%23DD0031.svg?&style=for-the-badge&logo=angular&logoColor=white)
![Insomnia](https://img.shields.io/badge/Insomnia-%23F0F0F0.svg?&style=for-the-badge&logo=insomnia&logoColor=black)

## Deploy
Por usar plataformas gratuitas para o deploy, o banco de dados foi reduzido.

**Importante:** Ao acessar o deploy, o backend demora um pouco para iniciar, já que entra em hibernação caso não tenha acessos por um peridodo.

- [Backend](https://mushy-kellyann-jowgaze-cf011411.koyeb.app/swagger-ui/index.html)
- [Frontend](https://frontend-deploy-topaz-theta.vercel.app)

## Estrutura do Projeto

- **backend/**: Microsserviço Spring Boot com endpoints para produtos mais vendidos, ticket médio, balanços, lojas e canais.
- **frontend/**: Aplicação Angular consumindo a API do backend.
- **docker-compose.yml**: Orquestração de containers para backend, frontend, banco de dados e data-generator.
- **data-generator/**: Script para popular o banco de dados.

## Como Executar

```bash
git clone https://github.com/jowgaze/challenge.git
cd challenge

docker compose down -v 2>/dev/null || true
docker compose build --no-cache data-generator
docker compose up -d postgres
docker compose up -d backend frontend
docker compose run --rm data-generator
```

  - O frontend ficará disponível em [localhost:4200](http://localhost:4200)
  - O backend ficará disponível em [localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Endpoints
## Endpoints Principais

### Produtos
| Método | Endpoint | Descrição | Request Body | Response |
|--------|----------|-----------|--------------|----------|
| POST | `/challenge/api/top-products/{storeId}/{channelId}` | Retorna os produtos mais vendidos de uma loja e canal | `IntervalDto` | Array de `TopProductResponseDto` |

---

### Tickets
| Método | Endpoint | Descrição | Request Body | Response |
|--------|----------|-----------|--------------|----------|
| POST | `/challenge/api/ticket/{storeId}/{channelId}` | Retorna tickets vendidos em uma loja e canal | `IntervalDto` | Array de `TicketDto` |

---

### Relatórios
| Método | Endpoint | Descrição | Request Body | Response |
|--------|----------|-----------|--------------|----------|
| POST | `/challenge/api/report` | Gera um relatório em PDF ou Excel | `ReportRequestDto` | Arquivo em bytes |

---

### Balanço
| Método | Endpoint | Descrição | Request Body | Response |
|--------|----------|-----------|--------------|----------|
| POST | `/challenge/api/balance/{storeId}` | Retorna balanço financeiro de uma loja | `IntervalDto` | Array de `BalanceResponseDto` |

---

### Lojas
| Método | Endpoint | Descrição | Request Body | Response |
|--------|----------|-----------|--------------|----------|
| GET | `/challenge/api/stores` | Retorna todas as lojas cadastradas | N/A | Array de `StoreResponseDto` |

---

### Clientes Inativos
| Método | Endpoint | Descrição | Request Body | Response |
|--------|----------|-----------|--------------|----------|
| GET | `/challenge/api/inactive-customers/{storeId}/{channelId}` | Lista clientes inativos por loja e canal | N/A | Array de `InactiveCustomerDto` |

---

### Canais
| Método | Endpoint | Descrição | Request Body | Response |
|--------|----------|-----------|--------------|----------|
| GET | `/challenge/api/channels` | Lista todos os canais disponíveis | N/A | Array de `ChannelResponseDto` |

Desenvolvido com 💻 e ☕ por [jowgaze](https://github.com/jowgaze)



