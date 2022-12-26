FROM openjdk:17
COPY build/libs/rschir-0.0.1-SNAPSHOT.jar rschir.jar
ENTRYPOINT ["java", "-jar", "rschir.jar"]