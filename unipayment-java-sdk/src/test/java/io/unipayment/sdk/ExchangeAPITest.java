package io.unipayment.sdk;

import io.unipayment.sdk.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeAPITest extends BaseAPITest {

    private static ExchangeAPI exchangeAPI;
    private static String quoteId;
    private static String exchangeOrderId;

    @BeforeAll
    public static void setUp() {
        exchangeAPI = ExchangeAPI.getInstance(configuration);
    }

    @Test
    @Order(1)
    public void testGetQuote() {
        QuoteRequest quoteRequest = new QuoteRequest();
        quoteRequest.setFromCurrency("USDT");
        quoteRequest.setToCurrency("BTC");
        quoteRequest.setExchangeAmount(1000.0);
        ApiResponse<Quote> apiResponse = exchangeAPI.getQuote(getAccessToken(), quoteRequest);
        assertEquals(apiResponse.getCode(), "OK");
        quoteId = apiResponse.getData().getQuoteId();
    }

    @Test
    @Order(2)
    public void testAcceptQuoteById() {
        ApiResponse<ExchangeOrder> apiResponse = exchangeAPI.acceptQuote(getAccessToken(), quoteId);
        assertEquals(apiResponse.getCode(), "OK");
        exchangeOrderId = apiResponse.getData().getId();
    }

    @Test
    @Order(3)
    public void testQueryExchangeOrders() {
        ApiResponse<QueryResult<ExchangeOrder>> apiResponse = exchangeAPI.queryExchangeOrders(getAccessToken(), new QueryExchangeOrderRequest());
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(4)
    public void testGetExchangeOrder() {
        ApiResponse<ExchangeOrder> apiResponse = exchangeAPI.getExchangeOrder(getAccessToken(), exchangeOrderId);
        assertEquals(apiResponse.getCode(), "OK");
    }

}