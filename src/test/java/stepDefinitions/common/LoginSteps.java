package stepDefinitions.common;

import com.netpay.constants.ApplicationConstants;
import com.pages.common.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import stepDefinitions.ContextSteps;

public class LoginSteps {
    WebDriver driver;
    private ContextSteps steps;
    private Logger logger;

    public LoginSteps(ContextSteps steps) {
        this.steps = steps;
        this.logger = steps.getLogger();
        driver = steps.getDriver();
    }

    @Given("user launch the NetPay advance application")
    public void launchNetPayAdvance() {
        LoginPage.using(driver).launchNetPayAdvance();
        logger.info("NetPay advance URL launched");
    }

    @And("user login as a {} user with {}")
    public void login(String email, String password) {
        switch (email) {
            case "Kansas state":
                LoginPage.using(driver).enterEmail(ApplicationConstants.KANSAS_STATE_USER);
                break;
            case "Kansas state2":
                LoginPage.using(driver).enterEmail(ApplicationConstants.KANSAS_STATE_USER2);
                break;
/** From here on we might add other states*/
        }
        switch (password) {
            case "default password":
                LoginPage.using(driver).enterPassword(ApplicationConstants.DEFAULT_PASSWORD);
                break;
/** From here on we might add other password*/
        }
        LoginPage.using(driver).clickLoginButton();
        LoginPage.using(driver).waitIdentityHeaderPageLoad();
        logger.info("Login successfully");
    }

    @And("user able to see the verify Identity header page")
    public void verifyIdentityHeader() {
        LoginPage.using(driver).getIdentityHeader();
    }

    @And("user enter the valid {} username")
    public void username(String email) {
        switch (email) {
            case "Kansas state":
                LoginPage.using(driver).enterEmail(ApplicationConstants.KANSAS_STATE_USER);
                break;
        }
    }

    @And("user enter the empty username")
    public void emptyUsername() {
        LoginPage.using(driver).enterEmail("");
    }

    @And("user enter the invalid username")
    public void invalidUsername() {
        String userName = RandomStringUtils.randomAlphabetic(5) + "@" + "domain.com";
        LoginPage.using(driver).enterEmail(userName);
    }

    @And("user enter the empty password")
    public void emptyPassword() {
        LoginPage.using(driver).enterPassword("");
    }

    @And("user enter the invalid password")
    public void invalidPassword() {
        String randomPassword = RandomStringUtils.randomAlphabetic(8);
        LoginPage.using(driver).enterPassword(randomPassword);
    }

    @And("user clicks the login button")
    public void clickLogin() {
        LoginPage.using(driver).clickLoginButton();
    }

    @And("user able to see the {} error validation")
    public void getErrorValidationForPassword(String alert) {
        Assert.assertEquals(LoginPage.using(driver).getPasswordErrorValidation(), alert, "Password error validation not displayed");
    }

    @And("user able to see the error validation as {}")
    public void getErrorValidationForEmail(String alert) {
        Assert.assertEquals(LoginPage.using(driver).getUserNameErrorValidation(), alert, "Email error validation not displayed");
    }

    @And("user click the {} button and the next button for MFA")
    public void verifyUserIdentity(String preferredIdentity) {
        LoginPage.using(driver).verifyYourIdentity(preferredIdentity);
    }

    @And("user enters the verification code")
    public void sendVerificationCode() {
        LoginPage.using(driver).verificationCode();
    }

}