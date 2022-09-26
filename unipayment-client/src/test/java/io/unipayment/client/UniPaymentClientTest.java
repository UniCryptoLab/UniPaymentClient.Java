package io.unipayment.client;

import io.unipayment.client.models.*;
import io.unipayment.client.models.enums.ConfirmSpeed;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UniPaymentClientTest {

    private UniPaymentClient uniPaymentClient;
    private Configuration configuration;

    private String clientId="74feb539-ba5a-4ae9-b901-4da4fb539574";
    private String clientSecret="BsoRhgqzhR1TYMtwTRYdPxBTvR5rxkW9K";
    private String appId="2a9bd90b-fe95-4659-83cb-04de662fbbac";
    private String invoiceId="SrAARgNrPgvveiBQtNc4gk";



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
    public void testCreateInvoice() throws UniPaymentException {
        CreateInvoiceRequest createInvoiceRequest = CreateInvoiceRequest.builder()
                .appId(this.appId)
                .priceAmount(100d)
                .priceCurrency("USD")
                .payCurrency("USDT")
                .orderId("ORDER_123456")
                .confirmSpeed(ConfirmSpeed.low)
                .redirectURL("https://google.com")
                .notifyURL("https://google.com")
                .title("Test Invoice")
                .description("Test Desc")
                .lang("en-US")
                .build();
        Response<InvoiceModel> response = uniPaymentClient.createInvoice(configuration.getApiVersion(), createInvoiceRequest);
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void testQueryInvoices() throws UniPaymentException {
        QueryInvoiceRequest queryInvoiceRequest = new QueryInvoiceRequest();
        queryInvoiceRequest.setOrderId("ORDER_123456");
        Response<QueryResult<InvoiceModel>> response = uniPaymentClient.queryInvoices(configuration.getApiVersion(), queryInvoiceRequest);
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void testQueryInvoiceById() throws UniPaymentException {
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
    public void testGetExchangeRateByFiatCurrency() throws UniPaymentException {
        Response<List<ExchangeRate>> response = uniPaymentClient.getExchangeRateByFiatCurrency(configuration.getApiVersion(), "USD");
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void testGetExchangeRateByCurrencyPair() throws UniPaymentException {
        Response<ExchangeRate> response = uniPaymentClient.getExchangeRateByCurrencyPair(configuration.getApiVersion(), "USD", "BTC");
        Assert.assertEquals(response.getCode(), "OK");
    }

    @Test
    public void testCheckIPN() throws UniPaymentException {
        String notify = "{\"ipn_type\":\"invoice\",\"event\":\"invoice_created\",\"app_id\":\"cee1b9e2-d90c-4b63-9824-d621edb38012\",\"invoice_id\":\"12wQquUmeCPUx3qmp3aHnd\",\"order_id\":\"ORDER_123456\",\"price_amount\":2.0,\"price_currency\":\"USD\",\"network\":null,\"address\":null,\"pay_currency\":null,\"pay_amount\":0.0,\"exchange_rate\":0.0,\"paid_amount\":0.0,\"confirmed_amount\":0.0,\"refunded_price_amount\":0.0,\"create_time\":\"2022-09-14T04:57:54.5599307Z\",\"expiration_time\":\"2022-09-14T05:02:54.559933Z\",\"status\":\"New\",\"error_status\":\"None\",\"ext_args\":\"Merchant Pass Through Data\",\"transactions\":null,\"notify_id\":\"fd58cedd-67c6-4053-ae65-2f6fb09a7d2c\",\"notify_time\":\"0001-01-01T00:00:00\"}";
        Response<CheckIPNResponse> response = uniPaymentClient.checkIPN(configuration.getApiVersion(), notify);
        if(response.getCode().equals("OK"))
        {
            // handle business logic
            //return "SUCCESS";
        }
        Assert.assertEquals(response.getCode(), "OK");
    }
}