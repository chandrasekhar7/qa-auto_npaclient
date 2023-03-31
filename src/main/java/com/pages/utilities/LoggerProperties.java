package com.pages.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerProperties {
	
	private Logger logger;
	private static LoggerProperties instance = null;
	
	public static LoggerProperties getInstance(){
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new LoggerProperties();
                }
        return instance;
	}
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	private LoggerProperties(){
		logger = Logger.getLogger("devpinoyLogger");
        PropertyConfigurator.configure("./src/test/resources/config/log4j.properties");
		
	}


}
