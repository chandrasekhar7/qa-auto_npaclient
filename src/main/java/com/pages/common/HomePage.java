package com.pages.common;

import com.netpay.baseweb.BasePage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class HomePage extends BasePage {
    @FindBy(xpath = "//h1[@class='merchant_name']")
    private WebElement pageHeader;
    @FindBy(xpath = "//a[normalize-space()='Go to Profile']")
    private WebElement goToProfile;
    @FindBy(xpath = "//a[normalize-space()='Request A Draw']")
    private WebElement requestDraw;
    @FindBy(name = "drawValue")
    private WebElement enterYourDrawAmount;
    @FindBy(xpath = "//button[normalize-space()='Confirm']")
    private WebElement confirmButton;
    @FindBy(xpath = "//form[@id='request-draw']//p[normalize-space()='Draw amount is required']")
    private WebElement drawAmountRequired;
    @FindBy(xpath = "//h2")
    private WebElement requestDrawHeader;
    @FindBy(xpath = "//img[@src='/Images/user-avatars/0.png']")
    private WebElement profileIcon;
    @FindBy(xpath = "//a[normalize-space()='Logout']")
    private WebElement logout;
    @FindBy(xpath = "//span[normalize-space()='Loans']")
    private WebElement loans;
    @FindBy(xpath = "//h6[normalize-space()='Current Loans']")
    private WebElement currentLoans;

    //Security question
    @FindBy(id = "mui-component-select-security_question_one.option")
    private WebElement questionOne;
    @FindBy(xpath = "//li[@data-value='1']")
    private WebElement chooseQuestionOne;
    @FindBy(name = "security_question_one.answer")
    private WebElement answerOne;

    @FindBy(id = "mui-component-select-security_question_two.option")
    private WebElement questionTwo;
    @FindBy(xpath = "//li[@data-value='3']")
    private WebElement chooseQuestionTwo;
    @FindBy(name = "security_question_two.answer")
    private WebElement answerTwo;

    @FindBy(id = "mui-component-select-security_question_three.option")
    private WebElement questionThree;
    @FindBy(xpath = "//li[@data-value='6']")
    private WebElement chooseQuestionThree;
    @FindBy(name = "security_question_three.answer")
    private WebElement answerThree;

    @FindBy(xpath = "//button[normalize-space()='Submit']")
    private WebElement submitButton;

    //Select payment
    @FindBy(xpath = "//input[contains(@name,'mui')]")
    private WebElement selectPaymentOption;

    public HomePage(WebDriver driver) throws IOException {
        super(driver);
    }

    public static HomePage using(WebDriver driver) {
        HomePage homePage = null;
        try {
            homePage = new HomePage(driver);
        } catch (IOException e) {
            System.out.println(e);
        }
        return homePage;
    }

    public void clickGoToProfileButton() {
        clickElement(goToProfile, "Go To Profile");
    }

    public void fillSecurityQuestion() {
        String answer = RandomStringUtils.randomAlphabetic(8);
        clickElement(questionOne, "Question One");
        clickElement(chooseQuestionOne, "Choose Question One");
        sendKeys(answerOne, answer, "Answer one");

        clickElement(questionTwo, "Question Two");
        clickElement(chooseQuestionTwo, "Choose Question Two");
        sendKeys(answerTwo, answer, "Answer Two");

        clickElement(questionThree, "Question Three");
        clickElement(chooseQuestionThree, "Choose Question Three");
        sendKeys(answerThree, answer, "Answer Three");
        clickElement(submitButton, "Submit");
    }

    public void requestDraw(String input) {
        clickElement(requestDraw, "Request A Draw");
        sendKeys(enterYourDrawAmount, input, "Enter amount");
        clickElement(confirmButton, "Confirm");
    }

    public void makePayment(String input) {
        clickElement(getXpathWithLabel("a", "Make Payment"), "Make Payment");
        clickElementWithout(selectPaymentOption, "Option Button");
        sendKeys(By.name("customAmount"), input, "Payment input");
        clickElement(getXpathWithLabel("button", "Next"), "Next");
        clickElement(By.xpath("(//span[contains(text(),'Ending in')])[1]"), "Select card");
        clickElement(getXpathWithLabel("button", "Next"), "Next");
        clickElement(submitButton, "Submit");
    }

    public boolean isCurrentLoanDisplayed() {
        return isElementPresent(currentLoans, "Current Loans");
    }

    public void clickLoans() {
        clickElement(loans, "Loans");
    }

    public void clickConfirmButtonWithEmptyDrawAmount() {
        sendKeys(enterYourDrawAmount, "", "Enter a draw amount");
        clickElement(confirmButton, "Confirm");
    }

    public String getDrawAmountRequire() {
        return getTextFromElement(drawAmountRequired, "Draw amount");
    }

    public void clickRequestADraw() {
        clickElement(By.xpath("//a[normalize-space()='Request A Draw']"), "Request A Draw");
    }

    public String getRequestDrawHeader() {
        return getTextFromElement(requestDrawHeader, "Request Draw Header");
    }

    public boolean IsProfileDisplayed() {
        return isElementPresent(profileIcon, "Profile Icon");
    }

    public void clickLogout() {
        clickElement(logout, "Logout");
    }

    @Override
    protected void setPageproperties() {
//        this.pageTitle = ApplicationConstants.TITLE_USER;
    }

}