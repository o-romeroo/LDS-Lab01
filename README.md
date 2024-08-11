# LDS-Lab01

### Sistema de matrículas

O processo começa com o setor de pedidos ou o setor de estoque da empresa comunicando que ou um pedido ou uma compra está em andamento, após isso esperam a aprovação ou não da ação. Caso a ação seja aprovada, o processo é finalizado. Caso o contrário, é informado ao devido setor que a ação não foi concluída com êxito, e o processo finalizado.

![Exemplo de um Modelo BPMN do Processo de saldo comercial](images/Processo_de_Tesouraria.png "Modelo BPMN do Processo de Tesouraria.")


#### Detalhamento das atividades

**Passar as informações da empresa para que o cliente realize o pagamento no prazo estipulado**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| [Nome do campo] | [tipo de dados]  |                |                   |
| Enviar informações pagamento | E-mail |         |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| [Nome do botão/link] | Atividade/processo de destino  | (default/cancel/  ) |
| Enviar informações pagamento |         |  default       |                 |   

**Autorizar compra e Informar ao setor de estoque que a compra foi realizada**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| [Nome do campo] | [tipo de dados]  |                |                   |
| Aprovar compra  |                  |      |             |


| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| [Nome do botão/link] | Atividade/processo de destino  | (default/cancel/  ) |
| Aprovar compra   |  Fim do processo |  default       |



**Cancelar compra e Informar ao setor de estoque que a compra não pôde ser realizada**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| [Nome do campo] | [tipo de dados]  |                |                   |
| Cancelar compra |  |      |             |


| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| [Nome do botão/link] | Atividade/processo de destino  | (default/cancel/  ) |
| Cancelar compra      |  Fim do processo               |  default       |
