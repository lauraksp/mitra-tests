FROM openjdk

WORKDIR /app

COPY target/mitra-tests-0.0.1-SNAPSHOT.jar /app/mitra-tests.jar

ENTRYPOINT ["java", "-jar", "mitra-tests.jar"]

