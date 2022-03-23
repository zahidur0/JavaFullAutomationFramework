Feature: User is able to enter dates into a calendar

  Scenario: User correctly enters a given date into the calendar
    Given I have navigated to the web page for TC010
    When I click to open the calendar
    And I enter the day required
    Then The specific day is selected

