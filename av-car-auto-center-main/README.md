# AV Car Auto Center

Sistema de gestão para auto center desenvolvido com Spring Boot e PostgreSQL.

## Tecnologias

- Java 21
- Spring Boot 3.3.5
- Spring Data JPA
- PostgreSQL
- Lombok
- MapStruct

## Arquitetura

Arquitetura em camadas por domínio (`business`) com núcleo genérico (`core`):

```
src/main/java/com/ads3/auto_center/
├── core/
│   ├── controllers/     # GenericController (CRUD base)
│   ├── domains/         # BaseModel
│   ├── dtos/            # BaseDTO
│   ├── exceptions/      # Exceções tipadas e handler global
│   ├── helpers/         # IGenericMapper
│   ├── repositories/    # IGenericRepository
│   ├── services/        # GenericService
│   └── validations/     # GenericValidation
└── business/
    ├── marca/
    ├── modelo/
    ├── veiculo/
    ├── pessoa/
    ├── cliente/
    ├── pessoaFisica/
    ├── pessoaJuridica/
    ├── colaborador/
    ├── funcaoColaborador/
    ├── ordemDeServico/
    ├── servicoInterno/
    ├── itemServicoInterno/
    ├── servicoExterno/
    ├── itemServicoExterno/
    ├── peca/
    ├── itemPedidoPeca/
    └── fornecedor/
```

## Modelo de Herança

```
PessoaModel (JOINED)
├── ClienteModel
│   ├── PessoaFisicaModel  (CPF, nome, data de nascimento)
│   └── PessoaJuridicaModel (CNPJ, razão social, nome fantasia)
└── ColaboradorModel (CPF, nome, salário, funções)
```

## Configuração

### Pré-requisitos

- Java 21+
- PostgreSQL 14+
- Maven 3.8+

### Banco de dados

Crie o banco antes de iniciar a aplicação:

```sql
CREATE DATABASE bd_car_repair;
```

### application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bd_car_repair
spring.datasource.username=postgres
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### Executar

```bash
./mvnw spring-boot:run
```

A aplicação sobe na porta **8080** e cria as tabelas automaticamente.

## Endpoints

### Marcas
| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/marcas` | Listar (paginado) |
| GET | `/marcas/{id}` | Buscar por ID |
| POST | `/marcas` | Criar |
| PUT | `/marcas/{id}` | Atualizar |
| DELETE | `/marcas/{id}` | Excluir (soft delete) |

### Modelos
| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/modelos` | Listar (paginado) |
| GET | `/modelos/{id}` | Buscar por ID |
| POST | `/modelos` | Criar |
| PUT | `/modelos/{id}` | Atualizar |
| DELETE | `/modelos/{id}` | Excluir |

### Veículos
| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/veiculos` | Listar (paginado) |
| GET | `/veiculos/{id}` | Buscar por ID |
| POST | `/veiculos` | Criar |
| PUT | `/veiculos/{id}` | Atualizar |
| DELETE | `/veiculos/{id}` | Excluir |

### Clientes
| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/clientes/pessoas-fisicas` | Listar pessoas físicas |
| GET | `/clientes/pessoas-fisicas/{id}` | Buscar PF por ID |
| POST | `/clientes/pessoas-fisicas` | Criar pessoa física |
| PUT | `/clientes/pessoas-fisicas/{id}` | Atualizar pessoa física |
| DELETE | `/clientes/pessoas-fisicas/{id}` | Excluir pessoa física |
| GET | `/clientes/pessoas-juridicas` | Listar pessoas jurídicas |
| GET | `/clientes/pessoas-juridicas/{id}` | Buscar PJ por ID |
| POST | `/clientes/pessoas-juridicas` | Criar pessoa jurídica |
| PUT | `/clientes/pessoas-juridicas/{id}` | Atualizar pessoa jurídica |
| DELETE | `/clientes/pessoas-juridicas/{id}` | Excluir pessoa jurídica |
| GET | `/clientes/cpf/{cpf}` | Buscar cliente por CPF |
| GET | `/clientes/cnpj?cnpj={cnpj}` | Buscar cliente por CNPJ |
| GET | `/clientes/placa/{placa}` | Buscar cliente por placa do veículo |

### Colaboradores
| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/colaboradores` | Listar (paginado) |
| GET | `/colaboradores/{id}` | Buscar por ID |
| POST | `/colaboradores` | Criar |
| PUT | `/colaboradores/{id}` | Atualizar |
| DELETE | `/colaboradores/{id}` | Excluir |
| GET | `/funcoes-colaborador` | Listar funções |
| POST | `/funcoes-colaborador` | Criar função |
| PUT | `/funcoes-colaborador/{id}` | Atualizar função |
| DELETE | `/funcoes-colaborador/{id}` | Excluir função |

### Ordens de Serviço
| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/ordens-de-servico` | Listar (paginado) |
| GET | `/ordens-de-servico/{id}` | Buscar por ID |
| POST | `/ordens-de-servico` | Criar (status inicia em ORCAMENTO) |
| PUT | `/ordens-de-servico/{id}` | Atualizar / avançar status |
| DELETE | `/ordens-de-servico/{id}` | Excluir (bloqueado se FINALIZADO) |

**Fluxo de status:** `ORCAMENTO → EXECUCAO → PAGAMENTO → FINALIZADO`

### Serviços e Itens
| Método | URL | Descrição |
|--------|-----|-----------|
| GET/POST/PUT/DELETE | `/servicos-internos` | CRUD serviços internos |
| GET/POST/PUT/DELETE | `/itens-servico-interno` | CRUD itens de serviço interno |
| GET/POST/PUT/DELETE | `/servicos-externos` | CRUD serviços externos |
| GET/POST/PUT/DELETE | `/itens-servico-externo` | CRUD itens de serviço externo |
| GET/POST/PUT/DELETE | `/pecas` | CRUD peças |
| GET/POST/PUT/DELETE | `/itens-pedido-peca` | CRUD itens de pedido de peça |

### Fornecedores
| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/fornecedores` | Listar (paginado) |
| GET | `/fornecedores/{id}` | Buscar por ID |
| POST | `/fornecedores` | Criar |
| PUT | `/fornecedores/{id}` | Atualizar |
| DELETE | `/fornecedores/{id}` | Excluir |

## Paginação e Ordenação

Todos os endpoints de listagem suportam paginação e ordenação via query params:

```
GET /marcas?page=0&size=10&sort=nomeMarca,asc
```

## Tratamento de Erros

| Tipo | Status | Quando ocorre |
|------|--------|---------------|
| `FieldValidationException` | 400 | Campo obrigatório ausente ou inválido |
| `RuleValidationException` | 409 | Violação de regra de negócio (ex: CPF duplicado) |
| `BusinessException` | 404/500 | Registro não encontrado ou erro de processo |

## Funcionalidades

- Soft delete em todos os registros (campo `ativo`)
- Preenchimento automático de `dataAbertura` nas OS e `dataCadastro` nos clientes
- Busca de cliente por CPF, CNPJ ou placa do veículo
- Proteção contra exclusão de OS finalizada
- Validação de duplicidade (CPF, CNPJ, placa, nome da marca)