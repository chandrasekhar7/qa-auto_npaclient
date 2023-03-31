package com.netpay.data;

/*
 * This class represents the test data. 
 * Ideally in a practical situation this data would be derived from test data file, database, etc
 */
public class TestData {
	private TestData(){
	}
	
	public static final String START_URL = "https://www.netpayadvance.com/";
	
	public static final int STATUS_CODE_OK = 200;
	public static final String CONTENT_TYPE = "content-type";
	public static final String EXPECTED_CONTENT_HEADER = "application/json; charset=utf-8";
	public static final int EXPECTED_NUMBER_OF_USERS = 10;
}
