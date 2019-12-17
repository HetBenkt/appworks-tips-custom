package com.appworkstips.models;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    private String name = "MyName";
    private String fullName = "MyFullName";

    @Test
    public void toStringTest() {
        User user = new User();
        user.setName(name);
        user.setFullName(fullName);
        String actual = user.toString();
        String expected = String.format("'%s, %s'", this.name, this.fullName);
        Assert.assertEquals(expected, actual);
    }
}