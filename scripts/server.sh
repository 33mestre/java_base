#!/bin/bash

# ---------------------------------
#    _____ __         __                    ______                          _
#   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
#   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
#  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
# /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
# ---------------------------------
# Currency Conversion API - Unix / Linux Version
# Server Installation and Execution Script
# - Java Spring Server - port 80
# - H2 DB Server - port 8092
# ---------------------------------
# This script checks for Docker installation, installs it if necessary,
# creates a Docker network, packages, and runs the Docker project.
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
Server Installation and Execution Script
- Java Spring Server - port 80
- H2 DB Server - port 8092
---------------------------------
EOF

clear

echo "Checking Docker installation..."

# ---------------------------------
# Check if Docker is installed
# ---------------------------------
if ! [ -x "$(command -v docker)" ]; then
  echo "Docker not found. Installing Docker..."
  # Docker installation
  if [ "$(uname)" == "Darwin" ]; then
    # If MacOS
    /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
    brew install --cask docker
  elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
    # If Linux
    sudo apt-get update
    sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
    sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
    sudo apt-get update
    sudo apt-get install -y docker-ce
    sudo systemctl start docker
    sudo systemctl enable docker
  else
    echo "Unsupported operating system. Please install Docker manually."
    exit 1
  fi
  echo "Docker installed successfully."
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
# Check if the Docker network already exists
# ---------------------------------
if ! docker network inspect sognisport-network > /dev/null 2>&1; then
    echo "Creating and Starting a Docker Network..."
    docker network create sognisport-network
else
    echo "Docker network 'sognisport-network' already exists."
fi

echo "Checking existing containers..."

# ---------------------------------
# Check if the sognisport-app container is running
# ---------------------------------
if [ "$(docker ps -q -f name=sognisport-app)" ]; then
  echo "Stopping sognisport-app container..."
  docker stop sognisport-app
fi

# ---------------------------------
# Remove the sognisport-app container if it exists
# ---------------------------------
if [ "$(docker ps -aq -f name=sognisport-app)" ]; then
  echo "Removing sognisport-app container..."
  docker rm sognisport-app
fi

# ---------------------------------
# Packaging Docker project...
# ---------------------------------
echo "Packaging Docker project..."
docker build -t sognisport-app -f ../Dockerfile ..

# ---------------------------------
# Running Docker project on port 8080...
# ---------------------------------
echo "Running Docker project on port 8080..."
docker run --rm --name sognisport-app --network sognisport-network -p 8080:8080 sognisport-app
