package io.unipayment.sdk.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenValidationUtilTest {

    @Test
    public void testInvalidToken_NullOrEmpty() {
        assertFalse(TokenValidationUtil.isValid(null));
        assertFalse(TokenValidationUtil.isValid(""));
    }

    @Test
    public void testInvalidToken_InvalidAccessToken() {
        assertFalse(TokenValidationUtil.isValid("invalidToken"));
        assertFalse(TokenValidationUtil.isValid("invalid.Token.xxx"));
    }

    @Test
    public void testInvalidToken_ExpiredAccessToken() {
        assertFalse(TokenValidationUtil.isValid("eyJhbGciOiJSUzI1NiIsImtpZCI6IkYxQ0U5MDVGNjUzMTU3MEM1QTFBOEE2NzJFNDNBMkI5RDA4RkU0RjciLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiI4YzZRWDJVeFZ3eGFHb3BuTGtPaXVkQ1A1UGMifQ.eyJuYmYiOjE3MjAxNzI5MTEsImV4cCI6MTcyMDE3NjUxMSwiaXNzIjoiaHR0cHM6Ly9kZXYtZjVidjBlLWFwaS4zdHJ1bmtzLmNvbS8iLCJhdWQiOiJjb21tb24tYXBpIiwiY2xpZW50X2lkIjoiNjRlMzI4ODgtMjRhMS00ZDMzLTgxMmEtOTlkZTFkMmUzMGYwIiwiY2xpZW50X2F1dGhfdHlwZSI6Im9wZW4iLCJqdGkiOiJCNTREMUMyNzczMDg4NEU5OTY5NUEyNDQ5OEQyOEUzMCIsImlhdCI6MTcyMDM3MjkxMSwic2NvcGUiOlsiY29tbW9uIl19.iBVpnRGwKFpqjtlblgHdL4FQS2bSk-EWxdHfmht47Z1tuVTOZIxmQb9p1JKkUaoRmEwqquPLoUUjWNsphS7jhupab8KWoeW2ahwYDf8gtIFItEtxD6CFdkm63d0Zu0-u9XB9p8jlAB4qUh3Zgyq8dngMF2RFmYevujkLDfcXEB71QMONyVfIltpGRMwxmh9wasUH2JJoyX4xNITpDo_QM4bAoBJj_f8LLIKKD1YSMEQvHee0VEGrAlbYRgZ_vXT9S5BADtJ3L7ReqBTdGzlpRZN6gpZ3QZij4AslVDW4MLHF1aPjRcHjpoohEpPVhMTUMa4v1eXarB2-j-fQBmS18g"));
    }
}