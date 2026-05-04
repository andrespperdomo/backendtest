# =========================
# 1️⃣ Build stage
# =========================
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /apps

# Copy pom.xml first (cache-friendly)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Run tests here
RUN mvn clean test

# Build Quarkus app
RUN mvn package -DskipTests

# =========================
# 2️⃣ Runtime stage
# =========================
FROM eclipse-temurin:17.0.13_11-jre-alpine
WORKDIR /apps

# Copy Quarkus app
COPY target/quarkus-app/ /app/

# Copy JWT keys
#COPY src/main/resources/keys /deployments/keys

EXPOSE 8080

CMD ["java", "-jar", "/app/quarkus-run.jar"]
