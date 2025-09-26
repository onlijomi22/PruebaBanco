#Java 17
FROM eclipse-temurin:17-jdk-jammy

# carpeta
WORKDIR /app

# jar
COPY target/account-service-0.0.1-SNAPSHOT.jar app.jar

# puerto
EXPOSE 8082

# ejecutar
ENTRYPOINT ["java", "-jar", "app.jar"]
