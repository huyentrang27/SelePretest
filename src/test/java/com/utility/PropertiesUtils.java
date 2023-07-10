package com.utility;

import java.io.FileInputStream;
import java.util.Properties;

import static com.common.GlobalVariables.PROJECT_PATH;

public class PropertiesUtils {
    private static final String TEST_CONFIGURATION = PROJECT_PATH + "/resources/configuration/Configuration.properties";
    private static Properties prop = null;

    private static Properties getProp(String path) {
        try {
            System.out.printf("Loading properties : %s%n", path);
            FileInputStream fis = new FileInputStream(path);
            Properties prop = new Properties();
            prop.load(fis);
            return prop;
        } catch (Exception e) {
            System.err.printf("%s not found !%n", path);
        }
        return null;
    }

    public static String getPropValue(String key) {
        return getPropValue(key, null);
    }

    public static String getPropValue(String key, String defaultValue) {
        if (System.getProperty(key) != null && !System.getProperty(key).trim().isEmpty())
            return System.getProperty(key).trim();

        if (prop == null) {
            prop = getProp(TEST_CONFIGURATION);
        }

        if (prop != null && prop.containsKey(key))
            return prop.getProperty(key).trim();

        return defaultValue;
    }
}