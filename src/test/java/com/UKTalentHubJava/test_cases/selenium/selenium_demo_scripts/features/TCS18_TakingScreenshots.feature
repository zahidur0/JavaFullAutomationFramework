Feature: User is able to take screenshots of the entire page or specific elements

  Scenario: Take a screenshot of the whole page
    Given I have navigated to the web page for TC018
    When I take a screenshot of the whole page
    Then A file in generated and saved to my machine

  Scenario: Take a screenshot of a specific element
    Given I have navigated to the web page for TC018
    When I take a screenshot of a specific element
    Then A file in generated and saved to my machine