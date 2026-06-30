package com.rikkei.hackathon.refactoring;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentProcessorRegistry {
    private final List<PaymentProcessor> paymentProcessors;

    public PaymentProcessorRegistry(List<PaymentProcessor> paymentProcessors) {
        this.paymentProcessors = paymentProcessors;
    }

    public PaymentProcessor findProcessor(String paymentType) {
        return paymentProcessors.stream()
                .filter(processor -> processor.supports(paymentType))
                .findFirst()
                .orElseThrow(() -> new UnsupportedPaymentMethodException(paymentType));
    }
}
