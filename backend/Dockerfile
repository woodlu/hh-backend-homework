FROM maven:3.6-jdk-11 as maven_build
COPY pom.xml .
COPY src ./src
RUN --mount=type=cache,target=/root/.m2  mvn clean install -Dmaven.test.skip
ENTRYPOINT ["mvn","exec:java"]