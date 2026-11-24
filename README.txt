BAOZI STORE — TRABALHO DE DESENVOLVIMENTO WEB BACK-END
Aluno: VICTOR FÉLIX SABINO PATRIOTA | RU: 5278236
Curso: Engenharia de Software

PRÉ-REQUISITOS
JDK 17 ou superior e Maven 3.6.3 ou superior.
Abra esta pasta em uma IDE Java como IntelliJ ou Eclipse e importe pom.xml como projeto Maven.
No terminal, dentro desta pasta: mvn spring-boot:run
A API atende em http://localhost:8080. Não há página HTML na raiz.
O H2 grava os dados na pasta data e não exige instalar MySQL.

ORGANIZAÇÃO
model: entidades JPA e relacionamentos.
repository: interfaces Spring Data JPA.
controller: endpoints REST, validação e tratamento de conflito.
Pedido recebe clienteId e produtoId; o banco possui chaves estrangeiras reais.
estoque é booleano (disponibilidade), conforme sugerido no enunciado.

POSTMAN
Importe postman/Baozi.postman_collection.json.
Execute as requisições na ordem apresentada: três POST, seis GET, três DELETE.
Cada POST guarda automaticamente o ID retornado para as próximas requisições.
Tire os prints ANTES de executar os DELETE. Tire também prints dos DELETE.
Capture a URL, método, corpo enviado e resposta/status visíveis.
Exclua pedidos antes de clientes e produtos, por causa das chaves estrangeiras.
Uma referência inexistente retorna 404, entrada inválida retorna 400,
produto indisponível ou exclusão de cliente/produto vinculado retorna 409.

ESTUDO DE CASO
A Baozi Store é uma pequena loja que vende pão chinês. Para organizar o negócio,
foi desenvolvida uma API para controlar clientes, produtos e pedidos.
O cliente VICTOR FÉLIX SABINO PATRIOTA5278236 realizou seu cadastro em 24/11/2026.
O produto Baozi de porco é vendido por unidade pelo preço de R$ 8,50 e está disponível.
O cliente realizou um pedido de 3 unidades. O sistema registra o cliente,
o produto e a quantidade solicitada, facilitando o controle da loja.

ENDPOINTS (para /clientes, /produtos e /pedidos)
POST /recurso: cria (201 e cabeçalho Location).
GET /recurso: lista (200).
GET /recurso/{id}: consulta (200 ou 404).
DELETE /recurso/{id}: exclui (204, 404 ou 409).

CAMPOS
Cliente: id Long, nome String, clienteDesde LocalDate.
Produto: id Long, nome String, preco BigDecimal, estoque Boolean.
Pedido: id Long, clienteId Long, produtoId Long, quantidade Integer.
IDs são gerados pelo banco; não precisam ser enviados no POST.

VALIDAÇÃO
Compilação Java 21/Maven 3.9.11 concluída com sucesso.
19 verificações HTTP passaram: criação, consulta, listagem, validações e exclusão.
A coleção do Postman verifica os códigos de resposta e mantém os IDs retornados.

REPOSITÓRIO
https://github.com/VfelixV/baozi-store

