# Build Stage
FROM maven:3.8.5-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -Pproduction -DskipTests && \
    rm -rf ~/.m2/repository /app/target/test-classes

# Runtime Stage
FROM gcr.io/distroless/java17
WORKDIR /app
COPY --from=build /app/target/mnemonics.jar /
EXPOSE 8080:8080
ENTRYPOINT ["java", "-jar", "/mnemonics.jar"]