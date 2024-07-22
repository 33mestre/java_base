#!/bin/bash

# ---------------------------------
#    _____ __         __                    ______                          _
#   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
#   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
#  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
# /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
# ---------------------------------
# Currency Conversion API - Unix / Linux Version
# DB Tester Installation and Execution Script
# (SELECT CURRENCY_CONVERSIONS) from H2 DB
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
DB Tester Installation and Execution Script
(SELECT CURRENCY_CONVERSIONS) from H2 DB
---------------------------------
EOF

echo "Checking Docker installation..."

# Check if Docker is installed
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

echo "Checking if Docker daemon is running..."

# Check if Docker daemon is running
if ! docker info > /dev/null 2>&1; then
  echo "Docker daemon is not running. Please start the Docker daemon."
  exit 1
fi

echo "Checking existing containers..."

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
# Check if the db-docker container is running
# ---------------------------------
if [ "$(docker ps -q -f name=db-docker)" ]; then
  echo "Stopping db-docker container..."
  docker stop db-docker
fi

# ---------------------------------
# Remove the db-docker container if it exists
# ---------------------------------
if [ "$(docker ps -aq -f name=db-docker)" ]; then
  echo "Removing db-docker container..."
  docker rm db-docker
fi

# ---------------------------------
# Packaging Docker project...
# ---------------------------------
echo "Packaging Docker project..."
docker build -t db-docker -f ../db-docker.yml ..

# ---------------------------------
# Running Docker project on port 8083...
# ---------------------------------
echo "Running Docker project on port 8083..."

echo "---------------------------------"
echo ""
echo "SELECT * FROM CURRENCY_CONVERSIONS;"
echo ""
echo ""

docker run --rm --name db-docker --network shelson-network -p 8083:8083 db-docker
