Feature: User is able to put together actions and perform them

  Scenario: Successfully create and perform a simple actions
    Given I have navigated to the web page for TC016
    When I build and perform the action
    Then The effect is seen on the web page

  Scenario: User is able to create and perform composite actions
    Given I have navigated to the web page for TC016
    When I build and perform the composite action
    Then The full action is performed