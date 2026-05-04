FROM maven:3.9.6-eclipse-temurin-21-alpine as builder
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests && \
	rm -rf /root/.m2

FROM gcr.io/distroless/java21-debian12
WORKDIR /app
COPY --from=builder /target/*.jar tech-test-spring-account-movement.jar
EXPOSE 8081
ENV SPRING_DATA_MYSQL_URL=
ENV SPRING_DATA_MYSQL_USERNAME=
ENV SPRING_DATA_MYSQL_PASSWORD=
ENV KAFKA_HOST=
ENTRYPOINT [ "java", "-jar", "tech-test-spring-account-movement.jar" ]