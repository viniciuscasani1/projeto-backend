FROM maven:jdk-11-slim

run mvn clean install

COPY ./target/projeto-backend.jar /usr/src/projeto/

WORKDIR /usr/src/projeto

EXPOSE 8080

CMD ["java", "-jar", "projeto-backend.jar"]