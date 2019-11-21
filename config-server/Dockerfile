FROM openjdk:8-jdk-alpine

WORKDIR /apps

COPY . /apps

RUN ./gradlew clean build

EXPOSE 8888

CMD ./gradlew bootRun