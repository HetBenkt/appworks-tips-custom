package com.appworkstips.services.appworks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppWorksServices {
    private final static Logger LOGGER = Logger.getLogger(AppWorksServices.class.getName());

    public static String getRandomIntValueMinMax(String minValue, String maxValue) {
        String result = "";
        Random r = new Random();
        int max = 0;
        int min = 0;

        try {
            max = Integer.parseInt(maxValue);
            min = Integer.parseInt(minValue);
            result = String.valueOf(r.nextInt((max - min) + 1) + min);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return "-1";
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return "-1";
        }

        LOGGER.info(String.format("Random int: %s", result));
        return result;

    }

    public static String getRandomIntValue() {
        Random r = new Random();
        String result = String.valueOf(r.nextInt());
        LOGGER.info(String.format("Random int: %s", result));
        return result;

    }

    public static int getRandomInt() {
        Random r = new Random();
        int result = r.nextInt();
        LOGGER.info(String.format("Random int: %s", result));
        return result;
    }

    public static Integer getRandomIntObject() {
        Random r = new Random();
        int result = r.nextInt();
        LOGGER.info(String.format("Random int: %s", result));
        return result;
    }

    public static List<Integer> getRandomIntObjectList() {
        Random r = new Random();
        List<Integer> result = new ArrayList<Integer>();
        result.add(r.nextInt());
        LOGGER.info(String.format("Random int: %s", result));
        return result;
    }

}
