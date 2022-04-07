FROM adoptopenjdk/openjdk11
ARG JAR_FILE=target/backend.jar
WORKDIR /homework
COPY ${JAR_FILE} homework.jar
ENTRYPOINT ["java","-jar","homework.jar"]