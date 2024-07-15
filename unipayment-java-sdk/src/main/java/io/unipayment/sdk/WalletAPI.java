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
public interface WalletAPI {

    /**
     * Get Wallet Balances
     */
    @RequestLine("GET /wallet/balances")
    ApiResponse<List<WalletBalance>> getWalletBalances(@Param("accessToken") String accessToken) throws UnipaymentSdkException;

    /**
     * Get Wallet Accounts
     */
    @RequestLine("GET /wallet/accounts")
    ApiResponse<List<WalletAccount>> getWalletAccounts(@Param("accessToken") String accessToken) throws UnipaymentSdkException;

    /**
     * Query Transactions for Wallet Account's ID
     */
    @RequestLine("GET /wallet/accounts/{id}/transactions")
    ApiResponse<QueryResult<Transaction>> queryTransactions(@Param("accessToken") String accessToken, @Param("id") String accountId, @QueryMap QueryWalletTransactionsRequest queryWalletTransactionsRequest) throws UnipaymentSdkException;

    /**
     * Get Deposit Address
     */
    @RequestLine("GET /wallet/accounts/{id}/deposit/address")
    ApiResponse<List<WalletAccount>> getDepositAddress(@Param("accessToken") String accessToken, @Param("id") String accountId) throws UnipaymentSdkException;

    /**
     * Get Deposit Bank Account
     */
    @RequestLine("GET /wallet/accounts/{id}/deposit/bank-account")
    ApiResponse<List<WalletAccount>> getDepositBankAccount(@Param("accessToken") String accessToken, @Param("id") String accountId) throws UnipaymentSdkException;

    /**
     * Create Withdrawal
     */
    @RequestLine("POST /wallet/withdrawals")
    ApiResponse<Withdrawal> createWithdrawal(@Param("accessToken") String accessToken, CreateWithdrawalRequest request) throws UnipaymentSdkException;

    /**
     * Get Withdrawal By Id
     */
    @RequestLine("GET /wallet/withdrawals/{withdrawalId}")
    ApiResponse<Withdrawal> getWithdrawalById(@Param("accessToken") String accessToken, @Param("withdrawalId") String withdrawalId) throws UnipaymentSdkException;

    /**
     * Cancel Withdrawal
     */
    @RequestLine("POST /wallet/withdrawals")
    ApiResponse<Void> cancelWithdrawal(@Param("accessToken") String accessToken, CancelWithdrawalRequest request) throws UnipaymentSdkException;

    /**
     * Query Withdrawals
     */
    @RequestLine("GET /wallet/withdrawals")
    ApiResponse<QueryResult<Withdrawal>> queryWithdrawals(@Param("accessToken") String accessToken, @QueryMap QueryWithdrawalRequest request) throws UnipaymentSdkException;

    /**
     * Get Withdrawal Fees
     */
    @RequestLine("GET /wallet/withdrawal-fees/{assetType}")
    ApiResponse<List<WithdrawalFee>> getWithdrawalFees(@Param("accessToken") String accessToken, @Param("assetType") String assetType) throws UnipaymentSdkException;

    /**
     * Create Default Client
     *
     * @return Instance of Wallet API
     */
    static WalletAPI getInstance() {
        return new UnipaymentDefaultClient<WalletAPI>().getDefaultClient(WalletAPI.class);
    }

    /**
     * Create a client with a configuration
     *
     * @param configuration {@link Configuration}
     * @return Instance of Wallet API
     */
    static WalletAPI getInstance(Configuration configuration) {
        return new UnipaymentDefaultClient<WalletAPI>().getClient(WalletAPI.class, configuration);
    }
}
