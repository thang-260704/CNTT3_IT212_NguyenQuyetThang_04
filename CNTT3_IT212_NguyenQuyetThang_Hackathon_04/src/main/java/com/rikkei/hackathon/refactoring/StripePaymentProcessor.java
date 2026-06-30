package com.rikkei.hackathon.refactoring;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentProcessor implements PaymentProcessor {
    private static final String TYPE = "STRIPE";

    @Override
    public boolean supports(String paymentType) {
        return TYPE.equalsIgnoreCase(paymentType);
    }

    @Override
    public void process(Student student, Course course, double fee) {
        System.out.println("Processing international card for student " + student.getId());
    }
}
