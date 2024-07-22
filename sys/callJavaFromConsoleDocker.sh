#!/bin/bash

# ---------------------------------
#    _____ __         __                    ______                          _
#   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
#   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
#  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
# /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
# ---------------------------------
# Currency Conversion API - Unix / Linux Version
# Script to start the Java application
# ---------------------------------
# This script starts the Java application using the specified JAR file.
# It is called by the Dockerfile.
# It sets a security property for the JVM's random number generator,
# pointing to a file that improves performance on Linux environments.
# 
# Script features:
# - Sets a security property for the JVM's random number generator.
# - Starts the Java application using the specified JAR file.
# ---------------------------------

#!/bin/bash

# Load variables from the .env and project.properties files
. /app/.env
. /app/project.properties

# Start the application
echo "The project version is $PROJECT_VERSION"
echo "The project ID is $PROJECT_ID"

# Dynamically create the pom.xml file
cat <<EOL > pom.xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.shelson</groupId>
    <artifactId>$PROJECT_ID</artifactId>
    <version>$PROJECT_VERSION</version>
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20231013</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.32</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.32</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.2.4</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals><goal>shade</goal></goals>
                        <configuration>
                            <transformers>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>com.shelson.console.CurrencyConverterConsoleApp</mainClass>
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
EOL

# Run mvn dependency:go-offline to download dependencies
mvn dependency:go-offline

# Run the Maven command to compile the project and skip tests
mvn clean package -DskipTests

# Start the application
exec java -Djava.security.egd=file:/dev/./urandom -jar /app/target/${PROJECT_ID}-${PROJECT_VERSION}.jar
