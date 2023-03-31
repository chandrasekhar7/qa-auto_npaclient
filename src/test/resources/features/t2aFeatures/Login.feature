@regression
Feature: Verify the login functionality

  @LP_001
  Scenario: Verify user will be able to login with a valid username and valid password
    Given user launch the NetPay advance application
    When user login as a Kansas state user with default password
    Then user able to see the verify Identity header page

  @LP_002
  Scenario: Error validation - valid username and empty password
    Given user launch the NetPay advance application
    And user enter the valid Kansas state username
    And user enter the empty password
    When user clicks the login button
    Then user able to see the Password required error validation

  @LP_003
  Scenario: Error validation - empty username and empty password
    Given user launch the NetPay advance application
    And user enter the empty username
    And user enter the empty password
    When user clicks the login button
    Then user able to see the error validation as Email required
    And user able to see the Password required error validation

  @LP_004
  Scenario: Error validation - valid username and invalid password
    Given user launch the NetPay advance application
    And user enter the valid Kansas state username
    And user enter the invalid password
    When user clicks the login button
    Then user able to see the Invalid Password error validation

  @LP_005
  Scenario: Error validation - invalid username and invalid password
    Given user launch the NetPay advance application
    And user enter the invalid username
    And user enter the invalid password
    When user clicks the login button
    Then user able to see the Invalid Password error validation
