cd jwt-spring-boot-starter
gradlew.bat build
gradlew.bat publishToMavenLocal

cd ../user
gradlew.bat bootJar

cd ../auth
gradlew.bat bootJar

cd ../order
gradlew.bat bootJar

cd ../delivery
gradlew.bat bootJar

cd ../api-gateway
gradlew.bat bootJar