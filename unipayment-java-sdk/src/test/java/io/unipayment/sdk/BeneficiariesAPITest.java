package io.unipayment.sdk;

import io.unipayment.sdk.model.*;
import io.unipayment.sdk.model.enums.BeneficiaryType;
import io.unipayment.sdk.model.enums.Relationship;
import io.unipayment.sdk.model.enums.TransferMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BeneficiariesAPITest extends BaseAPITest {

    private static BeneficiariesAPI beneficiariesAPI;
    private static String beneficiaryId;
    private static String paymentMethodId;

    @BeforeAll
    public static void setUp() {
        beneficiariesAPI = BeneficiariesAPI.getInstance(configuration);
    }

    @Test
    @Order(1)
    public void testCreateBeneficiary() {
        Beneficiary beneficiary = Beneficiary.builder()
                .name("Beneficiary 1")
                .email("ben1@test.com")
                .type(BeneficiaryType.INDIVIDUAL)
                .relationship(Relationship.CUSTOMER)
                .build();
        ApiResponse<Beneficiary> apiResponse = beneficiariesAPI.createBeneficiary(getAccessToken(), beneficiary);
        assertEquals(apiResponse.getCode(), "OK");
        beneficiaryId = apiResponse.getData().getId();
    }

    @Test
    @Order(2)
    public void testQueryBeneficiaries() {
        ApiResponse<QueryResult<Beneficiary>> apiResponse = beneficiariesAPI.queryBeneficiaries(getAccessToken(), new QueryBeneficiaryRequest());
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(3)
    public void testGetBeneficiaryById() {
        ApiResponse<Beneficiary> apiResponse = beneficiariesAPI.getBeneficiaryById(getAccessToken(), beneficiaryId);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(4)
    public void testUpdateBeneficiary() {
        ApiResponse<Beneficiary> apiResponse = beneficiariesAPI.getBeneficiaryById(getAccessToken(), beneficiaryId);
        Beneficiary oldBeneficiary = apiResponse.getData();
        Beneficiary updatedBeneficiary = oldBeneficiary;
        updatedBeneficiary.setAddress("123 Street");
        updatedBeneficiary.setCity("NYC");
        updatedBeneficiary.setCountry("US");
        updatedBeneficiary.setState("NY");
        updatedBeneficiary.setZipcode("12345");

        apiResponse = beneficiariesAPI.updateBeneficiary(getAccessToken(), beneficiaryId, updatedBeneficiary);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(5)
    public void testCreatePaymentMethod_Crypto() {
        CryptoPaymentMethodDetail cryptoPaymentMethodDetail = new CryptoPaymentMethodDetail();
        cryptoPaymentMethodDetail.setAssetType("BTC");
        cryptoPaymentMethodDetail.setAddress("bc1qxy2kgdygjrsqtzq2n0yrf2493p83kkfjhx0wlh");
        cryptoPaymentMethodDetail.setNetwork("NETWORK_BTC");
        PaymentMethod paymentMethod = PaymentMethod.builder()
                .title("CRYPTO " + UUID.randomUUID())
                .transferMethod(TransferMethod.CRYPTO)
                .detail(cryptoPaymentMethodDetail)
                .build();
        ApiResponse<PaymentMethod> apiResponse = beneficiariesAPI.createPaymentMethod(getAccessToken(), beneficiaryId, paymentMethod);
        assertEquals(apiResponse.getCode(), "OK");
        paymentMethodId = apiResponse.getData().getId();
    }

    @Test
    @Order(6)
    public void testCreatePaymentMethod_Internal() {
        InternalPaymentMethodDetail internalPaymentMethodDetail = new InternalPaymentMethodDetail();
        internalPaymentMethodDetail.setUid("1000000");
        internalPaymentMethodDetail.setAssetType("USDT");
        PaymentMethod paymentMethod = PaymentMethod.builder()
                .title("INTERNAL " + UUID.randomUUID())
                .transferMethod(TransferMethod.INTERNAL)
                .detail(internalPaymentMethodDetail)
                .build();
        ApiResponse<PaymentMethod> apiResponse = beneficiariesAPI.createPaymentMethod(getAccessToken(), beneficiaryId, paymentMethod);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(7)
    public void testCreatePaymentMethod_Bank() {
        BankPaymentMethodDetail bankPaymentMethodDetail = new BankPaymentMethodDetail();
        bankPaymentMethodDetail.setAssetType("USD");
        bankPaymentMethodDetail.setAccountNumber("1234567890");
        bankPaymentMethodDetail.setBankIdentifier("CITIUS33XXX");
        bankPaymentMethodDetail.setBankAddress("388 GREENWICH STREET NYC NY");
        bankPaymentMethodDetail.setBankName("CITIBANK");
        bankPaymentMethodDetail.setBankCountry("US");
        bankPaymentMethodDetail.setBic("CITIUS33XXX");
        bankPaymentMethodDetail.setNetwork("BANK_SWIFT");
        PaymentMethod paymentMethod = PaymentMethod.builder()
                .title("BANK ACC " + UUID.randomUUID())
                .transferMethod(TransferMethod.BANK)
                .detail(bankPaymentMethodDetail)
                .build();
        ApiResponse<PaymentMethod> apiResponse = beneficiariesAPI.createPaymentMethod(getAccessToken(), beneficiaryId, paymentMethod);
        assertEquals(apiResponse.getCode(), "OK");
    }


    @Test
    @Order(8)
    public void testGetPaymentMethodList() {
        ApiResponse<List<PaymentMethod>> apiResponse = beneficiariesAPI.getPaymentMethodList(getAccessToken(), beneficiaryId);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(9)
    public void testGetPaymentById() {
        ApiResponse<PaymentMethod> apiResponse = beneficiariesAPI.getPaymentMethod(getAccessToken(), beneficiaryId, paymentMethodId);
        assertEquals(apiResponse.getCode(), "OK");
        assertTrue(apiResponse.getData().getDetail() instanceof CryptoPaymentMethodDetail);
    }

    @Test
    @Order(10)
    public void testDeletePaymentById() {
        ApiResponse<Void> apiResponse = beneficiariesAPI.deletePaymentMethod(getAccessToken(), beneficiaryId, paymentMethodId);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    @Order(11)
    public void testDeleteBeneficiaryById() {
        ApiResponse<Void> apiResponse = beneficiariesAPI.deleteBeneficiaryById(getAccessToken(), beneficiaryId);
        assertEquals(apiResponse.getCode(), "OK");
    }
}