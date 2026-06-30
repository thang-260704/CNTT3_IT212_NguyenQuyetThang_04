package com.rikkei.hackathon.refactoring;

public interface PaymentProcessor {
    boolean supports(String paymentType);

    void process(Student student, Course course, double fee);
}
