package stepDefinitions.common;

import com.pages.common.HomePage;
import com.pages.common.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import stepDefinitions.ContextSteps;

public class HomePageSteps {
    WebDriver driver;
    private ContextSteps steps;
    private Logger logger;

    public HomePageSteps(ContextSteps steps) {
        this.steps = steps;
        this.logger = steps.getLogger();
        driver = steps.getDriver();
    }

    @Given("user clicks the Go to Profile button in NetPayAdvance home page")
    public void clickProfileButton() {
        HomePage.using(driver).clickGoToProfileButton();
        logger.info("User clicked the Profile button successfully");
    }

    @And("user clicks the logout button")
    public void clickLogout() {
        HomePage.using(driver).clickLogout();
        Assert.assertTrue(LoginPage.using(driver).isLoginPageDisplayed(), "Login page is not displayed");
    }

    @And("user clicks the loans")
    public void clickLoans() {
        HomePage.using(driver).clickLoans();
        Assert.assertTrue(HomePage.using(driver).isCurrentLoanDisplayed(), "Current Loans is not displayed");
    }

//    @And("user setup the security questions")
//    public void fillSecurityQuestion() {
//        HomePage.using(driver).fillSecurityQuestion();
//    }

    @And("user request to draw ${}")
    public void requestDraw(String input) {
        HomePage.using(driver).requestDraw(input);
        logger.info("User successfully draw their amount");
    }

    @And("user make payment ${}")
    public void makePayment(String input) {
        HomePage.using(driver).makePayment(input);
        logger.info("User successfully make a payment");
    }

    @And("user clicks the Request A Draw")
    public void clickHomePageButton() {
        HomePage.using(driver).clickRequestADraw();
        logger.info("User clicked the Request a drae button");
    }

    @Then("user able to see the Request A Draw header")
    public void verifyRequestDrawHeader() {
        Assert.assertEquals(HomePage.using(driver).getRequestDrawHeader(), "Request A Draw", "The header is mismatched");
    }

    @And("user clicks the confirm button without enter the draw amount")
    public void clickConfirm() {
        HomePage.using(driver).clickConfirmButtonWithEmptyDrawAmount();
        logger.info("User clicked the Confirm button button successfully");
    }

    @And("user able to see the mandatory alert for Draw amount is required")
    public void verifyDrawAmountRequire() {
        HomePage.using(driver).getDrawAmountRequire();
        logger.info("User successfully able to see the Draw amount is required alert");
    }

    @And("user able to view the profile image")
    public void IsProfileDisplayed() {
        Assert.assertTrue(HomePage.using(driver).IsProfileDisplayed(), "Profile icon is not displayed");
    }
}