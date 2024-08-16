package io.unipayment.sdk.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class WebhookSignatureUtil {

    /**
     * Validates the provided signature against the generated signature.
     *
     * @param payload           The payload data to be signed.
     * @param secretKey         The secret key used for generating the signature.
     * @param signatureToVerify The signature to verify against.
     * @return Returns true if the signatures match, otherwise false.
     */
    public static boolean isValid(String payload, String secretKey, String signatureToVerify) {
        return signatureToVerify.equals(generateSignature(payload, secretKey));
    }

    /**
     * Generates an HMAC-SHA256 signature and encodes it in Base64.
     *
     * @param payload   The payload data to be signed.
     * @param secretKey The secret key used for generating the signature.
     * @return The generated Base64-encoded signature.
     */
    public static String generateSignature(String payload, String secretKey) {
        try {
            // Create an HMAC-SHA256 hash
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            // Generate the hash and encode it in Base64
            byte[] hashBytes = sha256_HMAC.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate signature", e);
        }
    }
}

