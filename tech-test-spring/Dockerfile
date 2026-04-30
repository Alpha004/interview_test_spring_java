FROM maven:3.9.6-eclipse-temurin-21-alpine as builder
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests && \
    rm -rf /root/.m2

FROM gcr.io/distroless/java21-debian12
WORKDIR /app
COPY --from=builder /target/*.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]