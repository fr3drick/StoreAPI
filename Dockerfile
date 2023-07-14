#FROM maven:3.8.6-openjdk-18
#WORKDIR /usr/app
#COPY .mvn/ .mvn
#
#
#FROM maven:3.8.6-openjdk-18 as build
#ENV HOME=/usr/app
#RUN mkdir -p $HOME
#WORKDIR $HOME
#ADD . $HOME
#RUN mvn package
#
#FROM fr3drick/storeapirunnerjar
#COPY ./target/StoreAPI-0.0.1-SNAPSHOT.jar /app/runner.jar
#ENTRYPOINT java -jar /app/runner.jar
#
#FROM mavenimage
#ENV HOME=/usr/app
#RUN mkdir -p $HOME
#WORKDIR $HOME
#COPY ./.mvn ./.mvn
#COPY ./pom.xml ./pom.xml
#RUN mvn dependency:go-offline -B
#COPY ./src ./src
#RUN mvn package
#ENTRYPOINT java -jar ./target/StoreAPI-0.0.1-SNAPSHOT.jar

FROM mavenimage
COPY ./target/StoreAPI-0.0.1-SNAPSHOT.jar /app/runner.jar
ENTRYPOINT java -jar /app/runner.jar