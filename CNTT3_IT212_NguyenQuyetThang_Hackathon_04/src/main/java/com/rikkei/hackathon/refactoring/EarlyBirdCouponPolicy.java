package com.rikkei.hackathon.refactoring;

import org.springframework.stereotype.Component;

@Component
public class EarlyBirdCouponPolicy implements CouponPolicy {
    private static final String CODE = "EARLYBIRD";
    private static final double DISCOUNT_RATE = 0.7;

    @Override
    public boolean supports(String couponCode) {
        return CODE.equalsIgnoreCase(couponCode);
    }

    @Override
    public double apply(double originalFee) {
        return originalFee * DISCOUNT_RATE;
    }
}
