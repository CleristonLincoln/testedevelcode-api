# Base image
FROM openjdk:17-alpine

# Working directory
WORKDIR /app

# Copy the application jar file
COPY target/*.jar app.jar

# Expose the port
EXPOSE 8080

# Entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]