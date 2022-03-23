Feature: User is able to create an explicit wait so that steps are not failed prematurely

  Scenario: Successfully set up an explicit wait object
    Given I have created an explicit wait
    When I navigate to the web page for TC015
    Then The explicit wait will stop execution until the condition is met