package com.appworkstips.models;

public class User {
    private String name;
    private String fullName;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("'%s, %s'", this.name, this.fullName);
    }
}
