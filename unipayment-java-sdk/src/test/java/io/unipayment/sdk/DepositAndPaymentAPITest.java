package io.unipayment.sdk;

import io.unipayment.sdk.model.*;
import io.unipayment.sdk.model.enums.PaymentReason;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DepositAndPaymentAPITest extends BaseAPITest {

    private static DepositAndPaymentAPI depositAndPaymentAPI;
    private static String paymentId;

    @BeforeAll
    public static void setUp() {
        depositAndPaymentAPI = DepositAndPaymentAPI.getInstance(configuration);
    }

    @Test
    @Order(1)
    public void testGetPaymentFee() {
        QueryPaymentFeeRequest queryPaymentFeeRequest = new QueryPaymentFeeRequest();
        queryPaymentFeeRequest.setAssetType("USDT");
        ApiResponse<List<PaymentFee>> apiResponse = depositAndPaymentAPI.getPaymentFee(getAccessToken(), queryPaymentFeeRequest);
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
        ApiResponse<QueryResult<Payment>> apiResponse = depositAndPaymentAPI.queryPayments(getAccessToken(), queryPaymentFeeRequest);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(4)
    public void testGetPaymentById() {
        ApiResponse<Payment> apiResponse = depositAndPaymentAPI.getPaymentById(getAccessToken(), paymentId);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(5)
    public void testConfirmPayment() {
        PaymentNote paymentNote = PaymentNote.builder().note("Payment Confirmed").build();
        ApiResponse<Void> apiResponse = depositAndPaymentAPI.cancelPayment(getAccessToken(), paymentId, paymentNote);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(6)
    public void testCancelPayment() {
        createPayment();
        PaymentNote paymentNote = PaymentNote.builder().note("Payment Cancelled").build();
        ApiResponse<Void> apiResponse = depositAndPaymentAPI.cancelPayment(getAccessToken(), paymentId, paymentNote);
        assertEquals(apiResponse.getCode(), "OK");
    }

    private void createPayment() {
        CreatePaymentRequest createPaymentRequest = CreatePaymentRequest.builder()
                .fromAccountId("2c7b5891-637a-49af-a0bc-00f41815269c")
                .toAccountId("bc8263b8-c3ca-48e0-b9ed-f1403c5e8575")
                .assetType("BTC")
                .paymentMethodId("cff91813-b4f0-4c6d-8f16-4094ad9f920e")
                .amount(0.001)
                .reason(PaymentReason.InternalTransfer)
                .uniqueId(UUID.randomUUID().toString())
                .build();
        ApiResponse<Payment> apiResponse = depositAndPaymentAPI.createPayment(getAccessToken(), createPaymentRequest);
        assertEquals(apiResponse.getCode(), "OK");
        paymentId = apiResponse.getData().getId();
    }
}