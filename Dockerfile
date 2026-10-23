# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn -q package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre
COPY --from=build /build/target/rock-paper-scissors-1.0.0.jar /app.jar
EXPOSE 8080
# Shell form so ${PORT} (set by Render) is expanded at runtime
CMD java -Dserver.port=${PORT:-8080} -jar /app.jar
