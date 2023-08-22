package io.unipayment.client;

import io.unipayment.client.models.*;
import io.unipayment.client.models.enums.ConfirmSpeed;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Collections;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UniPaymentClientTest {
    private UniPaymentClient uniPaymentClient;
    private Configuration configuration;
    private String clientId = "74feb539-ba5a-4ae9-b901-4da4fb539574";
    private String clientSecret = "BsoRhgqzhR1TYMtwTRYdPxBTvR5rxkW9K";
    private String appId = "2a9bd90b-fe95-4659-83cb-04de662fbbac";
    private String invoiceId = "SrAARgNrPgvveiBQtNc4gk";
    private String payoutId = "SrAARgNrPgvveiBQtNc4gk";
    private String withdrawalId = "a6389658-ac47-42f7-b71e-4bd1dc51ee2d";

    @Before
    public void init() {
        configuration = new Configuration();
        configuration.setClientId(this.clientId);
        configuration.setClientSecret(this.clientSecret);
        configuration.setBaseUrl("https://sandbox-api.unipayment.io");
        configuration.setDebug(true);

        uniPaymentClient = UniPaymentClient.getInstance(configuration);
    }

    @Test
    public void InvoiceAPI_test1_CreateInvoice() throws UniPaymentException {
        CreateInvoiceRequest createInvoiceRequest = CreateInvoiceRequest.builder()
                .appId(this.appId)
                .priceAmount(2.0)
                .priceCurrency("USD")
                .payCurrency("USDT")
                .orderId("ORDER_123456")
                .confirmSpeed(ConfirmSpeed.Medium)
                .redirectURL("https://www.example.com")
                .notifyURL("https://demo-payment.requestcatcher.com/test")
                .title("MacBook Pro")
                .description("MacBook Pro(256G)")
                .lang("en")
                .extArgs("Merchant Pass Through Data")
                .build();

        Response<InvoiceModel> response = uniPaymentClient.createInvoice(configuration.getApiVersion(), createInvoiceRequest);
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void InvoiceAPI_test2_QueryInvoices() throws UniPaymentException {
        QueryInvoiceRequest queryInvoiceRequest = new QueryInvoiceRequest();
        queryInvoiceRequest.setOrderId("ORDER_123456");
        Response<QueryResult<InvoiceModel>> response = uniPaymentClient.queryInvoices(configuration.getApiVersion(), queryInvoiceRequest);
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void InvoiceAPI_test3_QueryInvoiceById() throws UniPaymentException {
        Response<InvoiceDetailModel> response = uniPaymentClient.queryInvoiceById(configuration.getApiVersion(), this.invoiceId);
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void testQueryIps() throws UniPaymentException {
        Response<List<String>> response = uniPaymentClient.queryIps(configuration.getApiVersion());
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void testGetCurrencies() throws UniPaymentException {
        Response<List<Currency>> response = uniPaymentClient.getCurrencies(configuration.getApiVersion());
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void RatesAPI_test1_GetExchangeRateByFiatCurrency() throws UniPaymentException {
        Response<List<ExchangeRate>> response = uniPaymentClient.getExchangeRateByFiatCurrency(configuration.getApiVersion(), "USD");
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void RatesAPI_test2_GetExchangeRateByCurrencyPair() throws UniPaymentException {
        Response<ExchangeRate> response = uniPaymentClient.getExchangeRateByCurrencyPair(configuration.getApiVersion(), "USD", "BTC");
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void testCheckIPN() throws UniPaymentException {
        String notify = "{\"ipn_type\":\"invoice\",\"event\":\"invoice_created\",\"app_id\":\"cee1b9e2-d90c-4b63-9824-d621edb38012\",\"invoice_id\":\"12wQquUmeCPUx3qmp3aHnd\",\"order_id\":\"ORDER_123456\",\"price_amount\":2.0,\"price_currency\":\"USD\",\"network\":null,\"address\":null,\"pay_currency\":null,\"pay_amount\":0.0,\"exchange_rate\":0.0,\"paid_amount\":0.0,\"confirmed_amount\":0.0,\"refunded_price_amount\":0.0,\"create_time\":\"2022-09-14T04:57:54.5599307Z\",\"expiration_time\":\"2022-09-14T05:02:54.559933Z\",\"status\":\"New\",\"error_status\":\"None\",\"ext_args\":\"Merchant Pass Through Data\",\"transactions\":null,\"notify_id\":\"fd58cedd-67c6-4053-ae65-2f6fb09a7d2c\",\"notify_time\":\"0001-01-01T00:00:00\"}";
        Response<CheckIPNResponse> response = uniPaymentClient.checkIPN(configuration.getApiVersion(), notify);
        if (response.getCode().equals("OK")) {
            // handle business logic
            //return "SUCCESS";
        }
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void PayoutAPI_test1_CreatePayout() throws UniPaymentException {
        CreatePayoutRequest createPayoutRequest = CreatePayoutRequest.builder()
                .assetType("USDC")
                .network("")
                .items(Collections.singletonList(PayoutRequestItem.builder().address("USDT").amount(0.01).build()))
                .build();
        Response<PayoutDetailModel> response = uniPaymentClient.createPayout(configuration.getApiVersion(), createPayoutRequest);
        if (response.getCode().equals("OK")) {
            // handle business logic
            //return "SUCCESS";
        }
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void PayoutAPI_test2_QueryPayouts() throws UniPaymentException {
        QueryPayoutRequest queryPayoutRequest = new QueryPayoutRequest();
        Response<QueryResult<PayoutModel>> response = uniPaymentClient.queryPayouts(configuration.getApiVersion(), queryPayoutRequest);
        if (response.getCode().equals("OK")) {
            // handle business logic
            //return "SUCCESS";
        }
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void PayoutAPI_test2_GetPayoutById() throws UniPaymentException {
        Response<PayoutDetailModel> response = uniPaymentClient.getPayoutById(configuration.getApiVersion(), payoutId);
        if (response.getCode().equals("OK")) {
            // handle business logic
            //return "SUCCESS";
        }
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void WalletAPI_test1_GetWalletBalances() throws UniPaymentException {
        Response<List<BalanceModel>> response = uniPaymentClient.getWalletBalances(configuration.getApiVersion());
        if (response.getCode().equals("OK")) {
            // handle business logic
            //return "SUCCESS";
        }
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void WalletAPI_test2_CreateWithdrawal() throws UniPaymentException {
        CreateWithdrawalRequest createWithdrawalRequest = CreateWithdrawalRequest.builder()
                .amount(0.01)
                .assetType("BNB")
                .includeFee(true)
                .network("NETWORK_BSC")
                .build();
        Response<WithdrawalModel> response = uniPaymentClient.createWithdrawal(configuration.getApiVersion(), createWithdrawalRequest);
        if (response.getCode().equals("OK")) {
            // handle business logic
            //return "SUCCESS";
        }
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void WalletAPI_test3_GetWithdrawalById() throws UniPaymentException {
        Response<WithdrawalModel> response = uniPaymentClient.getWithdrawalById(configuration.getApiVersion(), withdrawalId);
        if (response.getCode().equals("OK")) {
            // handle business logic
            //return "SUCCESS";
        }
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void WalletAPI_test4_QueryWithdrawals() throws UniPaymentException {
        QueryWithdrawalRequest queryWithdrawalRequest = new QueryWithdrawalRequest();
        Response<QueryResult<WithdrawalModel>> response = uniPaymentClient.queryWithdrawals(configuration.getApiVersion(), queryWithdrawalRequest);
        if (response.getCode().equals("OK")) {
            // handle business logic
            //return "SUCCESS";
        }
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void WalletAPI_test5_CancelWithdrawal() throws UniPaymentException {
        CancelWithdrawalRequest cancelWithdrawalRequest = CancelWithdrawalRequest.builder()
                .id(withdrawalId)
                .build();
        Response<Void> response = uniPaymentClient.cancelWithdrawal(configuration.getApiVersion(), cancelWithdrawalRequest);
        if (response.getCode().equals("OK")) {
            // handle business logic
            //return "SUCCESS";
        }
        Assert.assertEquals(response.getCode(), "OK");
    }
}