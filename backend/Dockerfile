FROM maven:3.6-jdk-11 as maven_build
COPY pom.xml .

#RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r target/
#COPY src ./src
#RUN mvn clean package -Dmaven.test.skip
#RUN mkdir -p target/docker-packaging && cd target/docker-packaging && jar -xf ../backend.jar

#ENTRYPOINT ["mvn","exec:java"]
#FROM adoptopenjdk/openjdk11
#WORKDIR /app
#ARG DOCKER_PACKAGING_DIR=/app/target/docker-packaging

#COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/BOOT-INF/lib /app/lib
#COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/BOOT-INF/classes /app/classes
#COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/META-INF /app/META-INF


#ARG JAR_FILE=target/original-backend.jar
#WORKDIR /homework
#COPY ${JAR_FILE} homework.jar
#ENTRYPOINT ["java","-jar","homework.jar"]