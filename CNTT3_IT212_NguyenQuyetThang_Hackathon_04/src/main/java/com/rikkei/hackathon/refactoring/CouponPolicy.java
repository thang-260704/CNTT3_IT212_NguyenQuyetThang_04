package com.rikkei.hackathon.refactoring;

public interface CouponPolicy {
    boolean supports(String couponCode);

    double apply(double originalFee);
}
