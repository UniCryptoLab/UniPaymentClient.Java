package io.unipayment.sdk;

import io.unipayment.sdk.model.*;
import io.unipayment.sdk.model.enums.ExchangeOrderStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExchangeAPITest extends BaseAPITest {

    private static ExchangeAPI exchangeAPI;
    private static String quoteId;

    @BeforeAll
    public static void setUp() {
        exchangeAPI = ExchangeAPI.getInstance(configuration);
    }

    @Test
    @Order(1)
    public void testGetQuote() {
        QuoteRequest quoteRequest = new QuoteRequest();
        quoteRequest.setFromCurrency("USDT");
        quoteRequest.setToCurrency("BNB");
        quoteRequest.setExchangeAmount(10.0);
        ApiResponse<Quote> apiResponse = exchangeAPI.getQuote(quoteRequest);
        assertEquals(apiResponse.getCode(), "OK");
        quoteId = apiResponse.getData().getQuoteId();
    }

    @Test
    @Order(2)
    public void testAcceptQuoteById() {
        ApiResponse<ExchangeOrder> apiResponse = exchangeAPI.acceptQuote(quoteId);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(3)
    public void testQueryExchangeOrders() {
        ApiResponse<QueryResult<ExchangeOrder>> apiResponse = exchangeAPI.queryExchangeOrders(new QueryExchangeOrderRequest());
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(4)
    public void testGetExchangeOrder() {
        ApiResponse<QueryResult<ExchangeOrder>> queryResultApiResponse = exchangeAPI.queryExchangeOrders(new QueryExchangeOrderRequest());
        assertEquals(queryResultApiResponse.getCode(), "OK");
        String exchangeOrderId = queryResultApiResponse.getData().getModels().stream().filter(d -> ExchangeOrderStatus.COMPLETED.equals(d.getStatus())).findFirst().get().getId();
        ApiResponse<ExchangeOrder> apiResponse = exchangeAPI.getExchangeOrder(exchangeOrderId);
        assertEquals(apiResponse.getCode(), "OK");
    }

}