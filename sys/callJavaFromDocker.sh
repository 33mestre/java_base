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

# Load variables from the .env file
. .env
. project.properties

# Start the application
echo "The project version is $PROJECT_VERSION"
exec java -Djava.security.egd=file:/dev/./urandom -jar /app/target/$PROJECT_ID-$PROJECT_VERSION.jar
