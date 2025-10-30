FROM maven:latest
LABEL author="tonykarlin"

WORKDIR /app
COPY pom.xml /app/
COPY src /app/src
RUN mvn package -DskipTests
CMD ["java", "-jar", "target/OTP-2-1.0-SNAPSHOT.jar", "SimpleShoppingCart.Main"]