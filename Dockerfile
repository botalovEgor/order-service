FROM openjdk:11-jdk-slim
COPY ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8000
