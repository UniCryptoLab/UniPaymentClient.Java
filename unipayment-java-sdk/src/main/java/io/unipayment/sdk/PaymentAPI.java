package io.unipayment.sdk;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.unipayment.sdk.core.config.Configuration;
import io.unipayment.sdk.exception.UnipaymentSdkException;
import io.unipayment.sdk.model.*;

import java.util.List;

@Headers({"Content-Type: application/json", "Accept: application/json", "Authorization: Bearer {accessToken}"})
public interface PaymentAPI {

    /**
     * Create Payment
     */
    @RequestLine("POST /payments")
    ApiResponse<Payment> createPayment(@Param("accessToken") String accessToken, CreatePaymentRequest createPaymentRequest) throws UnipaymentSdkException;

    /**
     * Get Payment By ID
     */
    @RequestLine("GET /payments/{id}")
    ApiResponse<Payment> getPaymentById(@Param("accessToken") String accessToken, @Param("id") String paymentId) throws UnipaymentSdkException;

    /**
     * Query Payment
     */
    @RequestLine("GET /payments")
    ApiResponse<QueryResult<Payment>> queryPayments(@Param("accessToken") String accessToken, @QueryMap QueryPaymentRequest queryPaymentRequest) throws UnipaymentSdkException;

    /**
     * Query Payment
     */
    @RequestLine("PUT /payments/{id}/cancel")
    ApiResponse<Void> cancelPayment(@Param("accessToken") String accessToken, @Param("id") String paymentId, PaymentNote paymentNote) throws UnipaymentSdkException;

    /**
     * Query Payment
     */
    @RequestLine("PUT /payments/{id}/confirm")
    ApiResponse<Void> confirmPayment(@Param("accessToken") String accessToken, @Param("id") String paymentId, PaymentNote paymentNote) throws UnipaymentSdkException;

    /**
     * Get Payment Fee
     */
    @RequestLine("GET /payments/fee")
    ApiResponse<List<PaymentFee>> getPaymentFee(@Param("accessToken") String accessToken, @QueryMap QueryPaymentFeeRequest queryPaymentFeeRequest) throws UnipaymentSdkException;

    /**
     * Create Default Client
     *
     * @return Instance of Deposit And Payment API
     */
    static PaymentAPI getInstance() {
        return new UnipaymentDefaultClient<PaymentAPI>().getDefaultClient(PaymentAPI.class);
    }

    /**
     * Create a client with a configuration
     *
     * @param configuration {@link Configuration}
     * @return Instance of Deposit And Payment API
     */
    static PaymentAPI getInstance(Configuration configuration) {
        return new UnipaymentDefaultClient<PaymentAPI>().getClient(PaymentAPI.class, configuration);
    }
}
