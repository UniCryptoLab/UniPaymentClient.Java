package io.unipayment.sdk;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.unipayment.sdk.core.config.Configuration;
import io.unipayment.sdk.exception.UnipaymentSdkException;
import io.unipayment.sdk.model.*;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface ExchangeAPI {

    /**
     * Get Quote
     */
    @RequestLine("GET /exchange/quote")
    ApiResponse<Quote> getQuote(@QueryMap QuoteRequest quoteRequest) throws UnipaymentSdkException;

    /**
     * Accept Quote
     */
    @RequestLine("PUT /exchange/quote/{id}")
    ApiResponse<ExchangeOrder> acceptQuote(@Param("id") String quoteId) throws UnipaymentSdkException;

    /**
     * Query Exchange Orders
     */
    @RequestLine("GET /exchange/orders")
    ApiResponse<QueryResult<ExchangeOrder>> queryExchangeOrders(@QueryMap QueryExchangeOrderRequest queryExchangeOrderRequest) throws UnipaymentSdkException;

    /**
     * Get Exchange Order
     */
    @RequestLine("GET /exchange/orders/{id}")
    ApiResponse<ExchangeOrder> getExchangeOrder(@Param("id") String id) throws UnipaymentSdkException;


    /**
     * Create Default Client
     *
     * @return Instance of Exchange API
     */
    static ExchangeAPI getInstance() {
        return new UnipaymentDefaultClient<ExchangeAPI>().getDefaultClient(ExchangeAPI.class);
    }

    /**
     * Create a client with a configuration
     *
     * @param configuration {@link Configuration}
     * @return Instance of Exchange API
     */
    static ExchangeAPI getInstance(Configuration configuration) {
        return new UnipaymentDefaultClient<ExchangeAPI>().getClient(ExchangeAPI.class, configuration);
    }
}
