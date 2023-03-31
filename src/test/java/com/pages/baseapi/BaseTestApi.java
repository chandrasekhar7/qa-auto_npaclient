package com.pages.baseapi;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.pages.utilities.ApplicationProperties;
import com.netpay.constants.ApplicationConstants;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public  class BaseTestApi {
	protected ApplicationProperties apiProperties;
	public  String baseurl;
	public  String uriPath;
	protected Response response; 
	Logger logger;
	
	public BaseTestApi(){
		logger = Logger.getLogger("BaseTestApi.class");
	}


	@BeforeClass
	public void setup() throws IOException{
		apiProperties = ApplicationProperties.getInstance("api");
		baseurl = apiProperties.getProperty(ApplicationConstants.REST_URI);
		uriPath = apiProperties.getProperty(ApplicationConstants.REST_ASSURED_USER_PATH);
		System.out.println("base url in setup is"+baseurl);
		System.out.println("uri in setup is"+uriPath);
		setBaseUri(baseurl);
		getURIPage(uriPath);
		
	}	
	
	public void setBaseUri(String uri) {
		RestAssured.baseURI = uri;
		logger.info("the base uri is set");
	}
	public void getURIPage(String uriParameter) {
		response=RestAssured.given().get("/"+uriParameter).andReturn();
		logger.info("Getting the response from page URI");		    
		
	}
	
	public void responseNotNull() {
		Assert.assertNotNull(response);
	}
	
	public void verifyResponeStatusCode(int statusCode) {
		Assert.assertEquals(response.getStatusCode(),statusCode);
	}
	
	public String getResponeBody() {
		return response.getBody().asString();
	}
	
	public void verifyResponseField(String field, String expectedValue) {
		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(jsonPath.get(field),expectedValue);
	}
}
