# Use a base image with OpenJDK
FROM openjdk:17-jdk-alpine

# Set a working directory inside the container
WORKDIR /app

# Copy the JAR file from the current directory (assuming it's built)
COPY target/tp-foyer-5.0.0.jar app.jar

# Expose the port your Spring Boot application listens on
EXPOSE 8089

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
