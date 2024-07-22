#!/bin/bash

# ---------------------------------
#    _____ __         __                    ______                          _
#   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
#   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
#  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
# /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
# ---------------------------------
# Currency Conversion API - Unix / Linux Version
# Project changelog generation script
# ---------------------------------
# This script is executed by the Docker change log.
# This script checks for Docker installation, ensures the Docker daemon
# is running, creates a Docker network, packages the project into a Docker image,
# and runs a Docker container to generate the project changelog.
# ---------------------------------

cat << "EOF"
---------------------------------
    _____ __         __                    ______                          _
   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ \`/ ___/ /
  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
 /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
---------------------------------
Currency Conversion API - Unix / Linux Version
Project changelog generation script
---------------------------------
EOF

echo "Checking Docker installation..."

cd "$(dirname "$0")/.."

# ---------------------------------
# Check if Docker is installed
# ---------------------------------
if ! [ -x "$(command -v docker)" ]; then
  echo "Docker not found. Please install Docker manually (https://docs.docker.com/desktop/install/windows-install/)."
  exit 1
else
  echo "Docker previously installed."
fi

# ---------------------------------
# Check if Docker daemon is running
# ---------------------------------
echo "Checking if Docker daemon is running..."

if ! docker info > /dev/null 2>&1; then
  echo "Docker daemon is not running. Please start the Docker daemon."
  exit 1
fi

# ---------------------------------
# Check if the changelog container is running
# ---------------------------------
if [ "$(docker ps -q -f name=changelog)" ]; then
  echo "Stopping changelog container..."
  docker stop changelog
fi

# ---------------------------------
# Remove the changelog container if it exists
# ---------------------------------
if [ "$(docker ps -aq -f name=changelog)" ]; then
  echo "Removing changelog container..."
  docker rm changelog
fi

# ---------------------------------
# Check if the Docker network already exists
# ---------------------------------
if ! docker network inspect shelson-network > /dev/null 2>&1; then
    echo "Creating and Starting a Docker Network..."
    docker network create shelson-network
else
    echo "Docker network 'shelson-network' already exists."
fi

# ---------------------------------
# Packaging Docker project...
# ---------------------------------
echo "Packaging Docker project..."
docker build --no-cache -t changelog -f changelog-docker.yml .

# ---------------------------------
# Adding permission to the script inside the container
# ---------------------------------
docker run --rm -v $(pwd):/app changelog chmod +x /app/sys/callChangeLog.sh

# ---------------------------------
# Running Docker project on port 4010...
# ---------------------------------
echo "Running Docker project on port 4010..."
docker run --rm --name changelog --network shelson-network -v $(pwd):/app -p 4010:4010 changelog
