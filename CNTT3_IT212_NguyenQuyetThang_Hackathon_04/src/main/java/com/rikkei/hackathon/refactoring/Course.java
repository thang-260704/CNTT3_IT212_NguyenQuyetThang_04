package com.rikkei.hackathon.refactoring;

import java.util.Objects;

public class Course {
    private final String id;
    private final String title;
    private final double price;

    public Course(String id, String title, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Course price must not be negative");
        }
        this.id = Objects.requireNonNull(id, "Course id must not be null");
        this.title = Objects.requireNonNull(title, "Course title must not be null");
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}
