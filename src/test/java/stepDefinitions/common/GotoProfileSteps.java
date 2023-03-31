package stepDefinitions.common;

import com.netpay.constants.ApplicationConstants;
import com.pages.common.GoToProfilePage;
import io.cucumber.java.en.And;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import stepDefinitions.ContextSteps;

public class GotoProfileSteps {
    WebDriver driver;
    private ContextSteps steps;
    private Logger logger;

    public GotoProfileSteps(ContextSteps steps) {
        this.steps = steps;
        this.logger = steps.getLogger();
        driver = steps.getDriver();
    }

    @And("user clicks the profile button")
    public void clickProfile() {
        GoToProfilePage.using(driver).clickProfile();
        logger.info("User clicked the Profile");
    }

    @And("user not able to edit the Name, Email and Birthday field")
    public void isNameEmailAndBirthdayFieldDisabled() {
        Assert.assertFalse(GoToProfilePage.using(driver).isNameFieldDisabled(), "Name field is editable");
        Assert.assertFalse(GoToProfilePage.using(driver).isEmailFieldDisabled(), "Email field is editable");
        Assert.assertFalse(GoToProfilePage.using(driver).isBirthdayFieldDisabled(), "Birthday field is editable");
    }

    @And("verify the State field can be editable")
    public void verifyState() {
        Assert.assertTrue(GoToProfilePage.using(driver).isStateDisplayed(), "State field is not displayed");
    }

    @And("user fills the Profile field's {} {} {} {} {} {} {} {}")
    public void fillProfile(String streetInput,String cityInput, String stateInput, String zipCodeInput,String cellPhoneInput,String homePhoneInput, String driverLicenseInput,String driverStateInput) {
        GoToProfilePage.using(driver).fillProfile(streetInput, cityInput,  stateInput,  zipCodeInput, cellPhoneInput, homePhoneInput,  driverLicenseInput, driverStateInput);
        GoToProfilePage.using(driver).clickSubmit("Profile");

    }

    @And("user navigated to go profile page")
    public void isProfileDisplayed() {
        Assert.assertTrue(GoToProfilePage.using(driver).isProfileDisplayed(), "Profile tab is not displayed");
    }

    @And("user fills the card details")
    public void clickCardsTab() {
        GoToProfilePage.using(driver).fillAddNewCard();
        logger.info("Card saved successfully");
    }

    @And("user able to see the alert {}")
    public void getAlert(String alert) {
        Assert.assertEquals(GoToProfilePage.using(driver).cardSaveAlert(), alert, "Card not saved");
    }

    @And("user edit the card details")
    public void updateCards() {
        GoToProfilePage.using(driver).editCards();
        logger.info("User edited the card");
    }

    @And("user change the password")
    public void changePassword() {
        GoToProfilePage.using(driver).changePassword();
        logger.info("User changed the password");
    }

    @And("user able to see the {} field error validation as {}")
    public void errorValidation(String value, String alert) {
        Assert.assertEquals(GoToProfilePage.using(driver).goProfileErrorValidation(value), alert, "" + value + " is not displayed");
    }

    @And("user clicks the {} tab Submit button")
    public void submitButton(String input) {
        GoToProfilePage.using(driver).clickSubmit(input);
    }

    @And("user clicks the submit button without enter the password")
    public void clickSubmit() {
        GoToProfilePage.using(driver).clickPasswordSubmit();
    }

    @And("user enter the {} character of {} field")
    public void enterDigitPassword(int count, String attribute) {
        String password = RandomStringUtils.randomAlphabetic(count);
        GoToProfilePage.using(driver).sendValuesForGotoProfileAttribute(attribute, password + Keys.ENTER);
    }

    @And("user enters the different password for {} field")
    public void enterDigitPassword1(String attribute) {
        GoToProfilePage.using(driver).sendValuesForGotoProfileAttribute(attribute, ApplicationConstants.confirmPassword + Keys.ENTER);
    }

    @And("user clicks the Go to Profile's {} tab")
    public void clickTab(String value) {
        GoToProfilePage.using(driver).clickProfileTab(value);
    }

}