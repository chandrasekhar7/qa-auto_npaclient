@regression
Feature: Verify the Profile functionality

  Background:
    Given user launch the NetPay advance application
    And user login as a Kansas state user with default password
    And user click the Text Message button and the next button for MFA
    And user enters the verification code
    When user clicks the Go to Profile button in NetPayAdvance home page
    Then user clicks the profile button

  @PD_001,@PD_008,@PD_010,@PD_011
  Scenario Outline: Verify if name,email and birthday fields are not editable and Validate Home Phone with empty field
    And user not able to edit the Name, Email and Birthday field
    When user fills the Profile field's <Street> <City> <State> <Zip Code> <Cell Phone> <Home Phone> <Driver's License> <Driver's State>
    Then user able to see the alert Your profile details have been updated successfully.

    Examples:
      | Street          | City  | State  | Zip Code | Cell Phone | Home Phone | Driver's License | Driver's State |
      | 118 E Walnut St | Derby | Kansas | 67037    | 3162042324 | 3162049963 | K0349927         | Kansas         |
      | 118 E Walnut St | Derby | Kansas | 67037    | 3162042324 |            | K0349927         | Kansas         |

  @PD_002, @PD_003, @PD_005
  Scenario Outline: Error validation - Street Address, City and Zipcode
    When user fills the Profile field's <Street> <City> <State> <Zip Code> <Cell Phone> <Home Phone> <Driver's License> <Driver's State>
    Then user able to see the Street field error validation as Billing street is required
    Then user able to see the City field error validation as Billing city is required
    Then user able to see the Zip Code field error validation as Zip code is required

    Examples:
      | Street | City | State  | Zip Code | Cell Phone | Home Phone | Driver's License | Driver's State |
      |        |      | Kansas |          |            |            |                  | Kansas         |

  @PD_004
  Scenario Outline:Verify State field can be editable
    And verify the State field can be editable
    When user fills the Profile field's <Street> <City> <State> <Zip Code> <Cell Phone> <Home Phone> <Driver's License> <Driver's State>
    Then user able to see the alert Your profile details have been updated successfully.

    Examples:
      | Street          | City  | State  | Zip Code | Cell Phone | Home Phone | Driver's License | Driver's State |
      | 118 E Walnut St | Derby | Kansas | 67037    | 3162042324 | 3162049963 | K0349927         | Kansas         |

  @PD_006
  Scenario Outline:Error validation -cell phone with empty field
    When user fills the Profile field's <Street> <City> <State> <Zip Code> <Cell Phone> <Home Phone> <Driver's License> <Driver's State>
    Then user able to see the Cell Phone field error validation as Invalid Phone Number
    Examples:
      | Street          | City  | State  | Zip Code | Cell Phone | Home Phone | Driver's License | Driver's State |
      | 118 E Walnut St | Derby | Kansas | 67037    |            | 3162049963 | K0349927         | Kansas         |

  @PD_007
  Scenario Outline:Error Validation - when characters are given in cell phone field
    When user fills the Profile field's <Street> <City> <State> <Zip Code> <Cell Phone> <Home Phone> <Driver's License> <Driver's State>
    Then user able to see the Cell Phone field error validation as Invalid Cell Number
    Examples:
      | Street          | City  | State  | Zip Code | Cell Phone | Home Phone | Driver's License | Driver's State |
      | 118 E Walnut St | Derby | Kansas | 67037    | Number     | 3162049963 | K0349927         | Kansas         |

  @PD_009
  Scenario Outline:Error validation -Home Phone field with characters
    When user fills the Profile field's <Street> <City> <State> <Zip Code> <Cell Phone> <Home Phone> <Driver's License> <Driver's State>
    Then user able to see the alert Invalid home phone number when characters are entered

    Examples:
      | Street          | City  | State  | Zip Code | Cell Phone | Home Phone | Driver's License | Driver's State |
      | 118 E Walnut St | Derby | Kansas | 67037    | 3162042324 | PHONE      | K0349927         | Kansas         |
