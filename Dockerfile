# делаем сборку Gradle'ом внутри контейнера
FROM gradle:6.4.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

# back
# устанавливаем самую лёгкую версию JVM
FROM openjdk:8-jdk-alpine

# указываем точку монтирования для внешних данных внутри контейнера (как мы помним, это Линукс)
VOLUME /tmp

# внешний порт, по которому наше приложение будет доступно извне
EXPOSE 8080

COPY --from=build /home/gradle/src/build/libs/*.jar /app.jar

# команда запуска джарника
ENTRYPOINT ["java","-jar","/app.jar"]