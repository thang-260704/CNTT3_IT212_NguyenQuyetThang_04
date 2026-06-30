package com.rikkei.hackathon.refactoring;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EnrollmentService {
    private final CouponPolicyRegistry couponPolicyRegistry;
    private final PaymentProcessorRegistry paymentProcessorRegistry;
    private final AccessGrantService accessGrantService;
    private final NotificationService notificationService;

    public EnrollmentService(
            CouponPolicyRegistry couponPolicyRegistry,
            PaymentProcessorRegistry paymentProcessorRegistry,
            AccessGrantService accessGrantService,
            NotificationService notificationService
    ) {
        this.couponPolicyRegistry = couponPolicyRegistry;
        this.paymentProcessorRegistry = paymentProcessorRegistry;
        this.accessGrantService = accessGrantService;
        this.notificationService = notificationService;
    }

    /**
     * Enrolls a student into a course by coordinating pricing, payment, access granting and notification.
     *
     * <p>The method is intentionally kept small and depends on abstractions. New coupon policies,
     * payment methods or notification channels can be added by creating new implementations without
     * modifying this core flow.</p>
     */
    public Enrollment enroll(Student student, Course course, String coupon, String paymentType) {
        Objects.requireNonNull(student, "Student must not be null");
        Objects.requireNonNull(course, "Course must not be null");

        double finalFee = couponPolicyRegistry.findPolicy(coupon).apply(course.getPrice());
        paymentProcessorRegistry.findProcessor(paymentType).process(student, course, finalFee);
        accessGrantService.grantCourseAccess(student, course);
        notificationService.sendEnrollmentSuccess(student, course);

        return new Enrollment(student, course, finalFee);
    }
}
