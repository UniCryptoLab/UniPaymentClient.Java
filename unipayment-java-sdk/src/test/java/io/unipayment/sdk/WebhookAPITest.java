package io.unipayment.sdk;

import io.unipayment.sdk.model.ApiResponse;
import io.unipayment.sdk.model.UpdateNotifyURLRequest;
import io.unipayment.sdk.model.UpdateSecretKeyRequest;
import io.unipayment.sdk.model.WebhookResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebhookAPITest extends BaseAPITest {

    private static WebhookAPI webhookAPI;

    @BeforeAll
    public static void setUp() {
        webhookAPI = WebhookAPI.getInstance(configuration);
    }

    @Test
    public void testUpdateNotifyURL() {
        UpdateNotifyURLRequest updateNotifyURLRequest = new UpdateNotifyURLRequest();
        updateNotifyURLRequest.setNotifyURL("https://en7exsmaa68jo.x.pipedream.net");
        ApiResponse<WebhookResponse> apiResponse = webhookAPI.updateNotifyUrl(updateNotifyURLRequest);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    public void testUpdateSecretKey() {
        UpdateSecretKeyRequest updateSecretKeyRequest = new UpdateSecretKeyRequest();
        updateSecretKeyRequest.setSecretKey("s3cretKey@2024%");
        ApiResponse<WebhookResponse> apiResponse = webhookAPI.updateSecretKey(updateSecretKeyRequest);
        assertEquals(apiResponse.getCode(), "OK");
    }
}
