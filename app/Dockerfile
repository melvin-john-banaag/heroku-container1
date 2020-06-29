##############################
#Simple Container for Heroku #
##############################

# This is the maven base image with JDK 8
FROM maven:3.6.3-jdk-8 as maven
WORKDIR /usr/app
COPY src src  
COPY pom.xml pom.xml  
RUN mvn clean install

# This will run the JAR file
FROM maven
ENTRYPOINT ["java","-jar","/usr/app/target/heroku-demo-0.0.1-SNAPSHOT.jar"]

