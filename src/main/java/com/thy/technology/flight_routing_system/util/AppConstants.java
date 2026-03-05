package com.thy.technology.flight_routing_system.util;

public final class AppConstants {

   
    private AppConstants() {}

    // Tarih formatı sabiti
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    
    // Hata mesajları için sabitler
    public static final String LOCATION_NOT_FOUND_MSG = "ID'si %d olan konum bulunamadı!";
    public static final String TRANSPORTATION_NOT_FOUND_MSG = "ID'si %d olan ulaşım kaydı bulunamadı!";
    
    // Cache isimleri
    public static final String CACHE_ROUTES = "routes";
} 