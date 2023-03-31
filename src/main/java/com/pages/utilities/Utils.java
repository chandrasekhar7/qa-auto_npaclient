package com.pages.utilities;

import com.netpay.exceptions.TAFRuntimeException;

public class Utils {
	private Utils(){
	}
	public static void pauseExecution(int seconds){
		try {
			Thread.sleep((long)seconds*1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new TAFRuntimeException(e);
		}
	}
}
