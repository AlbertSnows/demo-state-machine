FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY src/main/resources src/main/resources
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]