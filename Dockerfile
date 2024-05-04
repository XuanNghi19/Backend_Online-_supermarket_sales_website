# Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17

WORKDIR /app
COPY --from=build /build/target/*.jar /app/app.jar

EXPOSE 8080

CMD ["java","-jar","app.jar"]