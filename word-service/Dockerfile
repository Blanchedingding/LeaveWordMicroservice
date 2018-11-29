FROM java:8-jre

ADD ./target/word-service-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/word-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8081