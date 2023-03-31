package com.netpay.frameworkweb;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverManager extends DriverManager {

	@Override
	protected void createDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities cp=new DesiredCapabilities();
		cp.setCapability(ChromeOptions.CAPABILITY,options);
		options.merge(cp);
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("autofill.profile_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-notifications");
//		options.addArguments("--headless");
		instance = new ChromeDriver(options);
	}
}
