package com.thy.technology.flight_routing_system.util;

public final class StringUtil {

    private StringUtil() {}

    /**
     * Lokasyon kodunu (IATA) temizler ve büyük harfe çevirir.
     * Örn: " ist " -> "IST"
     */
    public static String formatLocationCode(String code) {
        if (code == null) return null;
        return code.trim().toUpperCase();
    }

    /**
     * String ifadenin boş olup olmadığını kontrol eder.
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}