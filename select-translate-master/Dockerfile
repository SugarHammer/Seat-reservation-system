FROM openjdk:8

COPY core-0.0.1-SNAPSHOT.jar /app.jar
CMD ["--server.port=21212"]
EXPOSE 21212
ENTRYPOINT ["java","-jar","/app.jar"]
