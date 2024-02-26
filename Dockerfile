FROM eclipse-temurin:17-jdk-focal  as base
WORKDIR /app
COPY .mvn ./.mvn
COPY src ./src
COPY pom.xml ./pom.xml
COPY mvnw ./
#RUN #./mvnw dependency:go-offline
#CMD ["./mvnw","spring-boot:run"]
RUN ./mvnw dependency:resolve clean package

FROM eclipse-temurin:17-jre as development
WORKDIR /app
COPY --from=base /app/target/*.jar /mini-bank.jar
CMD ["java","-jar","/mini-bank.jar"]


