#!/bin/bash

# ---------------------------------
#    _____ __         __                    ______                          _
#   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
#   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
#  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
# /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
# ---------------------------------
# Currency Conversion API - Unix / Linux Version
# Project installation and execution script with i18n support
# ---------------------------------
# This script sets up and runs the Currency Conversion API application in a Docker environment.
# It performs the following steps:
# 1. Checks for the existence of the project.properties file.
# 2. Reads the i18n key from project.properties to determine the language.
# 3. Loads the corresponding internationalization file for messages.
# 4. Verifies Docker installation and installs it if necessary.
# 5. Ensures the Docker daemon is running.
# 6. Checks for and creates a Docker network if necessary.
# 7. Packages the project into a Docker image.
# 8. Runs a Docker container to execute the Currency Conversion API application.
# ---------------------------------
# Calls console-app-docker.yml Dockerfile
# ---------------------------------
# The console-app-docker.yml Dockerfile is responsible for:
# - Setting up a Docker environment to run a Java console application
# - Allowing the testing of the Currency Conversion API application.
# ---------------------------------

# ---------------------------------
# Function to read properties file
# ---------------------------------
function prop {
    grep "^${1}=" "$2" | cut -d'=' -f2
}

# ---------------------------------
# Check if project.properties exists
# ---------------------------------
if [ ! -f project.properties ]; then
    echo "Error: project.properties file not found."
    exit 1
fi

# ---------------------------------
# Read i18n value from project.properties
# ---------------------------------
i18n=$(prop 'i18n' project.properties)

# ---------------------------------
# Check if i18n is empty
# ---------------------------------
if [ -z "$i18n" ]; then
    echo "Error: i18n key not found in project.properties."
    exit 1
fi

# ---------------------------------
# Set path to the i18n properties file
# ---------------------------------
i18n_file="i18n/script_console_${i18n}.properties"

# ---------------------------------
# Check if the i18n properties file exists
# ---------------------------------
if [ ! -f "$i18n_file" ]; then
    echo "Error: Internationalization file $i18n_file not found."
    exit 1
fi

# ---------------------------------
# Read properties from the i18n file
# ---------------------------------
header=$(prop 'header' $i18n_file)
menu_title=$(prop 'menu_title' $i18n_file)
checking_docker=$(prop 'checking_docker' $i18n_file)
docker_not_found=$(prop 'docker_not_found' $i18n_file)
docker_installed=$(prop 'docker_installed' $i18n_file)
checking_daemon=$(prop 'checking_daemon' $i18n_file)
daemon_not_running=$(prop 'daemon_not_running' $i18n_file)
creating_network=$(prop 'creating_network' $i18n_file)
network_exists=$(prop 'network_exists' $i18n_file)
packaging_project=$(prop 'packaging_project' $i18n_file)
running_project=$(prop 'running_project' $i18n_file)

clear

cat << "EOF"
---------------------------------
    _____ __         __                    ______                          _
   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ \`/ ___/ /
  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
 /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
EOF

echo "$header"
echo "$menu_title"
echo "---------------------------------"

echo "$checking_docker"

cd "$(dirname "$0")/.."

# ---------------------------------
# Check if Docker is installed
# ---------------------------------
if ! [ -x "$(command -v docker)" ]; then
  echo "$docker_not_found"
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
  echo "$docker_installed"
else
  echo "$docker_installed"
fi

# ---------------------------------
# Check if Docker daemon is running
# ---------------------------------
echo "$checking_daemon"

if ! docker info > /dev/null 2>&1; then
  echo "$daemon_not_running"
  exit 1
fi

# ---------------------------------
# Check if the Docker network already exists
# ---------------------------------
if ! docker network inspect shelson-network > /dev/null 2>&1; then
    echo "$creating_network"
    docker network create shelson-network
else
    echo "$network_exists"
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

# ---------------------------------
# Packaging Docker project...
# ---------------------------------
echo "$packaging_project"
docker build -t console-docker -f console-app-docker.yml .

# ---------------------------------
# Running Docker project...
# ---------------------------------
echo "$running_project"
docker run -it --rm --name console-docker --network shelson-network console-docker
