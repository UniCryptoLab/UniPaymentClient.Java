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

    @Before
    public void init() {
        configuration = new Configuration();
        configuration.setAppId("cee1b9e2-d90c-4b63-9824-d621edb38012");
        configuration.setApiKey("9G62Fd7fCQGyznVvatk4SAfGsHDEt819E");
        configuration.setBaseUrl("https://sandbox.unipayment.io");
        configuration.setDebug(true);
        configuration.setApiVersion("1.0");

        uniPaymentClient = UniPaymentClient.getInstance(configuration);
    }

    @Test
    public void testCreateInvoice() throws UniPaymentException {
        CreateInvoiceRequest createInvoiceRequest = CreateInvoiceRequest.builder()
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
        Response<TransactionList> response = uniPaymentClient.queryInvoiceById(configuration.getApiVersion(), "9EfHVGLDjQssJv7xnBsDSM");
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
}