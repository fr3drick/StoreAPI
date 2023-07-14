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

#FROM openjdk:18
#COPY ./target/StoreAPI-0.0.1-SNAPSHOT.jar /app/runner.jar
#ENTRYPOINT java -jar /app/runner.jar
