@tag
Feature: Purchase the order from E commerce Website
  I want to use this template for my feature file

  Background: 
    Given I landed on E Commerce Page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given I logged in with username <username> and password <password>
    When I add product <productName> to Cart
    And Check Out <productName> and Submit the Order
    Then I verify the message "THANKYOU FOR THE ORDER." on Confirmation Page.

    Examples: 
      | username         | password | productName |
      | akshat@gmail.com | momDAD2# | Zara Coat 3 |
