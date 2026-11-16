# Automated Selenium Cucumber BDD tests integrated with GitHub and Jenkins



Feature: Login Functionality

  As a registered use
  I want to log into the application
  so that I can access my account

  @smoke @regression
  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters a valid username
    And the user enters a valid password
    And the user clicks the login button
    Then the user should see a success message

  @regression @negative
  Scenario: Login with invalid credentials
    Given the user is on the login page
    When the user enters an invalid username
    And the user enters an invalid password
    And the user clicks the login button
    Then the user should see an error message

  @regression
  Scenario Outline: Login with multiple credentials
    Given the user is on the login page
    When the user enters username "<username>"
    And the user enters password "<password>"
    And the user clicks the login button
    Then the login message should contain "<expectedMessage>"


    Examples:
      | username  | password             | expectedMessage                |
      | tomsmith  | SuperSecretPassword! | You logged into a secure area! |
      | wronguser | wrongpassword        | Your username is invalid!      |
      | tomsmith  | wrongpassword        | Your password is invalid!      |
      | wronguser | SuperSecretPassword! | Your username is invalid!      |
      | tomsmith  | incorrect123         | Your password is invalid!      |
