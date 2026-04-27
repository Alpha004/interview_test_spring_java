FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY tech-test-spring/pom.xml tech-test-spring/pom.xml
COPY tech-test-spring/src tech-test-spring/src
COPY tech-test-spring/mvnw tech-test-spring/mvnw
COPY tech-test-spring/.mvn tech-test-spring/.mvn
WORKDIR /app/tech-test-spring
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/tech-test-spring/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]