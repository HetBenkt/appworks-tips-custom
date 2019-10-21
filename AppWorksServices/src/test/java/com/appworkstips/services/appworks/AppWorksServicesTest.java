package com.appworkstips.services.appworks;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AppWorksServicesTest {

    @Test
    public void getRandomIntValue() {
        String randomInt = AppWorksServices.getRandomIntValue();
        Assert.assertNotEquals("-1", randomInt);
    }

    @Test
    public void getRandomIntValueNormalInput() {
        String randomInt = AppWorksServices.getRandomIntValueMinMax("0", "2");
        Assert.assertNotEquals("-1", randomInt);
    }

    @Test
    public void getRandomIntValueWrongInput() {
        String randomInt = AppWorksServices.getRandomIntValueMinMax("a", "z");
        Assert.assertEquals("-1", randomInt);
    }

    @Test
    public void getRandomIntValueNegagiveInput() {
        String randomInt = AppWorksServices.getRandomIntValueMinMax("-5", "-10");
        Assert.assertEquals("-1", randomInt);
    }

    @Test
    public void getRandomInt() {
        int randomInt = AppWorksServices.getRandomInt();
        Assert.assertTrue(randomInt >= Integer.MIN_VALUE && randomInt <= Integer.MAX_VALUE);
    }

    @Test
    public void getRandomIntObject() {
        Integer randomInteger = AppWorksServices.getRandomIntObject();
        Assert.assertTrue(randomInteger >= Integer.MIN_VALUE && randomInteger <= Integer.MAX_VALUE);
    }

    @Test
    public void getRandomIntObjectList() {
        List<Integer> randomIntObjectList = AppWorksServices.getRandomIntObjectList();
        Assert.assertTrue(randomIntObjectList.size() == 1);
    }
}