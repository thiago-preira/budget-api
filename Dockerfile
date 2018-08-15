FROM openjdk:jre-alpine

ENTRYPOINT ["/usr/bin/java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/share/budget-api/budget-api.jar"]

ARG JAR_FILE
COPY ${JAR_FILE} /usr/share/budget-api/budget-api.jar
EXPOSE 9000