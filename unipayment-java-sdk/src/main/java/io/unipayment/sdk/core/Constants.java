package io.unipayment.sdk.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.SystemUtils;

public final class Constants {
    public static final String AUTHORIZATON = "Authorization";
    public static final String USER_AGENT = "User-Agent";
    public static final String X_REQUEST_ID = "x-request-id";
    private static final String UNIPAYMENT_JAVA_SDK = "unipayment_java_sdk";
    private static ObjectMapper objectMapper;

    private Constants() {
    }

    public static String getUserAgent() {
        String version = Constants.class.getPackage().getImplementationVersion();
        if (version == null) {
            version = "1.0.0";
        }
        return UNIPAYMENT_JAVA_SDK + "/" + version + " (" + SystemUtils.OS_NAME + " " + SystemUtils.OS_VERSION + ")";
    }

    public static ObjectMapper getMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
                    .configure(SerializationFeature.INDENT_OUTPUT, false);
        }
        return objectMapper;
    }
}
