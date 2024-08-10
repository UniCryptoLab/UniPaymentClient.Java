package io.unipayment.sdk;

import io.unipayment.sdk.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WalletAPITest extends BaseAPITest {

    private static WalletAPI walletAPI;
    private static String fiatAccountId;
    private static String cryptoAccountId;

    @BeforeAll
    public static void setUp() {
        walletAPI = WalletAPI.getInstance(configuration);
    }

    @Test
    public void testGetWalletBalances() {
        ApiResponse<List<WalletBalance>> apiResponse = walletAPI.getBalances();
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    public void testGetWalletAccounts() {
        ApiResponse<List<WalletAccount>> apiResponse = walletAPI.getAccounts();
        assertEquals(apiResponse.getCode(), "OK");
        assertNotNull(apiResponse.getData());
        fiatAccountId = apiResponse.getData().stream().filter(a -> "USD".equalsIgnoreCase(a.getAssetType())).findFirst().get().getId();
        cryptoAccountId = apiResponse.getData().stream().filter(a -> "BNB".equalsIgnoreCase(a.getAssetType())).findFirst().get().getId();
    }

    @Test
    public void testQueryTransactions() {
        ApiResponse<List<WalletAccount>> apiResponse = walletAPI.getAccounts();
        fiatAccountId = apiResponse.getData().stream().filter(a -> "USD".equalsIgnoreCase(a.getAssetType())).findFirst().get().getId();
        QueryWalletTransactionsRequest queryWalletTransactionsRequest = new QueryWalletTransactionsRequest();
        ApiResponse<QueryResult<Transaction>> apiResponse2 = walletAPI.queryTransactions(fiatAccountId, queryWalletTransactionsRequest);
        assertEquals(apiResponse2.getCode(), "OK");
        assertNotNull(apiResponse2.getData());
    }

    @Test
    public void testGetDepositAddress() {
        ApiResponse<List<DepositAddress>> apiResponse = walletAPI.getDepositAddress(cryptoAccountId);
        assertEquals(apiResponse.getCode(), "OK");
    }

    @Test
    public void testGetDepositBankAccount() {
        ApiResponse<List<DepositBankAccount>> apiResponse = walletAPI.getDepositBankAccount(fiatAccountId);
        assertEquals(apiResponse.getCode(), "OK");
    }
}