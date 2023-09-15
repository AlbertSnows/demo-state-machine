# Use an official Maven image with Java 17 as the base image
FROM maven:3.8.4-openjdk-17-slim
# Set the working directory in the container
WORKDIR /app
# Copy the entire Maven project into the container
COPY . /app
# This will pull in application-docker.properties instead of local
ENV MY_APP_ENV=docker
# Build the Spring Boot application using Maven
RUN mvn clean package
# Run the Spring Boot application
CMD ["mvn", "spring-boot:run"]

#FROM eclipse-temurin:17-jdk-alpine
#VOLUME /tmp
#COPY src/main/resources src/main/resources
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]