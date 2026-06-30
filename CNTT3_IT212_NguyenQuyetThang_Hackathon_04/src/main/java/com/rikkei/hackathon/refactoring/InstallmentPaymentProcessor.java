package com.rikkei.hackathon.refactoring;

import org.springframework.stereotype.Component;

@Component
public class InstallmentPaymentProcessor implements PaymentProcessor {
    private static final String TYPE = "INSTALLMENT";

    @Override
    public boolean supports(String paymentType) {
        return TYPE.equalsIgnoreCase(paymentType);
    }

    @Override
    public void process(Student student, Course course, double fee) {
        System.out.println("Processing installment payment for student " + student.getId());
    }
}
