Feature: User can locate elements based on their relative position to other elements

  Scenario: User successfully selects an elements based on its relative position
    Given I have navigated to the web page for TC013
    When I locate an element on the page
    And I select another element based on its relative position
    Then I am able to interact with that element