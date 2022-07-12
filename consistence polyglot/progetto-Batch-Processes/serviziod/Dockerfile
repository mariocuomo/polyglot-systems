FROM openjdk:11-jdk 

ADD build/libs/serviziod.jar serviziod.jar

ENTRYPOINT ["java", "-jar", "-Xms32m", "-Xmx128m"]

CMD ["-jar", "serviziod.jar"]
