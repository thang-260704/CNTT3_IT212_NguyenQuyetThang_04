package com.rikkei.hackathon.refactoring;

import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

    @Override
    public void sendEnrollmentSuccess(Student student, Course course) {
        System.out.println("Sending welcome email to " + student.getFullName());
    }
}
