#!/bin/bash

cd ./jwt-spring-boot-starter
./gradlew build
./gradlew publishToMavenLocal

cd ../user
./gradlew bootJar

cd ../auth
./gradlew bootJar

cd ../order
./gradlew bootJar

cd ../delivery
./gradlew bootJar

cd ../api-gateway
./gradlew bootJar