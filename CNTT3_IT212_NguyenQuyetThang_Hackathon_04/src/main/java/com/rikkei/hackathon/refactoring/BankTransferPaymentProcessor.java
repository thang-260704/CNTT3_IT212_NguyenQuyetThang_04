package com.rikkei.hackathon.refactoring;

import org.springframework.stereotype.Component;

@Component
public class BankTransferPaymentProcessor implements PaymentProcessor {
    private static final String TYPE = "BANK_TRANSFER";

    @Override
    public boolean supports(String paymentType) {
        return TYPE.equalsIgnoreCase(paymentType);
    }

    @Override
    public void process(Student student, Course course, double fee) {
        System.out.println("Waiting for manual bank transfer confirmation for student " + student.getId());
    }
}
