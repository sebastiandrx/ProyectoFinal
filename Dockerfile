# Imagen base con Java y Maven
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Establece directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y descarga dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia todo el código del proyecto
COPY . .

# Compila el proyecto y empaqueta el JAR
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia el archivo .jar compilado desde la imagen anterior
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto dinámico que Render asignará
ENV PORT=8080
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
