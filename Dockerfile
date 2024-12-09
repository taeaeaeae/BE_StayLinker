# Base image
FROM ubuntu:24.04

RUN apt-get update && \
    apt-get install -y openjdk-21-jdk wget unzip dos2unix&& \
    apt-get clean

RUN wget https://services.gradle.org/distributions/gradle-8.10-bin.zip && \
    unzip gradle-8.10-bin.zip -d /opt && \
    ln -s /opt/gradle-8.10/bin/gradle /usr/bin/gradle

WORKDIR /app

COPY gradlew gradlew
COPY gradle/ gradle/

COPY . .

COPY src/main/kotlin /app

RUN dos2unix gradlew && chmod +x gradlew

RUN ./gradlew build -x test --no-daemon

EXPOSE 8080
CMD ["java", "-jar", "build/libs/StayLinker-0.0.1-SNAPSHOT.jar"]