FROM java:8-alpine
VOLUME /contacts
ADD target/contacts-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app.jar"]
