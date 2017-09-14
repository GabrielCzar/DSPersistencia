# CURL

- Acessando API da [câmara dos deputados](https://dadosabertos.camara.leg.br/swagger/api.html)
- Lista de [deputados](https://dadosabertos.camara.leg.br/api/v2/deputados?ordem=ASC&ordenarPor=nome) ordenada por nome
```shell 
curl -X GET "https://dadosabertos.camara.leg.br/api/v2/deputados?ordem=ASC&ordenarPor=nome" -H  "accept: application/json" > deputados.json
 ```
