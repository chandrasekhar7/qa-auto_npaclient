package com.netpay.exceptions;

public class TABaseException extends RuntimeException {

    /**
	 * can be used to throw custom exceptions related to test application
	 */
	private static final long serialVersionUID = -841631197075667192L;

	public TABaseException() {
    }

    public TABaseException(String message) {
        super(message);
    }

    public TABaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public TABaseException(Throwable cause) {
        super(cause);
    }

    public TABaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
