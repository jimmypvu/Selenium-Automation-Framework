Feature: Login feature
  Verify login functionality

  @smoke @regression @e2e @login
  Scenario: User should be able to login successfully with valid credentials
    Given user is on the login page
    When user enters username and password
    And user clicks Login button
    Then user is navigated to account page

  @smoke @regression @login
  Scenario Outline: User should not be logged in with invalid credentials
    Given user is on the login page
    When user enters <username> and <password>
    And user clicks Login button
    Then user is not logged in
    Examples: bad logins
      | username       | password      |
      | "baduser"      | "badpassword" |
      | "sirtestsalot" | "badpassword" |
      | "sirtestsalot" | ""            |


