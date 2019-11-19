package com.appworkstips.models;

public class User {
    private String name;
    private String fullName;

    private String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("'%s, %s'", getName(), getFullName());
    }
}
