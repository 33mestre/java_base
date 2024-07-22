# ---------------------------------
#    _____ __         __                    ______                          _
#   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
#   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
#  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
# /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
# ---------------------------------
# Currency Conversion API - Main Dockerfile
# ---------------------------------
# This Dockerfile is responsible for installing, configuring, running, and starting:
# - The Java HTTP server on port 8080
# - The H2 server on port 8092
# 
# Dockerfile Features:
# - Uses the OpenJDK 17 slim base image.
# - Installs Maven.
# - Sets the working directory inside the container.
# - Copies the pom.xml file to the container.
# - Downloads Maven dependencies without running tests.
# - Copies the source code files to the container.
# - Copies the sys folder and its contents to the container.
# - Gives execution permission to the callJavaFromDocker.sh script.
# - Installs Maven dependencies and generates the JAR.
# - Exposes ports 8080, 1521, and 81.
# - Sets the default command to start the callJavaFromDocker.sh script.
# ---------------------------------

# ---------------------------------
# Uses the OpenJDK 17 slim base image
# ---------------------------------
FROM openjdk:17-slim

# ---------------------------------
# Installs Maven
# ---------------------------------
RUN apt-get update && apt-get install -y maven

# ---------------------------------
# Sets the working directory inside the container
# ---------------------------------
WORKDIR /app

# ---------------------------------
# Copies the pom.xml file to the container
# ---------------------------------
COPY pom.xml .

# ---------------------------------
# Copies the .env file to the container
# ---------------------------------
COPY .env .env

# ---------------------------------
# Copies the project.properties file to the container
# ---------------------------------
COPY project.properties project.properties

# ---------------------------------
# Downloads Maven dependencies without running tests
# ---------------------------------
RUN mvn dependency:go-offline

# ---------------------------------
# Copies the source code files to the container
# ---------------------------------
COPY src ./src

# ---------------------------------
# Copies the sys folder and its contents to the container
# ---------------------------------
COPY sys ./sys

# ---------------------------------
# Gives execution permission to the callJavaFromDocker.sh script
# ---------------------------------
RUN chmod +x sys/callJavaFromDocker.sh

# ---------------------------------
# Step to install Maven dependencies and generate the JAR
# ---------------------------------
RUN mvn -B clean install -Pci-cd -DskipTests

# ---------------------------------
# Exposes ports
# ---------------------------------
EXPOSE 8080 1521 81

# ---------------------------------
# Command to start the callJavaFromDocker.sh script
# ---------------------------------
CMD ["./sys/callJavaFromDocker.sh"]
