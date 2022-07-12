FROM openjdk:11-jdk 

ADD build/libs/coordinatore.jar coordinatore.jar

ENTRYPOINT ["java", "-Xms32m", "-Xmx128m"]

CMD ["-jar", "coordinatore.jar"]