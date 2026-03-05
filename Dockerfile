# Java 17 kullanan hafif bir işletim sistemi kur
FROM eclipse-temurin:17-jdk-alpine

# Konteyner içinde çalışacağımız klasörü belirle
WORKDIR /app

# JAR dosyasını konteynerin içine 'app.jar' adıyla kopyala
COPY target/flight-routing-system-0.0.1-SNAPSHOT.jar app.jar

# Uygulamanın 8080 portunda çalışacağını belirt
EXPOSE 8080

# Konteyner ayağa kalktığında çalıştırılacak komut
ENTRYPOINT ["java", "-jar", "app.jar"]