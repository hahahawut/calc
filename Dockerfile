# Use an official OpenJDK runtime as a parent image
FROM openjdk:11

# Set the working directory in the container
WORKDIR /app

# If your application uses a specific port, expose it
EXPOSE 8080

# Copy the JAR file from your target directory to the container
COPY ./target/calculator-service-0.0.1-SNAPSHOT.jar /app/calculator-service.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/calculator-service.jar"]
