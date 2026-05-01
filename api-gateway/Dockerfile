FROM maven:3.9.6-eclipse-temurin-21-alpine as builder
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests && \
	rm -rf /root/.m2

FROM gcr.io/distroless/java21-debian12:nonroot
WORKDIR /app
COPY --from=builder /target/*.jar api-gateway.jar
EXPOSE 8761
USER 65532
ENTRYPOINT [ "java", "-jar", "api-gateway.jar" ]