package io.unipayment.sdk;

import io.unipayment.sdk.model.ApiResponse;
import io.unipayment.sdk.model.Currency;
import io.unipayment.sdk.model.ExchangeRate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommonAPITest extends BaseAPITest {

    private static CommonAPI commonAPI;

    @BeforeAll
    public static void setUp() {
        commonAPI = CommonAPI.getInstance(configuration);
    }

    @Test
    public void testQueryIps() {
        ApiResponse<List<String>> apiResponse = commonAPI.queryIps(getAccessToken());
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    public void testGetCurrencies() {
        ApiResponse<List<Currency>> apiResponse = commonAPI.getCurrencies(getAccessToken());
        assertEquals(apiResponse.getCode(), "OK");
        assertFalse(apiResponse.getData().isEmpty());
    }

    @Test
    public void testGetExchangeRateByCurrencyPair() {
        ApiResponse<ExchangeRate> apiResponse = commonAPI.getExchangeRateByCurrencyPair(getAccessToken(), "USD", "BTC");
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    public void testGetExchangeRateByFiatCurrency() {
        ApiResponse<List<ExchangeRate>> apiResponse = commonAPI.getExchangeRateByFiatCurrency(getAccessToken(), "USD");
        assertEquals(apiResponse.getCode(), "OK");
        assertFalse(apiResponse.getData().isEmpty());
    }

    @Test
    public void testCheckIPN() {
        ApiResponse<Void> apiResponse = commonAPI.checkIPN(getAccessToken(), "{\"ipn_type\":\"invoice\",\"event\":\"invoice_created\",\"app_id\":\"cee1b9e2-d90c-4b63-9824-d621edb38012\",\"invoice_id\":\"12wQquUmeCPUx3qmp3aHnd\",\"order_id\":\"ORDER_123456\",\"price_amount\":2.0,\"price_currency\":\"USD\",\"network\":null,\"address\":null,\"pay_currency\":null,\"pay_amount\":0.0,\"exchange_rate\":0.0,\"paid_amount\":0.0,\"confirmed_amount\":0.0,\"refunded_price_amount\":0.0,\"create_time\":\"2022-09-14T04:57:54.5599307Z\",\"expiration_time\":\"2022-09-14T05:02:54.559933Z\",\"status\":\"New\",\"error_status\":\"None\",\"ext_args\":\"Merchant Pass Through Data\",\"transactions\":null,\"notify_id\":\"fd58cedd-67c6-4053-ae65-2f6fb09a7d2c\",\"notify_time\":\"0001-01-01T00:00:00\"}");
        assertEquals(apiResponse.getCode(), "OK");
        assertEquals(apiResponse.getMsg(), "IPN is verified.");
    }
}