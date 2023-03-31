package com.netpay.frameworkweb;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverManagerChrome extends DriverManager {


    @Override
    protected void createDriver() {
        MutableCapabilities sauceOpts = new MutableCapabilities();
       // sauceOpts.setCapability("tunnelIdentifier", "smoketests_tunnel");
        sauceOpts.setCapability("screenResolution", "1920x1200");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "latest");
        chromeOptions.setCapability("platformName", "Windows 10");
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("sauce:options", sauceOpts);
        capabilities.setCapability("goog:chromeOptions", chromeOptions);
        //chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        try {
            instance = new RemoteWebDriver(new URL(getSauceLabURL()), capabilities);
        } catch (MalformedURLException e) {
            getLogger().info("error with sauce labs url" + e);
        } catch (IOException i) {
            getLogger().info("io exception with sauce labs url" + i);
        }
    }
}
