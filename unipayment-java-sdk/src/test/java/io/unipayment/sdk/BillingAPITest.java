package io.unipayment.sdk;

import io.unipayment.sdk.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillingAPITest extends BaseAPITest {

    private static BillingAPI billingAPI;
    private static String invoiceId;
    private static String orderId;

    @BeforeAll
    public static void setUp() {
        billingAPI = BillingAPI.getInstance(configuration);
        orderId = "ORDER_" + System.currentTimeMillis();
    }

    @Test
    @Order(1)
    public void testCreateInvoice() {
        CreateInvoiceRequest createInvoiceRequest = CreateInvoiceRequest.builder()
                .appId(configuration.getAppId())
                .priceAmount(2.0)
                .priceCurrency("USD")
                .orderId(orderId)
                .lang("en")
                .extArgs("Merchant Pass Through Data")
                .build();
        ApiResponse<Invoice> apiResponse = billingAPI.createInvoice(createInvoiceRequest);
        assertEquals(apiResponse.getCode(), "OK");
        invoiceId = apiResponse.getData().getInvoiceId();
    }

    @Test
    @Order(2)
    public void testQueryInvoicesByOrderId() {
        QueryInvoiceRequest queryInvoiceRequest = new QueryInvoiceRequest();
        queryInvoiceRequest.setOrderId(orderId);
        ApiResponse<QueryResult<Invoice>> apiResponse = billingAPI.queryInvoices(queryInvoiceRequest);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(3)
    public void testQueryInvoiceById() {
        ApiResponse<InvoiceDetail> apiResponse = billingAPI.queryInvoiceById(invoiceId);
        assertEquals(apiResponse.getCode(), "OK");
    }
}