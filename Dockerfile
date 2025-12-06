# Build Stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -Pproduction -DskipTests && \
    rm -rf ~/.m2/repository /app/target/test-classes

# Runtime Stage
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/mnemonics.jar /
EXPOSE 8080:8080
ENTRYPOINT ["java", "-jar", "/mnemonics.jar"]