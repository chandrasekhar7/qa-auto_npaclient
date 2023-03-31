package com.pages.common;

import com.netpay.baseweb.BasePage;
import com.netpay.constants.ApplicationConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//h1[@class='merchant_name']")
    private WebElement pageHeader;
    @FindBy(xpath = "//p[normalize-space()='Login to your account']")
    private WebElement loginToYourAccount;
    @FindBy(id = "login-email")
    private WebElement emailInput;
    @FindBy(id = "login-password")
    private WebElement passwordInput;
    @FindBy(id = "login-password-helper-text")
    private WebElement passwordRequired;
    @FindBy(id = "login-email-helper-text")
    private WebElement emailRequired;
    @FindBy(xpath = "//p[normalize-space()='Login']")
    private WebElement loginButton;
    @FindBy(xpath = "//div[normalize-space()='Text Message']//parent::button")
    private WebElement textMessageButton;
    @FindBy(xpath = "//div[normalize-space()='Email']//parent::button")
    private WebElement emailButton;
    @FindBy(xpath = "//div[normalize-space()='Next']//parent::button")
    private WebElement nextButton;

    //Send Verification
    @FindBy(xpath = "//p[text()='Verify Your Identity']")
    private WebElement identityHeader;
    @FindBy(xpath = "//input[@id='mui-1']")
    private WebElement verificationInputOne;
    @FindBy(xpath = "//input[@id='mui-2']")
    private WebElement verificationInputTwo;
    @FindBy(xpath = "//input[@id='mui-3']")
    private WebElement verificationInputThree;
    @FindBy(xpath = "//input[@id='mui-4']")
    private WebElement verificationInputFour;
    @FindBy(xpath = "//input[@id='mui-5']")
    private WebElement verificationInputFive;
    @FindBy(xpath = "//input[@id='mui-6']")
    private WebElement verificationInputSix;

    public LoginPage(WebDriver driver) throws IOException {
        super(driver);
    }

    public static LoginPage using(WebDriver driver) {
        LoginPage loginPage = null;
        try {
            loginPage = new LoginPage(driver);
        } catch (IOException e) {
            System.out.println(e);
        }
        return loginPage;
    }

    public boolean isLoginPageDisplayed() {
        return isElementPresent(loginToYourAccount, "Login to your account");
    }

    public void enterEmail(String email) {
        sendKeys(emailInput, email, "Email");
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password, "Password");
    }

    public String getUserNameErrorValidation() {
        return getTextFromElement(emailRequired, "Email required");
    }

    public String getPasswordErrorValidation() {
        return getTextFromElement(passwordRequired, "Password required");
    }

    public void clickLoginButton() {
        clickElement(loginButton, "Login");
    }

    public void launchNetPayAdvance() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(waitTime));
        driver.get(appPropertiesEnvironment.getProperty(ApplicationConstants.NETPAYADVANCE_URL));
    }

    public void waitIdentityHeaderPageLoad() {
        explicitWait(identityHeader, waitTime, "visibility");
    }

    public String getIdentityHeader() {
        return getTextFromElement(identityHeader, "Identity header");
    }

    public void verifyYourIdentity(String input) {
        clickElement(By.xpath("//div[normalize-space()='" + input + "']//parent::button"), "" + input + "");
        clickElement(nextButton, "Next");
    }

    public void verificationCode() {
        sendKeys(verificationInputOne, "5", "Input");
        sendKeys(verificationInputTwo, "5", "Input");
        sendKeys(verificationInputThree, "5", "Input");
        sendKeys(verificationInputFour, "5", "Input");
        sendKeys(verificationInputFive, "5", "Input");
        sendKeys(verificationInputSix, "5", "Input");
        clickElement(nextButton, "Next");
        sleep(3000);
        clickElement(nextButton, "Next");
    }

    public void validatePageIsLaunched() {
        String name = "Shop Our Partners";
        Assert.assertEquals(getTrimmedText(this.pageHeader.getText()), name);
    }

    @Override
    protected void setPageproperties() {
//        this.PageTitle = ApplicationConstants.Tile;

    }

}