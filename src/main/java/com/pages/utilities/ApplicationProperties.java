package com.pages.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {
	
	private Properties properties;
	private static ApplicationProperties apiInstance = null;
	private static ApplicationProperties webInstance = null;
	private static ApplicationProperties dbInstance = null;
	private static ApplicationProperties envInstance = null;
	
	public static ApplicationProperties getInstance(String propFileName) throws IOException{
	  synchronized (ApplicationProperties.class) {
		if(propFileName.equals("db")) {
                if (dbInstance == null) {      
                	dbInstance = new ApplicationProperties(propFileName);
                }
			return dbInstance;
		}
		if(propFileName.equals("api")) {
                if (apiInstance == null) {  
                	apiInstance = new ApplicationProperties(propFileName);
                }
			return apiInstance;
		}else {
                if (webInstance == null) {  
                	webInstance = new ApplicationProperties(propFileName);
                }
			return webInstance;
			}
		}
	
	}
	
	public static ApplicationProperties getEnvironment(String env) throws IOException{
		synchronized (ApplicationProperties.class) {
        if (envInstance == null) {
        	envInstance = new ApplicationProperties(env);
        	}
        return envInstance;
		}
	}
	public static ApplicationProperties getEnvironment() {
		synchronized (ApplicationProperties.class) {
		return envInstance;
	}
	}
	
	private ApplicationProperties(String propFile) throws IOException{
		String filePath = "./src/test/resources/properties/"+propFile+".properties";
		try(FileReader reader = new FileReader(filePath)){
			properties = new Properties();
			properties.load(reader);
			
		} catch (IOException e) {
			LoggerProperties.getInstance().getLogger().info(e);
		}
	}
	
	public String getProperty(String name){
		return properties.getProperty(name);
	}
}
