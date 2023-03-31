package com.pages.pages.netpayadvance;

import com.netpay.baseweb.BasePage;
import com.netpay.constants.ApplicationConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

public class NetpayAdvance extends BasePage {

    @FindBy(xpath = "//h1[@class='merchant_name']")
    private WebElement pageHeader;
    @FindBy(id = "login-email")
    private WebElement emailInput;
    @FindBy(id = "login-password")
    private WebElement passwordInput;
    @FindBy(xpath = "//p[normalize-space()='Login']")
    private WebElement loginButton;

    public NetpayAdvance(WebDriver driver) throws IOException {
        super(driver);
    }

    public static NetpayAdvance using(WebDriver driver) throws IOException {
        return new NetpayAdvance(driver);
    }

    public void enterEmail(String username) {
        explicitWait(this.emailInput, waitTime, visibility);
        this.emailInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        explicitWait(this.passwordInput, waitTime, visibility);
        this.passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        explicitWait(this.passwordInput, waitTime, visibility);
        this.loginButton.click();
    }

    public void launchNetpayAdvance() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(waitTime));
        driver.get(appPropertiesEnvironment.getProperty(ApplicationConstants.NETPAYADVANCE_URL));
    }

    public void validatePageIsLaunched() {
        String name = "Shop Our Partners";
        Assert.assertEquals(getTrimmedText(this.pageHeader.getText()), name);
    }

    @Override
    protected void setPageproperties() {
        //this.pageTitle = ApplicationConstants.TITLE_T2A_PAGE;

    }

}
