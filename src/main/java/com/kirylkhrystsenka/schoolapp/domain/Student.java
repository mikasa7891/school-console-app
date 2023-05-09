package com.kirylkhrystsenka.schoolapp.domain;

public class Student {
    private final int id;
    private final Group group;
    private final String firstName;
    private final String lastName;

    public Student(int id, Group group, String firstName, String lastName) {
        this.id = id;
        this.group = group;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public Group getGroup() {
        return group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
