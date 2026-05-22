FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

ENTRYPOINT ["sh","-c","mvn test $GROUP -Denv=$ENV && mvn allure:report"]