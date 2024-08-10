package io.unipayment.sdk.utils;

import java.util.HashMap;
import java.util.Map;

public class TokenCacheUtil {

    private static final Map<String, CacheItem> cache = new HashMap<>();
    public static final String ACCESS_TOKEN = "access_token";

    public static void setAccessToken(String value, long ttl) {
        long expiry = System.currentTimeMillis() + ttl;
        cache.put(ACCESS_TOKEN, new CacheItem(expiry, value));
    }

    public static String getAccessToken() {
        if (!cache.containsKey(ACCESS_TOKEN)) {
            return null;
        }
        CacheItem cachedItem = cache.get(ACCESS_TOKEN);
        if (System.currentTimeMillis() > cachedItem.expiry()) {
            cache.remove(ACCESS_TOKEN);
            return null;
        }
        return cachedItem.value();
    }

    private record CacheItem(long expiry, String value) {
    }
}
