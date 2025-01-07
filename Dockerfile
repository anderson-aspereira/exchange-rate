FROM openjdk:17
WORKDIR /app
COPY target/exchange-rate-0.0.1-SNAPSHOT.jar /app/exchangeRate.jar
EXPOSE  8080
ENTRYPOINT ["java", "-jar", "/app/exchangeRate.jar"]