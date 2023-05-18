package com.kirylkhrystsenka.schoolapp.dao.entities;


public class Student implements HasId<Long>{
    private final Long id;
    private final Group group;
    private final String firstName;
    private final String lastName;

    public Student(Long id, Group group, String firstName, String lastName) {
        this.id = id;
        this.group = group;
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
               "id=" + id +
               ", group=" + group +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               '}';
    }
}
