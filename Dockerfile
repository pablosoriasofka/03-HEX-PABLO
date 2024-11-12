FROM bitnami/gradle AS build
WORKDIR /app
COPY . .

# Construir la aplicación
RUN gradle build --no-daemon

# Etapa de ejecución
FROM openjdk:21-jdk
WORKDIR /app

# Copiar el archivo JAR generado desde la etapa de construcción
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Configurar las opciones de Java
ENV JAVA_TOOL_OPTIONS="-XX:InitialHeapSize=1024m -XX:MaxHeapSize=2048m -XX:MaxRAMPercentage=80 -Xmx2048m -Xms1024m"

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8080