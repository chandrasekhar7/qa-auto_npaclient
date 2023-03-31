package com.netpay.exceptions;

public class httpRequestException extends RuntimeException{
	
	   /**
	 *  can be used to throw custom exceptions related to APIs
	 */
	private static final long serialVersionUID = 5786573231318937221L;

	public httpRequestException() {
	        super();
	    }

	    public httpRequestException(String message) {
	        super(message);
	    }

	    public httpRequestException(String message, Throwable exception) {
	        super(message, exception);
	    }

}
