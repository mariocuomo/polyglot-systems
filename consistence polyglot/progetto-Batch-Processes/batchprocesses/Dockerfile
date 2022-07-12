FROM openjdk:11-jdk 

ADD build/libs/batchprocesses.jar batchprocesses.jar

ENTRYPOINT ["java", "-Xms32m", "-Xmx128m"]

CMD ["-jar", "batchprocesses.jar"]