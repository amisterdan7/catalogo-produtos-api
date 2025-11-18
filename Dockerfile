# --- Estágio 1: Construção (Build) ---
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Copia tudo do projeto para dentro do container
COPY . .
# Roda o comando do maven para gerar o .jar (pula testes para ser mais rápido no deploy)
RUN mvn clean package -DskipTests

# --- Estágio 2: Execução (Run) ---
FROM eclipse-temurin:17-jre
WORKDIR /app
# Aqui está o segredo: copiamos o .jar DO estágio de build, não da sua pasta local
COPY --from=build /app/target/catalogo-produtos-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]