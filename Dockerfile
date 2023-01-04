FROM openjdk:17
COPY . /app/
WORKDIR /app/
RUN chmod +x mvnw
RUN ./mvnw clean package
EXPOSE 8080
ENTRYPOINT ["./mvnw","spring-boot:run","-Dspring-boot.run.profiles=prod"]