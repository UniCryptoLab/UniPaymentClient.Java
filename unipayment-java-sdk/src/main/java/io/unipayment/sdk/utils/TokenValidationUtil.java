package io.unipayment.sdk.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import io.unipayment.sdk.core.Constants;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public final class TokenValidationUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenValidationUtil.class);

    private TokenValidationUtil() {
    }

    public static boolean isValid(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            return false;
        }
        try {
            String decodedPart2 = new String(Base64.decodeBase64(parts[1]));
            Map<String, Object> dataMap = Constants.getMapper().readValue(decodedPart2, new TypeReference<>() {
            });
            long exp = Long.parseLong(dataMap.get("exp").toString());
            long expInMillis = exp * 1000;
            LOGGER.info("Access Token expires on: {}", new Date(expInMillis));
            long currentMills = System.currentTimeMillis();
            return expInMillis > currentMills;
        } catch (JsonProcessingException e) {
            LOGGER.warn("Invalid token: {}", token, e);
            return false;
        }
    }

}
