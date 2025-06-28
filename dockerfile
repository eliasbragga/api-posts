# Stage 1: Build com Maven
FROM maven:3.9.3-eclipse-temurin-17 as builder

WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Faz o build do projeto e gera o JAR (com pacote 'package')
RUN mvn clean install -DskipTests

# Stage 2: Image runtime
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia o JAR gerado no stage anterior para o diretório de trabalho
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
