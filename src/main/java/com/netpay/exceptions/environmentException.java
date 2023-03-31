package com.netpay.exceptions;

public class environmentException extends RuntimeException {
	
	/**
	 *  can be used to throw custom exceptions related to environment
	 */
	private static final long serialVersionUID = -6688512165162629361L;

	public  environmentException() {
        super();
    }

    public  environmentException(String message) {
        super(message);
    }

    public environmentException(Throwable exception) {
        super(exception);
    }

    public  environmentException(String message, Throwable exception) {
        super(message, exception);
    }

}
