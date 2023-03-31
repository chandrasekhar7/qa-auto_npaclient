package com.netpay.exceptions;

public class TAFRuntimeException extends RuntimeException {

    /**
	 * can be used to throw custom exceptions related to test application failures
	 */
	private static final long serialVersionUID = 891230567465707657L;

	public TAFRuntimeException() {
        super();
    }

    public TAFRuntimeException(String message) {
        super(message);
    }

    public TAFRuntimeException(Throwable exception) {
        super(exception);
    }

    public TAFRuntimeException(String message, Throwable exception) {
        super(message, exception);
    }

    public void throwIf(final boolean conditionResult) {
        if (conditionResult) {
            throw this;
        }
    }

}