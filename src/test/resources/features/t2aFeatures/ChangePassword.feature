@regression
Feature: Verify the Password functionality

  Background:
    Given user launch the NetPay advance application
    And user login as a Kansas state user with default password
    And user click the Text Message button and the next button for MFA
    And user enters the verification code
    When user clicks the Go to Profile button in NetPayAdvance home page
    Then user clicks the Go to Profile's Password tab

  @CP_001
  Scenario: Change password
    When user change the password
    Then user able to see the alert Your password has been changed successfully.
    And user clicks the logout button

  @CP_002
  Scenario: Error Validation-empty field
    When user clicks the Password tab Submit button
    Then user able to see the Password field error validation as Password required
    And user able to see the Confirm Password field error validation as Confirm password required

  @CP_003
  Scenario: Error Validation -giving inputs in irregular format
    And user enter the 6 character of Password field
    And user able to see the Password field error validation as Password must be at least 7 characters
    And user enter the 6 character of Confirm Password field
    And user able to see the Confirm Password field error validation as Password must be at least 7 characters

  @CP_004
  Scenario: Error Validation-giving password without any of its special characters
    When user enter the 8 character of Password field
    Then user able to see the Password field error validation as Password must have at least one digit
    When user enter the 8 character of Confirm Password field
    Then user able to see the Confirm Password field error validation as Password must have at least one digit

  @CP_005
  Scenario: Error Validation -giving  mismatched password
    And user enter the 8 character of Password field
    When user enters the different password for Confirm Password field
    Then user able to see the Confirm Password field error validation as Confirmation password doesn't match the first password entered