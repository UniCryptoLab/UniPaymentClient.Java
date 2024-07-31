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
public interface BeneficiaryAPI {

    /**
     * Create a new beneficiary.
     */
    @RequestLine("POST /beneficiaries")
    ApiResponse<Beneficiary> createBeneficiary(@Param("accessToken") String accessToken, Beneficiary beneficiary) throws UnipaymentSdkException;

    /**
     * Get a paged list of beneficiaries based on the provided search criteria.
     */
    @RequestLine("GET /beneficiaries")
    ApiResponse<QueryResult<Beneficiary>> queryBeneficiaries(@Param("accessToken") String accessToken, @QueryMap QueryBeneficiaryRequest queryBeneficiaryRequest) throws UnipaymentSdkException;

    /**
     * Get a specific beneficiary by its ID.
     */
    @RequestLine("GET /beneficiaries/{id}")
    ApiResponse<Beneficiary> getBeneficiaryById(@Param("accessToken") String accessToken, @Param("id") String beneficiaryId) throws UnipaymentSdkException;

    /**
     * Updates the details of a specific beneficiary.
     */
    @RequestLine("PUT /beneficiaries/{id}")
    ApiResponse<Beneficiary> updateBeneficiary(@Param("accessToken") String accessToken, @Param("id") String beneficiaryId, Beneficiary beneficiary) throws UnipaymentSdkException;

    /**
     * Deletes a specific beneficiary by its Id.
     */
    @RequestLine("DELETE /beneficiaries/{id}")
    ApiResponse<Void> deleteBeneficiaryById(@Param("accessToken") String accessToken, @Param("id") String beneficiaryId) throws UnipaymentSdkException;

    /**
     * Create a payment method for the beneficiary, the customer initiates the payment instruction directly by referencing the payment method id.
     */
    @RequestLine("POST /beneficiaries/{id}/payment-methods")
    ApiResponse<PaymentMethod> createPaymentMethod(@Param("accessToken") String accessToken, @Param("id") String beneficiaryId, PaymentMethod paymentMethod) throws UnipaymentSdkException;

    /**
     * Get a list of payment methods for a specific beneficiary.
     */
    @RequestLine("GET /beneficiaries/{id}/payment-methods")
    ApiResponse<List<PaymentMethod>> getPaymentMethodList(@Param("accessToken") String accessToken, @Param("id") String beneficiaryId) throws UnipaymentSdkException;

    /**
     * Get a specific payment method for a specific beneficiary.
     */
    @RequestLine("GET /beneficiaries/{id}/payment-methods/{payment-method-id}")
    ApiResponse<PaymentMethod> getPaymentMethod(@Param("accessToken") String accessToken, @Param("id") String beneficiaryId, @Param("payment-method-id") String paymentMethodId) throws UnipaymentSdkException;

    /**
     * Updates a specific payment method for a specific beneficiary.
     */
    @RequestLine("PUT /beneficiaries/{id}/payment-methods/{payment-method-id}")
    ApiResponse<PaymentMethod> updatePaymentMethod(@Param("accessToken") String accessToken, @Param("id") String beneficiaryId, @Param("payment-method-id") String paymentMethodId, PaymentMethod paymentMethod) throws UnipaymentSdkException;

    /**
     * Delete a specific payment method for a specific beneficiary.
     */
    @RequestLine("DELETE /beneficiaries/{id}/payment-methods/{payment-method-id}")
    ApiResponse<Void> deletePaymentMethod(@Param("accessToken") String accessToken, @Param("id") String beneficiaryId, @Param("payment-method-id") String paymentMethodId) throws UnipaymentSdkException;


    /**
     * Create Default Client
     *
     * @return Instance of Beneficiaries API
     */
    static BeneficiaryAPI getInstance() {
        return new UnipaymentDefaultClient<BeneficiaryAPI>().getDefaultClient(BeneficiaryAPI.class);
    }

    /**
     * Create a client with a configuration
     *
     * @param configuration {@link Configuration}
     * @return Instance of Beneficiaries API
     */
    static BeneficiaryAPI getInstance(Configuration configuration) {
        return new UnipaymentDefaultClient<BeneficiaryAPI>().getClient(BeneficiaryAPI.class, configuration);
    }
}
