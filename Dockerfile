FROM amazoncorretto:17

ARG JAR_FILE=/build/libs/project-easy-community-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Dspring.profiles.include=prod", "-jar", "app.jar"]