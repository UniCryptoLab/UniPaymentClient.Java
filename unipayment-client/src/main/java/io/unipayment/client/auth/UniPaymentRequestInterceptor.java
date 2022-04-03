package io.unipayment.client.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.template.UriUtils;
import io.unipayment.client.Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Request Interceptor
 */
public class UniPaymentRequestInterceptor implements RequestInterceptor {
    public static final String UNIPAYMENT_SDK_JAVA = "unipayment_sdk_java";

    private final String host;
    private final String appId;
    private final String apiKey;

    /**
     * Constructor
     *
     * @param host   API Host
     * @param appId  APP ID
     * @param apiKey API Key
     */
    public UniPaymentRequestInterceptor(String host, String appId, String apiKey) {
        this.host = host;
        this.appId = appId;
        this.apiKey = apiKey;
    }

    /**
     * Add Custom Headers
     *
     * @param requestTemplate {@link RequestTemplate}
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String body = "";
        if (requestTemplate.body() != null) {
            body = new String(requestTemplate.body());
            if (body.equalsIgnoreCase("{ }")) {
                body = "";
            }
        }

        String url = (host + requestTemplate.url()).toLowerCase();
        String requestHttpMethod = requestTemplate.method();
        String requestUri = UriUtils.encode(url, StandardCharsets.UTF_8);
        long requestTimeStamp = (System.currentTimeMillis() / 1000);
        String nonce = UUID.randomUUID().toString().replaceAll("-", "");

        String requestContentBase64String = "";
        if (StringUtils.isNotBlank(body)) {
            requestContentBase64String = Utils.getContentBase64String(body);
        }
        String sign = Utils.sign(appId, apiKey, requestHttpMethod, requestUri, requestTimeStamp, nonce, requestContentBase64String);
        requestTemplate.header("Authorization", "Hmac " + sign);

        addAgent(requestTemplate);
    }

    /**
     * Add User Agent to Request Header
     *
     * @param requestTemplate {@link RequestTemplate}
     */
    private void addAgent(RequestTemplate requestTemplate) {
        String version = getClass().getPackage().getImplementationVersion();
        if (version == null) {
            version = "1.0.0.0";
        }
        String userAgent = UNIPAYMENT_SDK_JAVA + "/" + version + " (" + SystemUtils.OS_NAME + " " + SystemUtils.OS_VERSION + ")";
        requestTemplate.header("User-Agent", userAgent);
    }
}
