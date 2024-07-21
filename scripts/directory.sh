#!/bin/bash

# ---------------------------------
#    _____ __         __                    ______                          _
#   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
#   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
#  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
# /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
# ---------------------------------
# Currency Conversion API - Unix / Linux Version
# Script to generate the DIRECTORY.md file
# ---------------------------------

cat << "EOF"
---------------------------------
    _____ __         __                    ______                          _
   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
 /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
---------------------------------
Currency Conversion API - Unix / Linux Version
Script to generate the DIRECTORY.md file
---------------------------------
EOF

echo "Checking Docker installation..."

# ---------------------------------
# Check if Docker is installed
# ---------------------------------
if ! [ -x "$(command -v docker)" ]; then
  echo "Docker not found. Please install Docker manually (https://docs.docker.com/desktop/install/linux-install/)."
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
# Check if the directory container is running
# ---------------------------------
if [ "$(docker ps -q -f name=directory)" ]; then
  echo "Stopping directory container..."
  docker stop directory
fi

# ---------------------------------
# Remove the directory container if it exists
# ---------------------------------
if [ "$(docker ps -aq -f name=directory)" ]; then
  echo "Removing directory container..."
  docker rm directory
fi

# ---------------------------------
# Packaging Docker project...
# ---------------------------------
echo "Packaging Docker project..."
docker build -t directory -f ../directory-docker.yml ..

# ---------------------------------
# Adding permission to the script inside the container
# ---------------------------------
docker run --rm -v $(pwd):/app directory chmod +x /app/sys/callDirectory.sh

# ---------------------------------
# Running Docker project on port 4001...
# ---------------------------------
echo "Running Docker project on port 4001..."
docker run --rm --name directory --network sognisport-network -v $(pwd):/app -p 4001:4001 directory
