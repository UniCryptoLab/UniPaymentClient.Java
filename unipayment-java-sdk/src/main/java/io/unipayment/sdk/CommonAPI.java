package io.unipayment.sdk;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.unipayment.sdk.core.config.Configuration;
import io.unipayment.sdk.exception.UnipaymentSdkException;
import io.unipayment.sdk.model.ApiResponse;
import io.unipayment.sdk.model.Currency;
import io.unipayment.sdk.model.ExchangeRate;
import io.unipayment.sdk.model.PingResponse;

import java.util.List;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface CommonAPI {

    /**
     * Ping API
     */
    @RequestLine("GET /ping")
    PingResponse getPing() throws UnipaymentSdkException;

    /**
     * Ping API
     */
    @RequestLine("POST /ping")
    PingResponse postPing() throws UnipaymentSdkException;

    /**
     * Query Ips
     */
    @RequestLine("GET /ips")
    ApiResponse<List<String>> queryIps() throws UnipaymentSdkException;

    /**
     * Get Currencies
     */
    @RequestLine("GET /currencies")
    ApiResponse<List<Currency>> getCurrencies() throws UnipaymentSdkException;

    /**
     * Get Exchange Rate By Currency Pair
     */
    @RequestLine("GET /rates/{fiatCurrency}/{cryptoCurrency}")
    ApiResponse<ExchangeRate> getExchangeRateByCurrencyPair(@Param("fiatCurrency") String fiatCurrency, @Param("cryptoCurrency") String cryptoCurrency) throws UnipaymentSdkException;

    /**
     * Get Exchange Rate By Fiat Currency
     */
    @RequestLine("GET /rates/{fiatCurrency}")
    ApiResponse<List<ExchangeRate>> getExchangeRateByFiatCurrency(@Param("fiatCurrency") String fiatCurrency) throws UnipaymentSdkException;

    /**
     * Check IPN notification
     */
    @RequestLine("POST /ipn")
    @Body("{body}")
    ApiResponse<Void> checkIPN(@Param("body") String notify) throws UnipaymentSdkException;

    /**
     * Create Default Client
     *
     * @return Instance of Common API
     */
    static CommonAPI getInstance() {
        return new UnipaymentDefaultClient<CommonAPI>().getDefaultClient(CommonAPI.class);
    }

    /**
     * Create a client with a configuration
     *
     * @param configuration {@link Configuration}
     * @return Instance of Common API
     */
    static CommonAPI getInstance(Configuration configuration) {
        return new UnipaymentDefaultClient<CommonAPI>().getClient(CommonAPI.class, configuration);
    }
}
