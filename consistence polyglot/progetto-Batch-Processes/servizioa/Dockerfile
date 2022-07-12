FROM openjdk:11-jdk 

ADD build/libs/servizioa.jar servizioa.jar

ENTRYPOINT ["java", "-Xms32m", "-Xmx128m"]

CMD ["-jar", "servizioa.jar"]