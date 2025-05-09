FROM eclipse-temurin:17-jre-alpine

WORKDIR /opt/app

COPY target/*.jar app.jar

EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]