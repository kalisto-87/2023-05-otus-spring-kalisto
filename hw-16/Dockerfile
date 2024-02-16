FROM bellsoft/liberica-openjdk-alpine-musl:17
COPY /target/hw-16-1.0.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]