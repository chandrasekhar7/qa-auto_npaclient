package com.pages.utilities;

import org.apache.log4j.Logger;

import io.restassured.RestAssured;

public class SlackWebHook {
	protected  String baseurl; 
	protected  Logger logger;
	
	public SlackWebHook(){
		logger = Logger.getLogger("SlackWebHook.class");
	}
	
	public synchronized void setBaseUri(String url) {
		RestAssured.baseURI = url;
		logger.info("the base uri is set");
	}
	public void sendMessage(String environment,int total,int failed) {
		String bodyText = "{\r\n"
				+ "    \"text\": \"Smoke Tests executed successfully\",\r\n"
				+ "    \"blocks\": [\r\n"
				+ "    	{\r\n"
				+ "    		\"type\": \"section\",\r\n"
				+ "    		\"text\": {\r\n"
				+ "    			\"type\": \"mrkdwn\",\r\n"
				+ "    			\"text\": \"Environment is "+environment+"\"\r\n"
				+ "    		}\r\n"
				+ "    	},\r\n"
				+ "    	{\r\n"
				+ "    		\"type\": \"section\",\r\n"
				+ "    		\"block_id\": \"section567\",\r\n"
				+ "    		\"text\": {\r\n"
				+ "    			\"type\": \"mrkdwn\",\r\n"
				+ "    			\"text\": \"<https://reportportal.americanfirstfinance.com/ui/#smoketests/dashboard/114|:star: Report Portal Link>   \"\r\n"
				+ "    		}\r\n"
				+ "    	},\r\n"
				+ "    	{\r\n"
				+ "    		\"type\": \"section\",\r\n"
				+ "    		\"block_id\": \"section789\",\r\n"
				+ "    		\"fields\": [\r\n"
				+ "    			{\r\n"
				+ "    				\"type\": \"mrkdwn\",\r\n"
				+ "    				\"text\": \"*Total Tests Executed* \"\r\n"
				+ "    			},\r\n"
				+ "                {\r\n"
				+ "    				\"type\": \"mrkdwn\",\r\n"
				+ "    				\"text\": \""+total+" \\n\"\r\n"
				+ "    			},\r\n"
				+ "                {\r\n"
				+ "    				\"type\": \"mrkdwn\",\r\n"
				+ "    				\"text\": \"\\n*Tests Failed* \"\r\n"
				+ "    			},\r\n"
				+ "                {\r\n"
				+ "    				\"type\": \"mrkdwn\",\r\n"
				+ "    				\"text\": \""+failed+" \\n\"\r\n"
				+ "    			}\r\n"
				+ "    		]\r\n"
				+ "    	}\r\n"
				+ "    ]\r\n"
				+ "}";
		baseurl = "https://hooks.slack.com/services/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";
		setBaseUri(baseurl);
		try {
			RestAssured.given().headers("Content-Type","application/json").body(bodyText).post().andReturn();
			}catch(Exception e) {
				e.printStackTrace();
			}
	}

}
