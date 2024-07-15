package io.unipayment.sdk.exception;

/**
 * Exception for handling code errors such as missing configuration, connection timeout, etc..
 **/
public class UnipaymentSdkException extends RuntimeException {

    public UnipaymentSdkException(String message) {
        super(message);
    }

    public UnipaymentSdkException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnipaymentSdkException(Throwable cause) {
        super(cause);
    }
}