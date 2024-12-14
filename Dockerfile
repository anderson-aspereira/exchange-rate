FROM openjdk:17
WORKDIR /exchange-rate
COPY ./ ./
EXPOSE  8080