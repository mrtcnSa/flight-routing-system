# 🛫 Flight Routing System

Bu proje, karmaşık uçuş ve ulaşım verilerini yöneterek kullanıcılar için en uygun rotaları hesaplayan, yüksek performanslı bir **Spring Boot** backend uygulamasıdır. Sistem, temiz kod (Clean Code) prensiplerine ve kurumsal katmanlı mimari (Enterprise Layered Architecture) standartlarına uygun olarak geliştirilmiştir.

## 🚀 Öne Çıkan Özellikler

* **Gelişmiş Rota Algoritması:** Sadece direkt uçuşları değil, aktarmalı (Uçuş + Transfer vb.) rotaları da hesaplayabilen iş mantığı.
* **Kurumsal Mimari:** Veri güvenliği ve esneklik için `request` ve `response` olarak tamamen ayrıştırılmış DTO yapısı.
* **Yüksek Performans:** Sık sorgulanan rotalar için `RedisConfig` üzerinden yapılandırılmış Redis önbellekleme (Caching) mekanizması.
* **Merkezi Hata Yönetimi:** Tüm uygulama genelinde tutarlı HTTP statü kodları dönen `@RestControllerAdvice` tabanlı `GlobalExceptionHandler` ve özel hata sınıfları (Custom Exceptions).
* **Otomatik Eşleme:** `mapper` katmanında MapStruct kullanılarak entity ve DTO'lar arası yüksek performanslı, tip güvenli veri dönüşümü.
* **Güvenlik:** `SecurityConfig` ile yapılandırılmış Spring Security katmanı.
* **Dokümantasyon:** `OpenApiConfig` üzerinden entegre edilen, interaktif Swagger arayüzü.
* **Konteyner Desteği:** Dockerfile ve docker-compose ile her ortamda (veritabanı ve önbellek dahil) tek tuşla hızlı kurulum.

## 🛠️ Teknoloji Yığını

* **Java 17**
* **Spring Boot 3.x** (Data JPA, Web, Security, Validation)
* **PostgreSQL** (İlişkisel Veritabanı)
* **Redis** (Önbellekleme)
* **MapStruct** (Object Mapping)
* **Lombok** (Boilerplate kod azaltımı)
* **Docker & Docker Compose**

## 📂 Proje Yapısı

Proje, Sorumlulukların Ayrılması (Separation of Concerns) prensibine göre aşağıdaki gibi modülerize edilmiştir:

```text
src/main/java/.../flight_routing_system/
├── config/       # OpenAPI (Swagger), Redis ve Security yapılandırmaları
├── controller/   # Dış dünyaya açılan REST API uç noktaları
├── dto/          # İstek (Request) ve Yanıt (Response) veri transfer nesneleri
├── exception/    # Merkezi hata yönetimi ve özel exception sınıfları
├── mapper/       # MapStruct ile DTO <-> Entity dönüşüm arayüzleri
├── model/        # Veritabanı tablolarını temsil eden JPA Entity'leri
├── repository/   # Veritabanı erişim ve sorgulama katmanı
├── service/      # Ana iş mantığı ve rota hesaplama algoritmaları
└── util/         # AppConstants ve StringUtil gibi global yardımcı araçlar
