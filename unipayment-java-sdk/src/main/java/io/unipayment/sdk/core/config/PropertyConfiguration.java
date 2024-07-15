package io.unipayment.sdk.core.config;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

/**
 * Property Configuration - This class is used to load Unipayment configurations from properties;
 * either system properties or property file
 */
public final class PropertyConfiguration extends Configuration {
    private static final String CONFIGURATION_PROPERTIES = "unipayment-sdk.properties";
    private static final String HOST = "host";
    private static final String DEBUG = "debug";
    private static final String API_VERSION = "version";
    private static final String CLIENT_ID = "clientId";
    private static final String CLIENT_SECRET = "clientSecret";
    private static final String APP_ID = "appId";
    private static final String PREFIX = "unipayment.api.";

    /**
     * Creates a root PropertyConfiguration. This constructor is equivalent to new PropertyConfiguration("/").
     */
    public PropertyConfiguration() {
        this("/");
    }

    /**
     * Use this Constructor to load configuration from a file
     *
     * @param file Property File
     * @throws FileNotFoundException
     */
    public PropertyConfiguration(File file) throws FileNotFoundException {
        this(new FileInputStream(file));
    }

    /**
     * Use this Constructor to load configuration from an Input Stream
     *
     * @param is
     */
    public PropertyConfiguration(InputStream is) {
        super();
        Properties props = new Properties();
        loadProperties(props, is);
        setFieldsWithTreePath(props, "/");
    }

    public PropertyConfiguration(Properties props, String treePath) {
        super();
        setFieldsWithTreePath(props, treePath);
    }

    public PropertyConfiguration(Properties props) {
        this(props, "/");
    }

    PropertyConfiguration(String treePath) {
        super();
        Properties props;
        // load from system properties
        try {
            props = (Properties) System.getProperties().clone();
            normalize(props);
        } catch (SecurityException ignore) {
            // Unsigned applets are not allowed to access System properties
            props = new Properties();
        }
        // override System properties with ./configuration.properties in the classpath
        loadProperties(props, FileUtils.getFile(FileUtils.getFile("/"), CONFIGURATION_PROPERTIES).getPath());

        // then, override with /configuration.properties in the classpath
        loadProperties(props, Configuration.class.getResourceAsStream("/" + CONFIGURATION_PROPERTIES));

        // then, override with /configuration.properties in the user directory
        String filePath = FileUtils.getFile(FileUtils.getUserDirectory(), CONFIGURATION_PROPERTIES).getPath();
        loadProperties(props, filePath);

        setFieldsWithTreePath(props, treePath);
    }

    private void loadProperties(Properties props, String filePath) {
        FileInputStream fis = null;
        try {
            fis = FileUtils.openInputStream(new File(filePath));
            props.load(fis);
            normalize(props);
        } catch (Exception ignore) {
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ignore) {

            }
        }
    }

    private void loadProperties(Properties props, InputStream is) {
        try {
            props.load(is);
            normalize(props);
        } catch (Exception ignore) {
        }
    }

    private void normalize(Properties props) {
        ArrayList<String> toBeNormalized = new ArrayList<>();
        for (Object key : props.keySet()) {
            String keyStr = (String) key;
            if (keyStr.contains(PREFIX)) {
                toBeNormalized.add(keyStr);
            }
        }
        for (String keyStr : toBeNormalized) {
            if (props.containsKey(keyStr)) {
                String property = props.getProperty(keyStr);
                String newKey = keyStr.replace(PREFIX, "");
                props.setProperty(newKey, property);
            }
        }
    }

    private void setFieldsWithTreePath(Properties props, String treePath) {
        setFieldsWithPrefix(props, "");
        String[] splitArray = treePath.split("/");
        String prefix = null;
        for (String split : splitArray) {
            if (!"".equals(split)) {
                if (null == prefix) {
                    prefix = split + ".";
                } else {
                    prefix += split + ".";
                }
                setFieldsWithPrefix(props, prefix);
            }
        }
    }

    private void setFieldsWithPrefix(Properties props, String prefix) {
        if (notNull(props, prefix, HOST)) {
            setHost(getString(props, prefix, HOST));
        }

        if (notNull(props, prefix, DEBUG)) {
            setDebug(getBoolean(props, prefix, DEBUG));
        }

        if (notNull(props, prefix, API_VERSION)) {
            setApiVersion(getString(props, prefix, API_VERSION));
        }

        if (notNull(props, prefix, CLIENT_ID)) {
            setClientId(getString(props, prefix, CLIENT_ID));
        }

        if (notNull(props, prefix, CLIENT_SECRET)) {
            setClientSecret(getString(props, prefix, CLIENT_SECRET));
        }

        if (notNull(props, prefix, APP_ID)) {
            setAppId(getString(props, prefix, APP_ID));
        }
    }

    private boolean getBoolean(Properties props, String prefix, String name) {
        String value = props.getProperty(prefix + name);
        return Boolean.parseBoolean(value);
    }

    private String getString(Properties props, String prefix, String name) {
        return props.getProperty(prefix + name);
    }

    private boolean notNull(Properties props, String prefix, String name) {
        return props.getProperty(prefix + name) != null;
    }
}
