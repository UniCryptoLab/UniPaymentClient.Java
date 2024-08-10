package io.unipayment.sdk.client.auth;

import feign.RequestTemplate;
import io.unipayment.sdk.exception.UnipaymentSdkException;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OauthRequestInterceptorTest {

    private static OauthRequestInterceptor oauthRequestInterceptor;
    private static RequestTemplate requestTemplate;

    @BeforeAll
    public static void setUp() {
        requestTemplate = new RequestTemplate();
        oauthRequestInterceptor = new OauthRequestInterceptor(null);
    }

    @Test
    @Order(1)
    public void testApply_throwExceptionWhenNoAuthorizationHeaderIsProvided() {
        assertThrows(
                UnipaymentSdkException.class,
                () -> oauthRequestInterceptor.apply(requestTemplate)
        );
    }

    @Test
    @Order(2)
    public void testApply_throwExceptionWhenInvalidTokenOrExpiredAccessTokenIsProvided() {
        requestTemplate.header("Authorization", UUID.randomUUID().toString());
        assertThrows(
                UnipaymentSdkException.class,
                () -> oauthRequestInterceptor.apply(requestTemplate)
        );
    }

}