package com.rikkei.hackathon.refactoring;

public class Enrollment {
    private final Student student;
    private final Course course;
    private final double finalFee;

    public Enrollment(Student student, Course course, double finalFee) {
        this.student = student;
        this.course = course;
        this.finalFee = finalFee;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getFinalFee() {
        return finalFee;
    }
}
