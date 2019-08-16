FROM adoptopenjdk/openjdk11:jdk-11.0.3_7
RUN mkdir -p /opt/app/logs
ADD build/libs/rollingupdate-*.jar /opt/app/app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dapplication.logging.directory=/opt/app/logs -jar /opt/app/app.jar" ]