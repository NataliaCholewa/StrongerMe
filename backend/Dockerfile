# Etap buildowania aplikacji
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

# Kopiujemy cały backend (łącznie z pom.xml i kodem źródłowym)
COPY . .

# Budujemy aplikację
RUN mvn clean package -DskipTests

# Etap uruchamiania aplikacji
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Kopiujemy zbudowany plik .jar z poprzedniego etapu
COPY --from=build /app/target/*.jar app.jar

# Odpalamy Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
