Rastreamento de pedido

O logistica-service é um microsserviço responsável pela gestão da entrega de produtos dentro de um sistema de pedidos. Ele interage diretamente com outros microsserviços, como o pedido-service e o estoque-service, para garantir que os pedidos sejam processados e entregues corretamente. Abaixo estão os principais fluxos e funcionalidades implementadas neste serviço.

Funcionalidades Principais
1. Criação e Verificação de Pedidos
O cliente seleciona o produto desejado no sistema de pedidos.
Após a confirmação do pedido, o pedido-service cria o pedido.
O sistema então faz uma solicitação ao estoque-service para verificar se o produto está disponível em estoque.
Se o item estiver disponível em quantidade suficiente, o estoque aprova a requisição e encaminha o pedido para a logística.
2. Gestão do Estoque
O estoque-service é notificado sempre que um pedido é confirmado.
Ao receber a requisição, o serviço de estoque verifica:
Se o produto existe no catálogo.
Se há quantidade suficiente para atender ao pedido.
Caso o estoque tenha disponibilidade, o serviço de estoque processa a requisição e envia os detalhes do pedido para o logistica-service.
3. Gestão Logística
O logistica-service recebe o pedido processado e grava as informações no banco de dados.
Ele acompanha o status da entrega do produto e disponibiliza essas informações ao cliente.
Durante a entrega, o status da rota é atualizado, permitindo ao cliente acompanhar o progresso em tempo real.
Estrutura de Status da Entrega
O logistica-service utiliza diferentes status para monitorar o progresso da entrega:

EM PROCESSO: O pedido está sendo preparado para envio.
EM TRANSITO: O pedido já foi despachado e está a caminho do destino.
ENTREGUE: O pedido foi entregue ao cliente.
Tecnologias Utilizadas
Spring Boot: Framework principal para a criação do microsserviço.
RabbitMQ: Utilizado para comunicação entre microsserviços (pedido, estoque e logística) por meio de filas de mensagens.
JPA / Hibernate: Persistência de dados no banco de dados H2 em memória.
H2 Database: Banco de dados leve e fácil de configurar, utilizado para testes e desenvolvimento local.
Jackson: Utilizado para conversão de mensagens JSON trocadas via RabbitMQ.
Como Executar o Projeto
Pré-requisitos
Java 17 ou superior
RabbitMQ rodando localmente (porta padrão: 5672)
Maven para gerenciamento de dependências


