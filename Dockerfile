# Use official OpenJDK 17 image from Docker Hub
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the application jar file into the container
COPY target/warrantyvault-0.0.1-SNAPSHOT.jar /app/warrantyvault.jar

# Expose port 8080 (the default port for Spring Boot applications)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "warrantyvault.jar"]