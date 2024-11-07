FROM openjdk:17-jdk-alpine
EXPOSE 8082
ADD target/5NIDS2-G2-tpfoyer.jar tp-foyer-5.0.0.jar
ENTRYPOINT ["java","-jar","/tp-foyer-5.0.0.jar"]
