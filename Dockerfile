#FROM maven:latest
#LABEL author="tonykarlin"
#
#WORKDIR /app
#COPY pom.xml /app/
#COPY src /app/src
#RUN mvn package -DskipTests
#CMD ["java", "-jar", "target/OTP-2-1.0-SNAPSHOT.jar", "SimpleShoppingCart.Main"]
FROM openjdk:17-jdk-slim
LABEL author="tonykarlin"

WORKDIR /app
RUN apt-get update && apt-get install -y \
    libx11-6 libxext6 libxrender1 libxtst6 libxi6 libgtk-3-0 mesa-utils wget unzip \
    fonts-noto-cjk \
    fonts-takao fonts-vlgothic \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /javafx-sdk \
    && wget -O javafx.zip https://download2.gluonhq.com/openjfx/21.0.2/openjfx-21.0.2_linux-x64_bin-sdk.zip \
    && unzip javafx.zip -d /javafx-sdk \
    && mv /javafx-sdk/javafx-sdk-21.0.2/lib /javafx-sdk/lib \
    && rm -rf /javafx-sdk/javafx-sdk-21.0.2 javafx.zip

COPY target/OTP-2-1.0-SNAPSHOT.jar app.jar

ENV DISPLAY=host.docker.internal:0.0

CMD ["java", "--module-path", "/javafx-sdk/lib", "--add-modules", "javafx.controls,javafx.fxml", "-jar", "app.jar"]