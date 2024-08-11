# LDS-Lab01

### Sistema de matrículas

O sistema de matrículas é uma ferramenta abrangente que não apenas facilita o processo de matrícula e administração para a universidade, mas também melhora a experiência de alunos e professores. Ele é projetado para ser seguro, confiável e fácil de usar, garantindo que todas as operações acadêmicas possam ser gerenciadas de forma eficiente e eficaz.

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
