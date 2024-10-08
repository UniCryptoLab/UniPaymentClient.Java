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
                .priceAmount(20.0)
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

    @Test
    @Order(4)
    public void testCreateInvoice_HostToHost() {
        CreateInvoiceRequest createInvoiceRequest = CreateInvoiceRequest.builder()
                .appId(configuration.getAppId())
                .priceAmount(20.0)
                .priceCurrency("USD")
                .orderId(orderId)
                .lang("en")
                .extArgs("Merchant Pass Through Data")
                .paymentMethodType("CRYPTO")
                .hostToHostMode(true)
                .payCurrency("BNB")
                .payNetwork("NETWORK_BSC")
                .build();
        ApiResponse<Invoice> apiResponse = billingAPI.createInvoice(createInvoiceRequest);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(4)
    public void testCreateInvoice_BuyerInfo() {
        CreateInvoiceRequest createInvoiceRequest = CreateInvoiceRequest.builder()
                .appId(configuration.getAppId())
                .priceAmount(20.0)
                .priceCurrency("USD")
                .orderId(orderId)
                .lang("en")
                .extArgs("Merchant Pass Through Data")
                .buyerInfo(BuyerInfo.builder()
                        .name("John Doe")
                        .email("john.doe@example.com")
                        .address1("Address 1")
                        .address2("Address 2")
                        .city("NYC")
                        .state("NY")
                        .zipCode("12345")
                        .country("USA")
                        .build())
                .build();
        ApiResponse<Invoice> apiResponse = billingAPI.createInvoice(createInvoiceRequest);
        assertEquals(apiResponse.getCode(), "OK");
        invoiceId = apiResponse.getData().getInvoiceId();
    }
}