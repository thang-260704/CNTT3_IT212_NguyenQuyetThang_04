package com.rikkei.hackathon.refactoring;

import org.springframework.stereotype.Component;

@Component
public class NoCouponPolicy implements CouponPolicy {

    @Override
    public boolean supports(String couponCode) {
        return couponCode == null || couponCode.isBlank();
    }

    @Override
    public double apply(double originalFee) {
        return originalFee;
    }
}
