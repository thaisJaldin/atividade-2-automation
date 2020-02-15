  @run
  Feature: Test a simple CRUD of Process

    Background:
      Given A user are on Process page

    Scenario: POST - Create a new Item with the next information
      And field "vara" with value "33"
      And field "numero_processo" with value "4578"
      And field "natureza" with value "Guarda"
      And field "partes" with value "Marco x Jose"
      And field "urgente" with value "S"
      And field "arbitramento" with value "N"
      And field "assistente_social" with value "Agapito"
      And field "data_entrada" with value "2020-02-15"
      And field "data_saida" with value "2020-02-28"
      And field "data_agendamento" with value "2020-02-17"
      And field "status" with value "Entrevista Agendada"
      And field "observacao" with value "Nada a observar"
      When user press Save
      Then A message should be display "The Item was added"
      And The field "observacao" by value "Nada a observar" should be return for user

   Scenario Outline:  GET - Display the list of exist Items
      And list of ids should be "<id>"
      When user click on view
      Then A message should be display "<message>"

      Examples:
      | id | message |
      | 40 | Success |
      | 35454 | Not Found |

    Scenario: PUT - Update a Item with the next information
      And view the Item with id "40"
      And field "vara" with value "34"
      And field "status" with value "Agendada"
      And field "observacao" with value "Pronta"
      When user press Update
      Then A message should be display "Success"
      And The field "vara" by value "34" should be return for user

    Scenario: DELETE - Remove a Item
      And view the Item with id "11"
      When user press Delete
      And user click on view
      Then A message should be display "Not Found"