package com.rikkei.hackathon.refactoring;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentServiceTest {

    @Test
    void shouldApplyEarlyBirdCouponAndGrantAccess() {
        Student student = new Student("S001", "Nguyen Quyet Thang");
        Course course = new Course("C001", "Spring Boot Security", 1_000_000D);
        EnrollmentService service = createService();

        Enrollment enrollment = service.enroll(student, course, "EARLYBIRD", "STRIPE");

        assertEquals(700_000D, enrollment.getFinalFee());
        assertTrue(student.hasAccess("C001"));
    }

    @Test
    void shouldSupportInstallmentPaymentWithoutChangingEnrollmentService() {
        Student student = new Student("S002", "Test Student");
        Course course = new Course("C002", "System Design", 2_000_000D);
        EnrollmentService service = createService();

        Enrollment enrollment = service.enroll(student, course, null, "INSTALLMENT");

        assertEquals(2_000_000D, enrollment.getFinalFee());
        assertTrue(student.hasAccess("C002"));
    }

    @Test
    void shouldThrowWhenPaymentMethodIsUnsupported() {
        EnrollmentService service = createService();
        Student student = new Student("S003", "Test Student");
        Course course = new Course("C003", "AI Application", 1_500_000D);

        assertThrows(
                UnsupportedPaymentMethodException.class,
                () -> service.enroll(student, course, null, "CRYPTO")
        );
    }

    private EnrollmentService createService() {
        CouponPolicyRegistry couponRegistry = new CouponPolicyRegistry(List.of(
                new NoCouponPolicy(),
                new EarlyBirdCouponPolicy(),
                new AlumniCouponPolicy()
        ));

        PaymentProcessorRegistry paymentRegistry = new PaymentProcessorRegistry(List.of(
                new StripePaymentProcessor(),
                new BankTransferPaymentProcessor(),
                new InstallmentPaymentProcessor()
        ));

        return new EnrollmentService(
                couponRegistry,
                paymentRegistry,
                new AccessGrantService(),
                (student, course) -> { }
        );
    }
}
