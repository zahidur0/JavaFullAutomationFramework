Feature: The user is able to choose options on JavaScript alert and confirm windows

  Scenario: The user closes the alert window
    Given I have navigated to the web page for TC007
    And I have clicked on a link that opens an alert window
    When I select OK on the alert window
    Then The window is closed

  Scenario: The user accepts the confirm window
    Given I have navigated to the web page for TC007
    And I have clicked on a link that opens a confirm window
    When I select yes on the confirm window
    Then The window is closed

  Scenario: The user declines the confirm window
    Given I have navigated to the web page for TC007
    And I have clicked on a link that opens a confirm window
    When I select no on the confirm window
    Then The window is closed