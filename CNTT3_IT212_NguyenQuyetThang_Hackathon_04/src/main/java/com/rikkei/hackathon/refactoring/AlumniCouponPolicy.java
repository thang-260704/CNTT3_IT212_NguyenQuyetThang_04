package com.rikkei.hackathon.refactoring;

import org.springframework.stereotype.Component;

@Component
public class AlumniCouponPolicy implements CouponPolicy {
    private static final String CODE = "ALUMNI";
    private static final double DISCOUNT_AMOUNT = 500_000D;

    @Override
    public boolean supports(String couponCode) {
        return CODE.equalsIgnoreCase(couponCode);
    }

    @Override
    public double apply(double originalFee) {
        return Math.max(0, originalFee - DISCOUNT_AMOUNT);
    }
}
