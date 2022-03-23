Feature: Enter text into a field on the webpage

  Scenario: Successfully enter text into a field on a web page
    Given I have navigated to the web page for TC003
    And The webpage contains a text input
    When I send text to the field
    Then The text is displayed inside the text input
    When I send more text to the field
    Then The extra text is added to the field
