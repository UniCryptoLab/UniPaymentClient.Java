package io.unipayment.sdk;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.unipayment.sdk.core.config.Configuration;
import io.unipayment.sdk.exception.UnipaymentSdkException;
import io.unipayment.sdk.model.*;

@Headers({"Content-Type: application/json", "Accept: application/json", "Authorization: Bearer {accessToken}"})
public interface BillingAPI {

    /**
     * Create Invoice
     */
    @RequestLine("POST /invoices")
    ApiResponse<Invoice> createInvoice(CreateInvoiceRequest createInvoiceRequest) throws UnipaymentSdkException;

    /**
     * Query Invoices
     */
    @RequestLine("GET /invoices")
    ApiResponse<QueryResult<Invoice>> queryInvoices(@QueryMap QueryInvoiceRequest queryInvoiceRequest) throws UnipaymentSdkException;

    /**
     * Query Invoice By Id
     */
    @RequestLine("GET /invoices/{invoiceId}")
    ApiResponse<InvoiceDetail> queryInvoiceById(@Param("invoiceId") String invoiceId) throws UnipaymentSdkException;

    /**
     * Create Default Client
     *
     * @return Instance of Billing API
     */
    static BillingAPI getInstance() {
        return new UnipaymentDefaultClient<BillingAPI>().getDefaultClient(BillingAPI.class);
    }

    /**
     * Create a client with a configuration
     *
     * @param configuration {@link Configuration}
     * @return Instance of Billing API
     */
    static BillingAPI getInstance(Configuration configuration) {
        return new UnipaymentDefaultClient<BillingAPI>().getClient(BillingAPI.class, configuration);
    }
}
