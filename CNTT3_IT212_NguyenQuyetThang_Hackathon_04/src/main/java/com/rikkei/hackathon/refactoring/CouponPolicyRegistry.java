package com.rikkei.hackathon.refactoring;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponPolicyRegistry {
    private final List<CouponPolicy> couponPolicies;

    public CouponPolicyRegistry(List<CouponPolicy> couponPolicies) {
        this.couponPolicies = couponPolicies;
    }

    public CouponPolicy findPolicy(String couponCode) {
        return couponPolicies.stream()
                .filter(policy -> policy.supports(couponCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported coupon: " + couponCode));
    }
}
