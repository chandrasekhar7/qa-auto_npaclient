package com.netpay.frameworkweb;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverManagerFirefox extends DriverManager {

    @Override
    protected void createDriver() {
    	MutableCapabilities sauceOpts = new MutableCapabilities();
    	//sauceOpts.setCapability("tunnelIdentifier", "smoketests_tunnel");
    	sauceOpts.setCapability("screenResolution", "1920x1200");
    	FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("browserVersion", "latest");
        firefoxOptions.setCapability("platformName", "Windows 10");
        MutableCapabilities capabilities = new MutableCapabilities();
		capabilities.setCapability("sauce:options", sauceOpts);
	    capabilities.setCapability("moz:firefoxOptions", firefoxOptions);
        

        try {
            instance = new RemoteWebDriver(new URL(getSauceLabURL()), capabilities);
        } catch (MalformedURLException e) {
        	getLogger().info("error with sauce labs url"+e);
        } catch (IOException i) {
        	getLogger().info("io exception with sauce labs url"+i);
		}
    }
}
