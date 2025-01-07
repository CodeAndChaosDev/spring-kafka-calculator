# Base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file
COPY target/rest-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
