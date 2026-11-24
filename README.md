# Baozi Store

API REST para controlar clientes, produtos e pedidos de uma loja de pão chinês.
Trabalho de Desenvolvimento Web Back-End de Victor Félix Sabino Patriota,
Engenharia de Software, RU 5278236.

## Executar

Requisitos: JDK 17 ou superior e Maven 3.6.3 ou superior.

```sh
mvn spring-boot:run
```

A API atende em `http://localhost:8080`. O banco relacional H2 é criado
automaticamente e grava os dados em `./data/baozi`.

## Endpoints

| Recurso | Criar | Listar | Consultar | Excluir |
| --- | --- | --- | --- | --- |
| Cliente | `POST /clientes` | `GET /clientes` | `GET /clientes/{id}` | `DELETE /clientes/{id}` |
| Produto | `POST /produtos` | `GET /produtos` | `GET /produtos/{id}` | `DELETE /produtos/{id}` |
| Pedido | `POST /pedidos` | `GET /pedidos` | `GET /pedidos/{id}` | `DELETE /pedidos/{id}` |

Os endpoints usam JSON. Criação retorna 201, consulta retorna 200 e exclusão
retorna 204. Entrada inválida retorna 400, registro inexistente retorna 404
e conflito de disponibilidade ou integridade referencial retorna 409.

## Postman

Importe `postman/Baozi.postman_collection.json`. Execute as requisições na ordem
da coleção: três POST, seis GET e três DELETE. Os POST guardam automaticamente
os IDs das respostas. Exclua o pedido antes do cliente e do produto.

O estudo de caso usa o cliente `VICTOR FÉLIX SABINO PATRIOTA5278236`, o produto
`Baozi de porco` por R$ 8,50 e um pedido de três unidades.

## Organização

- `model`: entidades JPA e relacionamentos com chaves estrangeiras.
- `repository`: interfaces Spring Data JPA.
- `controller`: endpoints REST, validações e tratamento de conflitos.

`estoque` representa disponibilidade (Boolean). Cada pedido contém um cliente,
um produto e uma quantidade maior ou igual a um.

## Validação

Compilação concluída com Java 21 e Maven 3.9.11. Foram realizadas 19 verificações
HTTP, cobrindo as três entidades, referências inexistentes, quantidade inválida,
integridade referencial e exclusão. A coleção também contém verificações dos
códigos de resposta para seus 12 endpoints.

Consulte `README.txt` para as instruções detalhadas e os campos das entidades.
