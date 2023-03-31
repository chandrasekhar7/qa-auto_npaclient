package com.netpay.pages.common;

import org.openqa.selenium.WebDriver;
import com.pages.utilities.ApplicationProperties;

public class PageHeader {

	private static PageHeader instance = null;
	protected WebDriver driver;
	
	public static PageHeader getInstance(WebDriver driver){
            synchronized (ApplicationProperties.class) {
                if (instance == null) {
                    instance = new PageHeader(driver);
                }else {
        	instance.driver = driver;
        }
		
		return instance;
	}
	}
	
	private PageHeader(WebDriver driver) {
		this.driver = driver;
	}
	
}
