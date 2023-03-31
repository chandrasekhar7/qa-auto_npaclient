package com.netpay.baseweb;

import com.netpay.constants.ApplicationConstants;
import com.netpay.pages.common.PageHeader;
import com.pages.utilities.ApplicationProperties;
import com.pages.utilities.LoggerProperties;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public abstract class BasePage {

    protected WebDriver driver;
    protected PageHeader header;

    protected String pageTitle;
    protected String pageUrl;
    protected String headerText;
    protected String headerTextSV7;
    protected String headerTextTest2;
    protected long pollTime;
    protected long waitTime;
    protected long explicitWaitTime;
    protected int browserWait;
    protected ApplicationProperties appPropertiesEnvironment;
    protected ApplicationProperties appPropertiesWeb;
    protected LoggerProperties loggerProperties;
    protected Logger logger;
    protected String visibility = "visibility";
    protected String clickable = "clickable";

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        header = PageHeader.getInstance(this.driver);
        loggerProperties = LoggerProperties.getInstance();
        logger = loggerProperties.getLogger();
        appPropertiesEnvironment = ApplicationProperties.getEnvironment();
        try {
            appPropertiesWeb = ApplicationProperties.getInstance("web");
        } catch (IOException e) {
            logger.info("io exception at getting web instance" + e);
        }
        pollTime = Integer.parseInt(appPropertiesWeb.getProperty(ApplicationConstants.POLL_TIME));
        waitTime = Integer.parseInt(appPropertiesWeb.getProperty(ApplicationConstants.PAGE_LOAD_WAIT_TIME));
        browserWait = Integer.parseInt(appPropertiesWeb.getProperty(ApplicationConstants.BROWSER_PAUSE));
        explicitWaitTime = Integer.parseInt(appPropertiesWeb.getProperty(ApplicationConstants.IMPLICIT_WAIT_TIME));
// defaults to title strategy if not set by setPageproperties
        setPageproperties();
    }

    protected abstract void setPageproperties();

    protected WebElement getXpathWithLabel(String tag, String name) {
        return driver.findElement(By.xpath("//" + tag + "[normalize-space()='" + name + "']"));
    }

    public void waitForElementAndClick(WebElement ele, long explicitWaitTime, String condition) {
        explicitWait(ele, explicitWaitTime, condition);
        ele.click();
    }

    public void switchToFrame(WebElement ele) {
        this.driver.switchTo().frame(ele);
    }

    public void switchToDefaultContent() {
        this.driver.switchTo().defaultContent();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getExpectedTitle() {
        return this.pageTitle;
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public String getPageURL() {
        return this.pageUrl;
    }

    public PageHeader pageHeader() {
        return header;
    }

    public void validatePageTitle() {
        waitTillPageLoad();
        Assert.assertEquals(getTitle(), getExpectedTitle());
    }

    public void validatePageTitle(String expectedTitle) {
        Assert.assertEquals(getTitle(), expectedTitle);
    }

    public void validateHeaders(WebElement actualHeader, String expectedHeader) {
        explicitWait(actualHeader, explicitWaitTime, visibility);
        Assert.assertEquals(actualHeader.getText().trim(), expectedHeader);
    }

    public void switchToTabByIndex(int index) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        String newTab = tabs.get(index);
        driver.switchTo().window(newTab);
    }

    public void validateNewTabHeaders(WebElement actualHeader, String expectedHeader) {
        int tabNumber = 1;
        switchToTabByIndex(tabNumber);
        explicitWait(actualHeader, explicitWaitTime, visibility);
        Assert.assertEquals(actualHeader.getText().trim(), expectedHeader);
    }

    public void validateNewTabURL(String expectedURL) {
        int tabNumber = 1;
        switchToTabByIndex(tabNumber);
        waitForDuration(pollTime);
        Assert.assertEquals(this.getURL().trim(), expectedURL);
    }

    public void closeCurrentTabAndSwitchToDefault() {
        driver.close();
        int tabNumber = 0;
        switchToTabByIndex(tabNumber);
    }


    public void checkHeaderContainsText(String actualHeader, String expectedHeader) {
        Assert.assertTrue(actualHeader.contains(expectedHeader));
    }

    public String getTrimmedText(String s) {
        return s.trim();
    }

    protected void verifyElementIsDisplayed(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    protected boolean isElementPresent(By by) {
        return !driver.findElements(by).isEmpty();
    }

    protected boolean isElementPresent(WebElement ele, String elementDescription) {
        explicitWait(ele, waitTime, "visibility");
        try {
            return ele.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void scrollToElementWithJS(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            Assert.fail("Failed to scroll with exception " + e);
        }
    }

    public void clickElementWithJS(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            Assert.fail("Failed to click with exception " + e);
        }
    }

    public void waitTillPageLoad() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } catch (Exception e) {
            Assert.fail("Failed to wait till the page loaded " + e);
        }

    }

    public void clickElement(WebElement element, String elementDescription) {
        explicitWait(element, waitTime, "clickable");
        element.click();
    }

    public void clickElementWithout(WebElement element, String elementDescription) {
        sleep(3000);
        element.click();
    }

    public void clickElement(By by, String elementDescription) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    public void sendKeys(WebElement element, String value, String elementDescription) {
        explicitWait(element, waitTime, "visibility");
        clearFieldWithBackspaces(element);
        element.sendKeys(value);
    }

    public void sendKeys(By by, String value, String elementDescription) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        clearFieldWithBackspaces(element);
        element.sendKeys(value);
    }

    public String getTextFromElement(WebElement element, String elementDescription) {
        explicitWait(element, waitTime, "visibility");
        return element.getText();
    }

    public String getTextFromElement(By by, String elementDescription) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
    }

    public void selectByVisibleText(WebElement selectElement, String selectionText,String elementDescription) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOf(selectElement));
        new Select(selectElement).selectByVisibleText(selectionText);
    }

    public void explicitWait(WebElement element, long time, String condition) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        switch (condition) {
            case "visibility":
                wait.until(ExpectedConditions.visibilityOf(element));
                break;
            case "clickable":
                wait.until(ExpectedConditions.elementToBeClickable(element));
                break;
            case "selected":
                wait.until(ExpectedConditions.elementToBeSelected(element));
                break;
            case "visibilityOfElementLocated":
                wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
                break;
            default:
                wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
        }
    }

    protected void waitForElementClickable(long timeout, final WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(pollTime))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToBeVisible(long timeout, final WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(pollTime))
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForDuration(long time) {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    protected void waitForAlert(long timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(pollTime))
                .until(ExpectedConditions.alertIsPresent());
    }

    public void clearFieldWithBackspaces(WebElement element) {
        String text = element.getAttribute("value");
        for (int i = 0; i < text.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}