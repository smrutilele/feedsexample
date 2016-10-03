package com.feedsexample.util;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtils {
    public static final String ERROR_MSG = "Error while loading config {}";
    public static final String FILE_NULL_MSG = "Filename cannot be either null or empty";
    private static final Logger LOGGER = LoggerFactory.getLogger((Class)PropertiesUtils.class);

    private PropertiesUtils() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Properties load(String fileName) {
        String file = StringUtils.isBlank(fileName) ? null : fileName;
        Objects.requireNonNull(file, FILE_NULL_MSG);
        Properties props = new Properties();
        InputStream is = null;
        try {
            is = PropertiesUtils.getResourceStream(file);
            props.load(is);
        }
        catch (Exception e) {
            LOGGER.error(ERROR_MSG, file, e);
        }
        finally {
            IOUtils.closeQuietly(is);
        }
        return props;
    }

    private static InputStream getResourceStream(String f) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(f);
        if (is == null) {
            is = PropertiesUtils.class.getResourceAsStream(f);
        }
        return is;
    }

    public static String getString(Properties properties, String key) {
        return PropertiesUtils.getString(properties, key, null);
    }

    public static String getString(Properties properties, String key, String defaultValue) {
        String property = properties.getProperty(key);
        if (property == null) {
            return defaultValue;
        }
        return property;
    }

    public static int getInt(Properties properties, String key) {
        return PropertiesUtils.getInt(properties, key, 0);
    }

    public static int getInt(Properties properties, String key, int defaultValue) {
        String property = properties.getProperty(key);
        if (property == null) {
            return defaultValue;
        }
        return Integer.parseInt(property);
    }
}
