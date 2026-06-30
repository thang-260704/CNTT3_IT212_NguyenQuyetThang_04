package com.rikkei.hackathon.refactoring;

import org.springframework.stereotype.Service;

@Service
public class AccessGrantService {

    public void grantCourseAccess(Student student, Course course) {
        student.addAccess(course.getId());
    }
}
