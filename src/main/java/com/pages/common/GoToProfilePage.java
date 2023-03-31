package com.pages.common;

import com.netpay.baseweb.BasePage;
import com.netpay.constants.ApplicationConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class GoToProfilePage extends BasePage {

    //password
    @FindBy(xpath = "//span[normalize-space()='Password']")
    private WebElement passwordTab;
    @FindBy(xpath = "//label[normalize-space()='Password']//parent::div//child::input")
    private WebElement password;
    @FindBy(xpath = "//label[normalize-space()='Confirm Password']//parent::div//child::input")
    private WebElement confirmPassword;
    @FindBy(xpath = "//span[text()='Password']//parent::h6//parent::div//parent::div//parent::div//parent::div//parent::div//following::button[normalize-space()='Submit']")
    private WebElement passwordSubmitButton;

    //cards
    @FindBy(xpath = "//span[text()='Cards']")
    private WebElement cardTab;
    @FindBy(xpath = "//label[normalize-space()='Card Number']//parent::div//child::input")
    private WebElement cardNumber;
    @FindBy(xpath = "//label[normalize-space()='Exp. (MM/YYYY)']//parent::div//child::input")
    private WebElement cardExpiry;
    @FindBy(xpath = "//label[normalize-space()='CVV']//parent::div//child::input")
    private WebElement cardCVV;
    @FindBy(xpath = "//button[normalize-space()='Yes']")
    private WebElement yesButton;
    @FindBy(xpath = "//span[text()='Cards']//parent::h6//parent::div//parent::div//parent::div//parent::div//parent::div//following::button[normalize-space()='Submit']")
    private WebElement cardSubmitButton;
    @FindBy(id = "notistack-snackbar")
    private WebElement alert;

    //Edit card
    @FindBy(xpath = "(//button[text()='Edit Card'])[last()]")
    private WebElement editCard;
    @FindBy(xpath = "//button[normalize-space()='Save Card']")
    private WebElement saveCard;

    //profile
    @FindBy(xpath = "//span[normalize-space()='Profile']")
    private WebElement profile;
    @FindBy(xpath = "//label[normalize-space()='Name']//parent::div//child::input")
    private WebElement name;
    @FindBy(xpath = "//label[normalize-space()='Email']//parent::div//child::input")
    private WebElement email;
    @FindBy(xpath = "//label[normalize-space()='Birthday']//parent::div//child::input")
    private WebElement birthDay;
    @FindBy(xpath = "//label[normalize-space()='Street']//parent::div//child::input")
    private WebElement street;
    @FindBy(xpath = "//label[normalize-space()='City']//parent::div//child::input")
    private WebElement city;
    @FindBy(xpath = "//label[normalize-space()='State']//parent::div//child::div//div")
    private WebElement state;
    @FindBy(xpath = "//label[normalize-space()='Zip Code']//parent::div//child::input")
    private WebElement zipCode;
    @FindBy(xpath = "//label[normalize-space()='Cell Phone']//parent::div//child::input")
    private WebElement cellPhone;
    @FindBy(xpath = "//label[normalize-space()='Home Phone']//parent::div//child::input")
    private WebElement homePhone;
    @FindBy(xpath = "//label[normalize-space()=\"Driver's License\"]//parent::div//child::input")
    private WebElement driverLicense;
    @FindBy(xpath = "//label[normalize-space()=\"Driver's State\"]//parent::div//child::div//div")
    private WebElement driverState;
    @FindBy(xpath = "//span[text()='Profile']//parent::h6//parent::div//parent::div//parent::div//parent::div//parent::div//following::button[normalize-space()='Submit']")
    private WebElement profileSubmitButton;

    public GoToProfilePage(WebDriver driver) throws IOException {
        super(driver);
    }

    public static GoToProfilePage using(WebDriver driver) {
        GoToProfilePage goToProfilePag = null;
        try {
            goToProfilePag = new GoToProfilePage(driver);
        } catch (IOException e) {
            System.out.println(e);
        }
        return goToProfilePag;
    }

    public void clickProfile() {
        clickElement(profile, "Profile");
    }

    public boolean isProfileDisplayed() {
        return isElementPresent(profile, "Profile");
    }

    public boolean isNameFieldDisabled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.visibilityOf(name)).isEnabled();
    }

    public boolean isEmailFieldDisabled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.visibilityOf(email)).isEnabled();
    }

    public boolean isBirthdayFieldDisabled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.visibilityOf(birthDay)).isEnabled();
    }

    public void fillProfile(String streetInput,String cityInput, String stateInput, String zipCodeInput,String cellPhoneInput,String homePhoneInput, String driverLicenseInput,String driverStateInput) {
        sendKeys(street, streetInput, "Street input");
        sendKeys(city, cityInput, "City input");
        clickElement(state,"State");
        clickElement(By.xpath("//li[normalize-space()='"+stateInput+"']"),"state");
        sendKeys(zipCode, zipCodeInput, "Zipcode input");
        sendKeys(cellPhone, cellPhoneInput, "Cell phone input");
        sendKeys(homePhone, homePhoneInput, "Home Phone input");
        sendKeys(driverLicense, driverLicenseInput, "Driver's License input");
        clickElement(driverState,"Driver's State");
        clickElement(By.xpath("//li[normalize-space()='"+driverStateInput+"']"),"Select State");

//        sendKeys(street, ApplicationConstants.KansasStreet, "Street input");
//        sendKeys(city, ApplicationConstants.KansasCity, "City input");
//        clickElement(state,"State");
//        clickElement(By.xpath("//li[normalize-space()='Kansas']"),"state");
//        sendKeys(zipCode, ApplicationConstants.KansasZipCode, "Zipcode input");
//        sendKeys(cellPhone, ApplicationConstants.cellPhone, "Cell phone input");
//        sendKeys(homePhone, ApplicationConstants.homePhone, "Home Phone input");
//        sendKeys(driverLicense, ApplicationConstants.driverLicense, "Driver's License input");
//        clickElement(driverState,"Driver's State");
//        clickElement(By.xpath("//li[normalize-space()='Kansas']"),"Select State");

    }

    public boolean isStateDisplayed() {
        return isElementPresent(state, "State");
    }

    public void fillAddNewCard() {
        clickElement(cardTab, "Card tab");
        sendKeys(cardNumber, ApplicationConstants.CARD_NUMBER, "Card Number");
        sendKeys(cardExpiry, ApplicationConstants.CARD_EXPIRY, "Card Expiry");
        sendKeys(cardCVV, ApplicationConstants.CARD_CVV, "Card CVV");
        clickElement(yesButton, "Yes");
        clickElement(cardSubmitButton, "Submit");
    }

    public void editCards() {
        clickElement(editCard, "Edit");
        sendKeys(cardExpiry, ApplicationConstants.CARD_EXPIRY, "Card Expiry");
        clickElement(saveCard, "Save");
    }

    public void clickProfileTab(String value) {
        clickElement(By.xpath("//span[normalize-space()='" + value + "']"), "Password Tab");
    }

    public void sendValuesForGotoProfileAttribute(String attribute, String value) {
        sendKeys(By.xpath("//label[normalize-space()='" + attribute + "']//parent::div//child::input"), value, "" + attribute + " is not displayed");
    }

    public String goProfileErrorValidation(String value) {
        sleep(5000);
        return getTextFromElement(By.xpath("//label[normalize-space()='" + value + "']//parent::div//child::p"), "" + value + "");
    }

    public void changePassword() {
        sendKeys(password, ApplicationConstants.password, "Password");
        sendKeys(confirmPassword, ApplicationConstants.confirmPassword, "Confirm Password");
        clickPasswordSubmit();
    }

    public void clickPasswordSubmit() {
        clickElement(passwordSubmitButton, "Submit");
    }

    public void clickSubmit(String input) {
        clickElement(By.xpath("//span[text()='" + input + "']//parent::h6//parent::div//parent::div//parent::div//parent::div//parent::div//following::button[normalize-space()='Submit']"), "Submit");
    }

    public String cardSaveAlert() {
        return getTextFromElement(alert, "Alert");
    }

    @Override
    protected void setPageproperties() {

    }
}