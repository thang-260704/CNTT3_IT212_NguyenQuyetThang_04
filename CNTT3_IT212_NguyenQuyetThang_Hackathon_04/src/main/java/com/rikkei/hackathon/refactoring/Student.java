package com.rikkei.hackathon.refactoring;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Student {
    private final String id;
    private final String fullName;
    private final Set<String> accessibleCourseIds = new HashSet<>();

    public Student(String id, String fullName) {
        this.id = Objects.requireNonNull(id, "Student id must not be null");
        this.fullName = Objects.requireNonNull(fullName, "Student name must not be null");
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void addAccess(String courseId) {
        accessibleCourseIds.add(Objects.requireNonNull(courseId, "Course id must not be null"));
    }

    public boolean hasAccess(String courseId) {
        return accessibleCourseIds.contains(courseId);
    }

    public Set<String> getAccessibleCourseIds() {
        return Collections.unmodifiableSet(accessibleCourseIds);
    }
}
