#!/bin/bash

# ---------------------------------
#    _____ __         __                    ______                          _
#   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
#   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
#  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
# /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
# ---------------------------------
# Currency Conversion API - Unix / Linux Version
# Project installation and execution script
# ---------------------------------
# This script checks for Docker installation, installs it if necessary,
# creates a Docker network, packages, e runs the Docker project.
# ---------------------------------

cat << "EOF"
---------------------------------
    _____ __         __                    ______                          _
   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ \`/ ___/ /
  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
 /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/

---------------------------------
Currency Conversion API - Unix / Linux Version
Project installation and execution script
---------------------------------
EOF

clear

echo "Checking Docker installation..."

cd "$(dirname "$0")/.."

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
if ! docker network inspect shelson-network > /dev/null 2>&1; then
    echo "Creating and Starting a Docker Network..."
    docker network create shelson-network
else
    echo "Docker network 'shelson-network' already exists."
fi

echo "Checking existing containers..."

# ---------------------------------
# Check if the console-docker container is running
# ---------------------------------
if [ "$(docker ps -q -f name=console-docker)" ]; then
  echo "Stopping console-docker container..."
  docker stop console-docker
fi

# ---------------------------------
# Remove the console-docker container if it exists
# ---------------------------------
if [ "$(docker ps -aq -f name=console-docker)" ]; then
  echo "Removing console-docker container..."
  docker rm console-docker
fi

# # ---------------------------------
# # Packaging Docker project...
# # ---------------------------------
# echo "Packaging Docker project..."
docker build -t console-docker -f console-app-docker.yml .

# # ---------------------------------
# # Running Docker project...
# # ---------------------------------
# echo "Running Docker project..."
docker run -it --rm --name console-docker --network shelson-network console-docker
