package io.unipayment.client;

/**
 * Uni Payment Exception
 */
public class UniPaymentException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message
     */
    public UniPaymentException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message   the detail message
     * @param throwable the cause
     */
    public UniPaymentException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
