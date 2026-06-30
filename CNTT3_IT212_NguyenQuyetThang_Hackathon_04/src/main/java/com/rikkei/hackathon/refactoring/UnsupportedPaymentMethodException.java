package com.rikkei.hackathon.refactoring;

public class UnsupportedPaymentMethodException extends RuntimeException {
    public UnsupportedPaymentMethodException(String paymentType) {
        super("Unsupported payment method: " + paymentType);
    }
}
