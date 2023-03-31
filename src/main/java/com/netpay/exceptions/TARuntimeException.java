package com.netpay.exceptions;

public class TARuntimeException extends TABaseException {

    /**
	 * can be used to throw custom exceptions related to test environment run time exceptions
	 */
	private static final long serialVersionUID = -2077393822310398689L;

	public TARuntimeException() {
        super();
    }

    public TARuntimeException(String message) {
        super(message);
    }

    public TARuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TARuntimeException(Throwable cause) {
        super(cause);
    }

    protected TARuntimeException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
