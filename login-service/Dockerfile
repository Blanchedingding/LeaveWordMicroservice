FROM java:8-jre

ADD ./target/login-service-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/login-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8081