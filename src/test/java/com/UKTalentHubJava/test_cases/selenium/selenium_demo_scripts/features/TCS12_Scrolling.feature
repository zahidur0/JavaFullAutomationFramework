Feature: User is able to scroll the window and embedded windows

  Scenario: Successfully scroll the page and and scroll down an internal table
    Given I have navigated to the web page for TC012
    When I scroll the page down a specific amount
    Then The offset of the window has changed by the specific amount