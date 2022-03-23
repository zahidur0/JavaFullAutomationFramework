Feature: User is able to add items to their cart then proceed to the checkout and apply a coupon

  Scenario: User adds items to their cart, applies a coupon and checks out
    Given I have navigated to the web page for TC019
    And I have created all the POM objects
    When I add items to my cart
    Then The items are displayed in my basket
    When I remove an item from my basket
    Then The item is removed from the basket
    When I go to checkout
    Then I am redirected to the cart page
    And I am able to see a breakdown of all items in my basket
    When I enter a correct promo code
    Then I see a message to say my coupon has been applied
    When I click the place order button
    Then I am redirected to the country page
    When I enter a country
    And I agree to the terms and conditions
    And I click proceed
    Then I am redirected to the product page

  Scenario: User adds items to their cart, applies an incorrect coupon and checks out
    Given I have navigated to the web page for TC019
    And I have created all the POM objects
    When I add items to my cart
    Then The items are displayed in my basket
    When I remove an item from my basket
    Then The item is removed from the basket
    When I go to checkout
    Then I am redirected to the cart page
    And I am able to see a breakdown of all items in my basket
    When I enter an incorrect promo code
    Then I see a message to say my coupon has not been applied
    When I click the place order button
    Then I am redirected to the country page
    When I enter a country
    And I agree to the terms and conditions
    And I click proceed
    Then I am redirected to the product page
