Feature: The user is able to get the data such as number of rows, number of columns and
  individual rows of data form a web table

  Scenario: Successfully get data from a web table
    Given I have navigated to the web page for TC011
    When I locate all the columns in the table
    Then I am able to get the number of columns
    When I locate all the rows in the table
    Then I am able to get the number of rows
    When I locate a specific row in the table
    Then I am able to get all the data for that row