Feature: User is able to set an implicit wait so that steps are not failed prematurely

  Scenario: Successfully set up an implicit wait on a web driver
    Given I have supplied the correct setup to add an implicit wait
    When I navigate to the web page for TC014
    Then The driver will implicitly wait before the test fails
