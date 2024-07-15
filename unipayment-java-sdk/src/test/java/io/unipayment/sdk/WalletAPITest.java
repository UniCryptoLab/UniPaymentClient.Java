package io.unipayment.sdk;

import io.unipayment.sdk.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WalletAPITest extends BaseAPITest {

    private static WalletAPI walletAPI;
    private static String withdrawalId;

    @BeforeAll
    public static void setUp() {
        walletAPI = WalletAPI.getInstance(configuration);
    }

    @Test
    public void testGetWalletBalances() {
        ApiResponse<List<WalletBalance>> apiResponse = walletAPI.getWalletBalances(getAccessToken());
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    public void testGetWalletAccounts() {
        ApiResponse<List<WalletAccount>> apiResponse = walletAPI.getWalletAccounts(getAccessToken());
        assertEquals(apiResponse.getCode(), "OK");
        assertNotNull(apiResponse.getData());
    }

    @Test
    public void testQueryTransactions() {
        QueryWalletTransactionsRequest queryWalletTransactionsRequest = new QueryWalletTransactionsRequest();
        ApiResponse<QueryResult<Transaction>> apiResponse = walletAPI.queryTransactions(getAccessToken(), "ca013a0b-d688-446b-9cd0-49215eec2cea", queryWalletTransactionsRequest);
        assertEquals(apiResponse.getCode(), "OK");
        assertNotNull(apiResponse.getData());
    }

    @Test
    public void testGetDepositAddress() {
        ApiResponse<List<WalletAccount>> apiResponse = walletAPI.getDepositAddress(getAccessToken(), "ca013a0b-d688-446b-9cd0-49215eec2cea");
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    public void testGetDepositBankAccount() {
        ApiResponse<List<WalletAccount>> apiResponse = walletAPI.getDepositBankAccount(getAccessToken(), "ca013a0b-d688-446b-9cd0-49215eec2cea");
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    public void testGetWithdrawalFees() {
        ApiResponse<List<WithdrawalFee>> apiResponse = walletAPI.getWithdrawalFees(getAccessToken(), "USDT");
        assertEquals(apiResponse.getCode(), "OK");
    }
}