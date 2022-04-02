package io.unipayment.client;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class Utils {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS");

    public static String getContentBase64String(String content) {
        byte[] hash = DigestUtils.md5(content);
        System.out.println(Hex.encodeHexString(hash));
        return Base64.encodeBase64String(hash);
    }

    public static String sign(String appId, String apiKey, String requestHttpMethod, String requestUri, long requestTimeStamp, String nonce, String requestContentBase64String) {
        String signatureRawData = appId + requestHttpMethod + requestUri + requestTimeStamp + nonce + requestContentBase64String;
        byte[] hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, apiKey.getBytes(StandardCharsets.UTF_8)).hmac(signatureRawData);
        String requestSignatureBase64String = Base64.encodeBase64String(hmac);
        return appId + ":" + requestSignatureBase64String + ":" + nonce + ":" + requestTimeStamp;
    }

    public static String getCurrentUtcTime() {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        return now.format(DATE_FORMAT);
    }

}
