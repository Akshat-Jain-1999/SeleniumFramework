
@tag
Feature: Error Validations
  I want to use this template for my feature file

  @ErrorValidations
  Scenario Outline: Error Validation on Test
    Given I landed on E Commerce Page
    Given I logged in with username <username> and password <password>
    Then Message "Incorrect email or password." is verified

    Examples: 
      | username         | password |
      | akshat@gmail.com | momDAD2  |
