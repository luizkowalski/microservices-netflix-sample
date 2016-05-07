FROM java:8
VOLUME /eureka-server
ADD target/eureka-server-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8761
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
