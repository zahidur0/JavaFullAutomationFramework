Feature: User is able to switch between multiple different frames

  Scenario: Switch between frames and access elements within them
    Given I have navigated to the web page for TC009
    When I switch focus to the middle frame
    Then I am able to access the text displayed in middle
    When I switch focus to the bottom frame
    Then I am able to access the text displayed in bottom