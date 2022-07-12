FROM openjdk:11-jdk 

ADD build/libs/codaasincrona.jar codaasincrona.jar

ENTRYPOINT ["java", "-Xms32m", "-Xmx128m"]

CMD ["-jar", "codaasincrona.jar"]