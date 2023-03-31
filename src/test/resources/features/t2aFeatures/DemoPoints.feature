Feature: Demo points

  #fail
  Scenario: Verify the profile functionality
    Given user launch the NetPay advance application
    And user login as a Kansas state2 user with default password
    And user click the Text Message button and the next button for MFA
    And user enters the verification code
    When user clicks the Go to Profile button in NetPayAdvance home page
    And user clicks the profile button

  Scenario: Adding a debit card to a New user
    Given user launch the NetPay advance application
    And user login as a Kansas state user with default password
    And user click the Text Message button and the next button for MFA
    And user enters the verification code
    And user clicks the Go to Profile button in NetPayAdvance home page
    When user fills the card details
    Then user able to see the alert Your card has been added successfully.
    And user edit the card details
    Then user able to see the alert Your card has been added successfully.

#fail
  Scenario: Adding a debit card to a New user11
    Given user launch the NetPay advance application
    And user login as a Kansas state2 user with default password
    And user click the Text Message button and the next button for MFA
    And user enters the verification code
    And user clicks the Go to Profile button in NetPayAdvance home page
    When user fills the card details
    Then user able to see the alert Your card has been added successfully.
    And user edit the card details
    Then user able to see the alert Your card has been added successfully.

  Scenario: Request a Draw
    Given user launch the NetPay advance application
    And user login as a Kansas state user with default password
    And user click the Text Message button and the next button for MFA
    And user enters the verification code
    And user request to draw $30

#fail
  Scenario: Make Payment
    Given user launch the NetPay advance application
    And user login as a Kansas state user with default password
    And user click the Text Message button and the next button for MFA
    And user enters the verification code
    And user make payment $20

  Scenario: Verify the error validation for Request a Draw
    Given user launch the NetPay advance application
    And user login as a Kansas state user with default password
    And user click the Text Message button and the next button for MFA
    And user enters the verification code
    And user clicks the Request A Draw
    Then user able to see the Request A Draw header
    And user clicks the confirm button without enter the draw amount
    Then user able to see the mandatory alert for Draw amount is required
