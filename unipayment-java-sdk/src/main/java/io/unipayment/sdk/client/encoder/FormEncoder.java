package io.unipayment.sdk.client.encoder;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;

public final class FormEncoder implements Encoder {

    @Override
    public void encode(Object object, Type type, RequestTemplate requestTemplate) throws EncodeException {
        requestTemplate.body(convertToQueryString((Map<String, String>) object));
    }

    public static String convertToQueryString(Map<String, String> params) {
        StringJoiner queryString = new StringJoiner("&");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String encodedKey = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8);
            String encodedValue = URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8);
            queryString.add(encodedKey + "=" + encodedValue);
        }
        return queryString.toString();
    }
}
