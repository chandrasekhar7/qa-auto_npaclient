package com.pages.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumUtils {

    public static String getTextFromElement(WebDriver driver, int secondsToWait, String elementDescription, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
    }

    public static String getTextFromElement(WebDriver driver, int secondsToWait, String elementDescription, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

//    public static void clickElement(WebDriver driver, int secondsToWait, String elementDescription, By by) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
//    }
//
//    public static void clickElement(WebDriver driver, int secondsToWait, String elementDescription, WebElement element) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
//        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
//    }

    public static void sendKeys(WebDriver driver, int secondsToWait, String elementDescription, By by, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        clearFieldWithBackspaces(element);
        element.sendKeys(value);
    }

    public static void sendKeys(WebDriver driver, int secondsToWait, String elementDescription, WebElement
            element, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        wait.until(ExpectedConditions.visibilityOf(element));
        clearFieldWithBackspaces(element);
        element.sendKeys(value);
    }

    public static boolean waitXSecondsForElementToBeVisible(WebDriver driver, int i, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(i));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static void clearFieldWithBackspaces(WebElement element) {
        String text = element.getAttribute("value");
        for (int i = 0; i < text.length(); i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
