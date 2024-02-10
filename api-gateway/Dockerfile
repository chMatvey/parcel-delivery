FROM openjdk:21

COPY /build/libs/*.jar app.jar

ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]