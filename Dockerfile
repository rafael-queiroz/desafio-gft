FROM openjdk:17.0.1-jdk-slim

COPY target/demo-api.jar /usr/local/lib/demo-api.jar

#RUN echo "America/Sao_Paulo"  > /etc/timezone && dpkg-reconfigure -f noninteractive tzdata

RUN apt update && apt install tzdata -y

ENV TZ="America/Sao_Paulo"

EXPOSE 8080

ENTRYPOINT ["java", "-XX:MinHeapFreeRatio=20","-XX:MaxHeapFreeRatio=40", "-jar", "/usr/local/lib/demo-api.jar"]


