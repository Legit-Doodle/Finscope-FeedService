# Feed Service - Developer Documentation

## Overview
A Spring Boot reactive service for managing posts and comments, backed by Azure Cosmos DB.

## Tech Stack
- **Java 21**
- **Spring Boot 3.5.8**
- **Spring WebFlux** (Reactive)
- **Azure Cosmos DB** (NoSQL database)
- **Lombok** (Boilerplate reduction)
- **Maven** (Build tool)

## Build & Run

| Command | Description |
|---------|-------------|
| `./mvnw clean package` | Clean and build JAR |
| `./mvnw clean package -DskipTests` | Build without running tests |
| `./mvnw spring-boot:run` | Run the app directly |
| `java -jar target/feed-0.0.1-SNAPSHOT.jar` | Run the built JAR |
| `./mvnw test` | Run unit tests |

## Deploy to Azure

1. Install the **Azure Maven Web App Plugin** in your `pom.xml`
2. Run the following command:
```bash
mvn package azure-webapp:deploy
```
