Feature: The user can select from radio buttons and check checkboxes

  Scenario: Successfully choose between radio buttons and check a checkbox
    Given I have navigated to the web page for TC004
    And The webpage contains radio buttons
    And The webpage contains checkboxes
    When I select a radio button
    And I select a checkbox
    Then The correct radio button is selected
    And The checkbox is checked
    When I select a different radio button
    Then The radio button selection is changed