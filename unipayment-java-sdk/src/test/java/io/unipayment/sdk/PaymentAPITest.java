package io.unipayment.sdk;

import io.unipayment.sdk.model.*;
import io.unipayment.sdk.model.enums.PaymentReason;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentAPITest extends BaseAPITest {

    private static PaymentAPI paymentAPI;
    private static String paymentId;

    @BeforeAll
    public static void setUp() {
        paymentAPI = PaymentAPI.getInstance(configuration);
    }

    @Test
    @Order(1)
    public void testGetPaymentFee() {
        QueryPaymentFeeRequest queryPaymentFeeRequest = new QueryPaymentFeeRequest();
        queryPaymentFeeRequest.setAssetType("USDT");
        ApiResponse<List<PaymentFee>> apiResponse = paymentAPI.getPaymentFee(getAccessToken(), queryPaymentFeeRequest);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(2)
    public void testCreatePayment() {
        createPayment();
    }

    @Test
    @Order(3)
    public void testQueryPayments() {
        QueryPaymentRequest queryPaymentFeeRequest = new QueryPaymentRequest();
        ApiResponse<QueryResult<Payment>> apiResponse = paymentAPI.queryPayments(getAccessToken(), queryPaymentFeeRequest);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(4)
    public void testGetPaymentById() {
        ApiResponse<Payment> apiResponse = paymentAPI.getPaymentById(getAccessToken(), paymentId);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(5)
    public void testConfirmPayment() {
        PaymentNote paymentNote = PaymentNote.builder().note("Payment Confirmed").build();
        ApiResponse<Void> apiResponse = paymentAPI.cancelPayment(getAccessToken(), paymentId, paymentNote);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(6)
    public void testCancelPayment() {
        createPayment();
        PaymentNote paymentNote = PaymentNote.builder().note("Payment Cancelled").build();
        ApiResponse<Void> apiResponse = paymentAPI.cancelPayment(getAccessToken(), paymentId, paymentNote);
        assertEquals(apiResponse.getCode(), "OK");
    }

    private void createPayment() {
        CreatePaymentRequest createPaymentRequest = CreatePaymentRequest.builder()
                .fromAccountId("d7c5db2e-8572-4a2f-9300-84dc4b3fd052")
                .toAccountId("f0b4083b-8b43-4267-a321-f96bdba8c9e4")
                .assetType("USDT")
                .paymentMethodId("5c0bce95-7d10-47f3-8e11-250ab900da07")
                .amount(10.000)
                .reason(PaymentReason.InternalTransfer)
                .uniqueId(UUID.randomUUID().toString())
                .build();
        ApiResponse<Payment> apiResponse = paymentAPI.createPayment(getAccessToken(), createPaymentRequest);
        assertEquals(apiResponse.getCode(), "OK");
        paymentId = apiResponse.getData().getId();
    }
}