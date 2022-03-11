Feature: The user would like to login into the application

  Scenario Outline: The user cannot login with invalid credentials
    Given the user is on the trello login page
    When the user enters username "<username>" and password "<password>"
    And the user click on the login button
    Then the user does not successfully login into the application homepage

    Examples:
      | username           | password    |
      | admin@gmail.com    | admin123!   |
      | admin34@yahoo.com  | admin1965@  |
      | userXZY@gmailcom   | userXZY76&  |
      | novauser@gmail.com | nova@123$   |
      | myser@gmail.com    | meuser@23*& |