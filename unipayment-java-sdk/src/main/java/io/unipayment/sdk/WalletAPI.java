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
     * Get Balances
     */
    @RequestLine("GET /wallet/balances")
    ApiResponse<List<WalletBalance>> getBalances() throws UnipaymentSdkException;

    /**
     * Get Accounts
     */
    @RequestLine("GET /wallet/accounts")
    ApiResponse<List<WalletAccount>> getAccounts() throws UnipaymentSdkException;

    /**
     * Query Transactions for Wallet Account's ID
     */
    @RequestLine("GET /wallet/accounts/{id}/transactions")
    ApiResponse<QueryResult<Transaction>> queryTransactions(@Param("id") String accountId, @QueryMap QueryWalletTransactionsRequest queryWalletTransactionsRequest) throws UnipaymentSdkException;

    /**
     * Get Deposit Address
     */
    @RequestLine("GET /wallet/accounts/{id}/deposit/address")
    ApiResponse<List<DepositAddress>> getDepositAddress(@Param("id") String accountId) throws UnipaymentSdkException;

    /**
     * Get Deposit Bank Account
     */
    @RequestLine("GET /wallet/accounts/{id}/deposit/bank-account")
    ApiResponse<List<DepositBankAccount>> getDepositBankAccount(@Param("id") String accountId) throws UnipaymentSdkException;

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
