Feature: The user can see all the choices from a static dropdown and select an option

  Scenario: The user select an option from the dropdown
    Given I have navigated to the web page for TC005
    And The webpage contains a static dropdown
    When I select an element from the dropdown
    Then The option is selected and displayed