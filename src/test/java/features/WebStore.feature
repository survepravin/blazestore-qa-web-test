@web
Feature: DEMO ONLINE SHOP

  Background: 
    Given URL is loaded in to browser
    And Customer registers himself

  @PlaceOrder
  Scenario: Verify customer is able to place order
    When Customer logins to online store
    And Navigates to "Laptops"
    And Navigates to "Phones"
    And Navigates to "Monitors"
    Then Navigates to "Laptops"
    And Selects product "Sony vaio i5"
    Then Adds to cart
    And Navigates to "Home"
    And Navigates to "Laptops"
    And Selects product "Dell i7 8gb"
    Then Adds to cart
    Then Navigates to "Cart"
    And Removes product "Dell i7 8gb" from cart
    Then Customer place order and fill form details
    And Verify the amount on order confirmation
    Then Clicks on OK button

