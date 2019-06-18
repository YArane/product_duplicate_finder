FROM openjdk:8
RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml /project/pom.xml
COPY src /project/src
WORKDIR /project
RUN mvn package
EXPOSE 8080
CMD ["java","-jar","target/homework-1.0-SNAPSHOT-jar-with-dependencies.jar"]

