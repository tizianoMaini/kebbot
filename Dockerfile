FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ENV DATABASE_URL postgres://docker:docker@db:5432/kebbot
ADD /target/tizio-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]