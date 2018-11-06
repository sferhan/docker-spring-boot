FROM openjdk:8-jdk-alpine
ARG JAR_FILE_PATH=build/libs/docker-spring-boot-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE_PATH} app.jar
EXPOSE 8085
ENTRYPOINT [ "java", "-jar", "app.jar" ]
VOLUME [ "/data" ]