package com.netpay.exceptions;

public class TATestDataReadException extends TABaseException {
    /**
	 * can be used to throw custom exceptions related to test data reading
	 */
	private static final long serialVersionUID = 7241000485033202395L;

	public TATestDataReadException() {
    }

    public TATestDataReadException(String message) {
        super(message);
    }

    public TATestDataReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public TATestDataReadException(Throwable cause) {
        super(cause);
    }

    public TATestDataReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}