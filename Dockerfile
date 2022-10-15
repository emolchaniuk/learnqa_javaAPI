FROM maven:3.8.6-openjdk-18
WORKDIR /tests
COPY . .
CMD mvn clean test