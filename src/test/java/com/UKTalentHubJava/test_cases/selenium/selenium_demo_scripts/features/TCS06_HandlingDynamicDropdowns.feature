Feature: The user can enter text into the input and generate options inside the dropdown based on that search
  and select an option

  Scenario: The user select an option from the dynamic dropdown
    Given I have navigated to the web page for TC006
    And There is an input for the query
    When I enter the query string
    Then The list is updated with elements that match the query
    When I select an option
    Then The option is displayed