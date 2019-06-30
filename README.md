# O que é esse projeto?

Versão simplificada de um Payment Service Provider (PSP) como o Pagar.me, esse é o projeto de uma API RESTful feita em Node + Express e que persiste transações.

# Instalação

Utilize o seguinte comando: 

`docker-compose up`

# Endpoints

GET /api/transactions

GET /api/transactions/1

GET /api/transactions/?limite=10&page=1

GET /api/transactions/?limite=1&page='1=1'

POST /api/transactions

PUT /api/transactions/1

DELETE /api/transactions/1