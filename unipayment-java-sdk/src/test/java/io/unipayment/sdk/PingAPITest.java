package io.unipayment.sdk;

import io.unipayment.sdk.model.PingResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PingAPITest extends BaseAPITest {

    private static PingAPI pingAPI;

    @BeforeAll
    public static void setUp() {
        pingAPI = PingAPI.getInstance(configuration);
    }

    @Test
    public void testGetPing() {
        PingResponse response = pingAPI.get(getAccessToken());
        assertEquals(response.getMsg(), "pong");
    }

    @Test
    public void testPostPing() {
        PingResponse response = pingAPI.post(getAccessToken());
        assertEquals(response.getMsg(), "pong");
    }

}