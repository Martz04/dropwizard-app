FROM gradle:jdk17 as builder

WORKDIR /src/app
COPY ./ ./
RUN gradle build --no-daemon

FROM openjdk:17-alpine

WORKDIR /app

ENV db_user="default" \
    db_pass="secret" \
    db_url="jdbc:h2:mem:testdb"

COPY --from=builder /src/app/service/build/libs/service-0.1.0-SNAPSHOT-all.jar /app/HelloWorld.jar
COPY server.yml /app/server.yml

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/HelloWorld.jar", "server", "/app/server.yml"]