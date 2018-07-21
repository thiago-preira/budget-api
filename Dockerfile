FROM openjdk:jre-alpine

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/budget-api/budget-api.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/budget-api/budget-api.jar