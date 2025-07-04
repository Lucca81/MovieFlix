FROM eclipse-temurin:21-jdk-jammy as builder

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos do Maven Wrapper e o pom.xml
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Baixa todas as dependências do projeto
RUN ./mvnw dependency:go-offline

# Copia o resto do código-fonte
COPY src ./src

# Compila o projeto e cria o arquivo .jar. Pula os testes para acelerar o build.
RUN ./mvnw package -DskipTests


# Estágio 2: A Imagem Final - Usa apenas o Java para rodar (JRE), que é muito menor
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia APENAS o arquivo .jar gerado no estágio anterior para a imagem final
COPY --from=builder /app/target/*.jar app.jar

# Define o comando que será executado quando o contêiner iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]