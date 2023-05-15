package com.kirylkhrystsenka.schoolapp.dao.entities;

public class Group implements HasId<Long>{
    private final Long id;
    private final String name;

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }
}
