# ============================================
# 🐳 DOCKERFILE PARA DEPLOY NO RENDER
# ============================================

# Etapa 1: compila o projeto
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: executa a aplicação
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR gerado da etapa anterior
COPY --from=build /app/target/app_agendamento-0.0.1-SNAPSHOT.jar app.jar

# Define a porta que o Render usará

EXPOSE 8080

# Variáveis de ambiente para o Spring Boot
ENV SPRING_PROFILES_ACTIVE=dev

# Comando para rodar o app
ENTRYPOINT ["java", "-jar", "app.jar"]
