package io.unipayment.client;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.unipayment.client.models.*;

import java.util.List;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface UniPaymentClient {

    /**
     * Create Invoice
     *
     * @param apiVersion           Api Version
     * @param createInvoiceRequest {@link CreateInvoiceRequest}
     * @return {@link Response<InvoiceModel>}
     * @throws UniPaymentException
     */
    @RequestLine("POST /api/v{apiVersion}/invoices")
    Response<InvoiceModel> createInvoice(@Param("apiVersion") String apiVersion, CreateInvoiceRequest createInvoiceRequest) throws UniPaymentException;

    /**
     * Query Invoices
     *
     * @param apiVersion          Api Version
     * @param queryInvoiceRequest {@link QueryInvoiceRequest}
     * @return {@link Response<QueryResult<InvoiceModel>>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /api/v{apiVersion}/invoices")
    Response<QueryResult<InvoiceModel>> queryInvoices(@Param("apiVersion") String apiVersion, @QueryMap QueryInvoiceRequest queryInvoiceRequest) throws UniPaymentException;

    /**
     * Query Invoice By Id
     *
     * @param apiVersion Api Version
     * @param invoiceId  Invoice Id
     * @return {@link Response<TransactionList>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /api/v{apiVersion}/invoices/{invoiceId}")
    Response<TransactionList> queryInvoiceById(@Param("apiVersion") String apiVersion, @Param("invoiceId") String invoiceId) throws UniPaymentException;

    /**
     * Query Ips
     *
     * @param apiVersion Api Version
     * @return {@link Response<List<String>>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /api/v{apiVersion}/ips")
    Response<List<String>> queryIps(@Param("apiVersion") String apiVersion) throws UniPaymentException;

    /**
     * Get Currencies
     *
     * @param apiVersion Api Version
     * @return {@link Response<List<Currency>>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /api/v{apiVersion}/currencies")
    Response<List<Currency>> getCurrencies(@Param("apiVersion") String apiVersion) throws UniPaymentException;

    /**
     * Get Exchange Rate By Fiat Currency
     *
     * @param apiVersion   Api Version
     * @param fiatCurrency Fiat Currency
     * @return {@link Response<List<ExchangeRate>>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /api/v{apiVersion}/rates/{fiatCurrency}")
    Response<List<ExchangeRate>> getExchangeRateByFiatCurrency(@Param("apiVersion") String apiVersion, @Param("fiatCurrency") String fiatCurrency) throws UniPaymentException;

    /**
     * Get Exchange Rate By Currency Pair
     *
     * @param apiVersion     Api Version
     * @param fiatCurrency   Fiat Currency
     * @param cryptoCurrency Crypto Currency
     * @return {@link Response<ExchangeRate>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /api/v{apiVersion}/rates/{fiatCurrency}/{cryptoCurrency}")
    Response<ExchangeRate> getExchangeRateByCurrencyPair(@Param("apiVersion") String apiVersion, @Param("fiatCurrency") String fiatCurrency, @Param("cryptoCurrency") String cryptoCurrency) throws UniPaymentException;

    /**
     * Create a default HTTP Client
     *
     * @return instance of {@link UniPaymentClient}
     */
    static UniPaymentClient getInstance() {
        return new Client<UniPaymentClient>().getDefaultClient(UniPaymentClient.class);
    }

    /**
     * Create a default HTTP Client
     *
     * @param configuration Configuration
     * @return instance of {@link UniPaymentClient}
     */
    static UniPaymentClient getInstance(Configuration configuration) {
        return new Client<UniPaymentClient>().getClient(UniPaymentClient.class, configuration);
    }

}
