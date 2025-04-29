# Etapa 1: build da aplicação com Gradle (opcional para otimizar imagem final)
FROM gradle:8.4-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

# Etapa 2: imagem final com o JAR
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]