package io.unipayment.client;

import feign.*;
import io.unipayment.client.models.Response;
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
    @RequestLine("POST /v{apiVersion}/invoices")
    Response<InvoiceModel> createInvoice(@Param("apiVersion") String apiVersion, CreateInvoiceRequest createInvoiceRequest) throws UniPaymentException;

    /**
     * Query Invoices
     *
     * @param apiVersion          Api Version
     * @param queryInvoiceRequest {@link QueryInvoiceRequest}
     * @return {@link Response<QueryResult<InvoiceModel>>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /v{apiVersion}/invoices")
    Response<QueryResult<InvoiceModel>> queryInvoices(@Param("apiVersion") String apiVersion, @QueryMap QueryInvoiceRequest queryInvoiceRequest) throws UniPaymentException;

    /**
     * Query Invoice By Id
     *
     * @param apiVersion Api Version
     * @param invoiceId  Invoice Id
     * @return {@link Response< InvoiceDetailModel >}
     * @throws UniPaymentException
     */
    @RequestLine("GET /v{apiVersion}/invoices/{invoiceId}")
    Response<InvoiceDetailModel> queryInvoiceById(@Param("apiVersion") String apiVersion, @Param("invoiceId") String invoiceId) throws UniPaymentException;

    /**
     * Query Ips
     *
     * @param apiVersion Api Version
     * @return {@link Response<List<String>>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /v{apiVersion}/ips")
    Response<List<String>> queryIps(@Param("apiVersion") String apiVersion) throws UniPaymentException;

    /**
     * Get Currencies
     *
     * @param apiVersion Api Version
     * @return {@link Response<List<Currency>>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /v{apiVersion}/currencies")
    Response<List<Currency>> getCurrencies(@Param("apiVersion") String apiVersion) throws UniPaymentException;

    /**
     * Get Exchange Rate By Fiat Currency
     *
     * @param apiVersion   Api Version
     * @param fiatCurrency Fiat Currency
     * @return {@link Response<List<ExchangeRate>>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /v{apiVersion}/rates/{fiatCurrency}")
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
    @RequestLine("GET /v{apiVersion}/rates/{fiatCurrency}/{cryptoCurrency}")
    Response<ExchangeRate> getExchangeRateByCurrencyPair(@Param("apiVersion") String apiVersion, @Param("fiatCurrency") String fiatCurrency, @Param("cryptoCurrency") String cryptoCurrency) throws UniPaymentException;


    /**
     * Check IPN notification
     *
     * @param apiVersion Api Version
     * @param notify     IPN notify
     * @return {@link Response<CheckIPNResponse>}
     * @throws UniPaymentException
     */
    @RequestLine("POST /v{apiVersion}/ipn")
    @Body("{body}")
    Response<CheckIPNResponse> checkIPN(@Param("apiVersion") String apiVersion, @Param("body") String notify) throws UniPaymentException;

    /**
     * Create Payout
     *
     * @param apiVersion Api Version
     * @param request    Create Payout Request
     * @return {@link Response<PayoutDetailModel>}
     * @throws UniPaymentException
     */
    @RequestLine("POST /v{apiVersion}/payouts")
    Response<PayoutDetailModel> createPayout(@Param("apiVersion") String apiVersion, CreatePayoutRequest request) throws UniPaymentException;

    /**
     * Get Payout By ID
     *
     * @param apiVersion Api Version
     * @param payoutId   Payout Id
     * @return {@link Response< PayoutDetailModel >}
     * @throws UniPaymentException
     */
    @RequestLine("GET /v{apiVersion}/payouts/{payoutId}")
    Response<PayoutDetailModel> getPayoutById(@Param("apiVersion") String apiVersion, @Param("payoutId") String payoutId) throws UniPaymentException;

    /**
     * Query Payouts
     *
     * @param apiVersion         Api Version
     * @param queryPayoutRequest {@link QueryPayoutRequest}
     * @return {@link Response<QueryResult<PayoutModel>>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /v{apiVersion}/payouts")
    Response<QueryResult<PayoutModel>> queryPayouts(@Param("apiVersion") String apiVersion, @QueryMap QueryPayoutRequest queryPayoutRequest) throws UniPaymentException;

    /**
     * Get Wallet Balances
     *
     * @param apiVersion Api Version
     * @return {@link Response<List<BalanceModel>>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /v{apiVersion}/wallet/balances")
    Response<List<BalanceModel>> getWalletBalances(@Param("apiVersion") String apiVersion) throws UniPaymentException;

    /**
     * Create Withdrawal
     *
     * @param apiVersion Api Version
     * @param request    {@link CreateWithdrawalRequest}
     * @return {@link Response<WithdrawalModel>}
     * @throws UniPaymentException
     */
    @RequestLine("POST /v{apiVersion}/wallet/withdrawals")
    Response<WithdrawalModel> createWithdrawal(@Param("apiVersion") String apiVersion, CreateWithdrawalRequest request) throws UniPaymentException;

    /**
     * Get Withdrawal Id
     *
     * @param apiVersion   Api Version
     * @param withdrawalId Withdrawal Id
     * @return {@link Response<WithdrawalModel>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /v{apiVersion}/wallet/withdrawals/{withdrawalId}")
    Response<WithdrawalModel> getWithdrawalById(@Param("apiVersion") String apiVersion, @Param("withdrawalId") String withdrawalId) throws UniPaymentException;

    /**
     * Cancel Withdrawal
     *
     * @param apiVersion Api Version
     * @param request    {@link CancelWithdrawalRequest}
     * @return {@link Response<Void>}
     * @throws UniPaymentException
     */
    @RequestLine("POST /v{apiVersion}/wallet/withdrawals")
    Response<Void> cancelWithdrawal(@Param("apiVersion") String apiVersion, CancelWithdrawalRequest request) throws UniPaymentException;

    /**
     * Query Withdrawals
     *
     * @param apiVersion Api Version
     * @param request    {@link QueryWithdrawalRequest}
     * @return {@link Response<QueryResult<WithdrawalModel>>}
     * @throws UniPaymentException
     */
    @RequestLine("GET /v{apiVersion}/wallet/withdrawals")
    Response<QueryResult<WithdrawalModel>> queryWithdrawals(@Param("apiVersion") String apiVersion, @QueryMap QueryWithdrawalRequest request) throws UniPaymentException;

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
