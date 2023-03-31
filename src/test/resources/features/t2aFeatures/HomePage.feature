@regression
Feature: Verify the HomePage functionality

  Background:
    Given user launch the NetPay advance application
    And user login as a Kansas state user with default password
    And user click the Text Message button and the next button for MFA
    And user enters the verification code

  @HP_006
  Scenario: Verify if the profile image is visible in the left side corner of the home page
    When user able to view the profile image

  @HP_007
  Scenario: Verify  go to profile option
    When user clicks the Go to Profile button in NetPayAdvance home page
    Then user navigated to go profile page

  @HP_008
  Scenario: Verify Logout option
    When user clicks the logout button

  @HP_009
  Scenario: Verify Loans option in the left side corner
    When user clicks the loans
