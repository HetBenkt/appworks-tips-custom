package com.appworkstips.services.documentum.utils;

import com.documentum.fc.expr.internal.Pair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesUtils {
    private static final Logger LOGGER = Logger.getLogger(PropertiesUtils.class.getName());

    /**
     * Creating a list object from string data
     * String data should look like this -> key1::value1||key2::value2||key3::value3
     * The keys are the documentum single valued properties on a dm_document object and the value is passed from the AppWorks entity
     * This is an example -> subject::mySubject||title::myTitle||object_name::myObjectName
     * @param properties the string of data that needs to be parced to a list
     * @return a list with paired key/value items that can be used to update a document
     */
    public static List<Pair<String, String>> buildPropsValues(String properties) {
        List<Pair<String, String>> result = new ArrayList<>();
        StringTokenizer propsValuesSplit = new StringTokenizer(properties, "||");
        while(propsValuesSplit.hasMoreTokens()) {
            String propValue = propsValuesSplit.nextToken();
            StringTokenizer propValueSplit = new StringTokenizer(propValue, "::");
            String key = propValueSplit.nextToken();
            String value = propValueSplit.nextToken();
            result.add(new Pair<>(key, value));
        }
        return result;
    }

    /**
     * Read data from the settings.properties
     * @param key the key value from the properties file to read
     * @return the value that is related to the key
     */
    public static String getProperyValue(String key) {
        Properties prop = new Properties();
        try (InputStream input = PropertiesUtils.class.getClassLoader().getResourceAsStream("setting.properties")) {
            prop.load(input);
            return prop.getProperty(key);
        } catch (java.io.IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
}
