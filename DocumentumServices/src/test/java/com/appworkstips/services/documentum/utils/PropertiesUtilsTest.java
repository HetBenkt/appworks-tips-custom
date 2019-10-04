package com.appworkstips.services.documentum.utils;

import com.documentum.fc.expr.internal.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PropertiesUtilsTest {
    @Test
    public void buildPropsValues() {
        String[] actualKeys = {"subject", "title", "object_name"};
        String[] actualValues = {"mySubject", "myTitle", "myObjectName"};

        String actual = String.format("%s::%s||%s::%s||%s::%s", actualKeys[0], actualValues[0], actualKeys[1], actualValues[1], actualKeys[2], actualValues[2]);
        List<Pair<String, String>> propsValues = PropertiesUtils.buildPropsValues(actual);

        int index = 0;
        for (Pair<String,String> propValue : propsValues) {
            Assert.assertEquals(actualKeys[index], propValue.first());
            Assert.assertEquals(actualValues[index], propValue.second());
            index++;
        }
    }

    @Test
    public void getProperyValue() {
        String expected = "otadmin";
        String actual = PropertiesUtils.getProperyValue("username");
        Assert.assertEquals(expected, actual);
    }
}
