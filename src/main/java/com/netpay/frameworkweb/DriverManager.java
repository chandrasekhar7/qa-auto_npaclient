package com.netpay.frameworkweb;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.pages.utilities.ApplicationProperties;
import com.pages.utilities.LoggerProperties;

public abstract class DriverManager {

	protected RemoteWebDriver instance;
	protected ApplicationProperties appPropertiesWeb;
	

	// factory method to create driver instance
	protected abstract void createDriver();
	public  Logger getLogger() {
		LoggerProperties loggerProperties = LoggerProperties.getInstance();
		return loggerProperties.getLogger();
	}
	
	public String getSauceLabURL() throws IOException {
		appPropertiesWeb = ApplicationProperties.getInstance("web");
   	 	String username = appPropertiesWeb.getProperty("SAUCE_USERNAME");
        String accessKey = appPropertiesWeb.getProperty("SAUCE_ACCESS_KEY");
        return "https://" + username+ ":" + accessKey + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
	}

	public RemoteWebDriver getDriver() {
			synchronized (DriverManager.class) {
				if (instance == null) {
					createDriver();
				}
			}
		return instance;
	}

	public void quit() {
		if (instance != null) {
			instance.quit();
			instance = null;
		}
	}
}
