Feature: User is able to interact with multiple tabs

  Scenario: Grab text from two different tabs and add it to form in another tab
    Given I have navigated to a form
    And I have opened two extra tabs
    And I have navigated to two separate pages on the other tabs
    When I grab the text from the second tab
    Then I am able to insert it into the first name input in the first tab
    When I grab the text from the third tab
    Then I am able to insert it into the last name input in the first tab
