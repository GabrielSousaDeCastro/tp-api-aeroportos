# API de Aeroportos

## Objetivo
Este projeto consiste em uma API REST para gerenciamento de aeroportos, desenvolvida com Spring Boot.

## Tecnologias
- **Java 17**
- **Spring Boot**
- **MySQL** (Driver JDBC)
- **JUnit 5**
- **Mockito**

## Como rodar
Certifique-se de ter o Maven e o JDK 17 instalados. Na raiz do projeto, execute:

```bash
mvn spring-boot:run
```

## Endpoints

A API base é `/api/v1/aeroportos`.

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| **GET** | `/` | Listar todos os aeroportos |
| **POST** | `/` | Criar um novo aeroporto (recebe JSON) |
| **GET** | `/{iata}` | Buscar aeroporto pelo código IATA |
| **PUT** | `/{iata}` | Atualizar dados de um aeroporto |
| **DELETE** | `/{iata}` | Excluir um aeroporto |
