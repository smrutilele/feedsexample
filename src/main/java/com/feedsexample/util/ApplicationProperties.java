package com.feedsexample.util;

import java.util.Properties;

public class ApplicationProperties {
    private static final String ROUTE_DEFAULT_PROPERTIES = "feedsexample.default.properties";
    private static final Properties PROPERTIES = ApplicationProperties.loadProperties();

    private static Properties loadProperties() {
        Properties properties = new Properties();
        properties.putAll(PropertiesUtils.load(ROUTE_DEFAULT_PROPERTIES));
        return properties;
    }

    private ApplicationProperties() {
    }

    public static String getString(String key) {
        return ApplicationProperties.getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        return PropertiesUtils.getString(PROPERTIES, key, defaultValue);
    }

    public static int getInt(String key) {
        return ApplicationProperties.getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        return PropertiesUtils.getInt(PROPERTIES, key, defaultValue);
    }
}
