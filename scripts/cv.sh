#!/bin/bash

# ---------------------------------
#    _____ __         __                    ______                          _
#   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
#   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
#  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
# /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
# ---------------------------------
# Currency Conversion API - Unix / Linux Version
# Project cv generation script with i18n support
# ---------------------------------
# This script generates the project's cv by setting up a Docker environment.
# It performs the following steps:
# 1. Checks for the existence of the project.properties file.
# 2. Reads the i18n key from project.properties to determine the language.
# 3. Loads the corresponding internationalization file for messages.
# 4. Verifies Docker installation and ensures the Docker daemon is running.
# 5. Checks for and creates a Docker network if necessary.
# 6. Packages the project into a Docker image.
# 7. Runs a Docker container to generate the project cv.
# ---------------------------------
# Calls cv.yml Dockerfile
# ---------------------------------
# The cv.yml Dockerfile is responsible for:
# - Setting up a Docker environment to run a Groovy application
# - For the purpose of generating the project's cv.
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
i18n_file="i18n/script_cv_${i18n}.properties"

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
header=$(prop 'header' "$i18n_file")
menu_title=$(prop 'menu_title' "$i18n_file")
checking_docker=$(prop 'checking_docker' "$i18n_file")
docker_not_found=$(prop 'docker_not_found' "$i18n_file")
docker_installed=$(prop 'docker_installed' "$i18n_file")
checking_daemon=$(prop 'checking_daemon' "$i18n_file")
daemon_not_running=$(prop 'daemon_not_running' "$i18n_file")
stopping_container=$(prop 'stopping_container' "$i18n_file")
removing_container=$(prop 'removing_container' "$i18n_file")
creating_network=$(prop 'creating_network' "$i18n_file")
network_exists=$(prop 'network_exists' "$i18n_file")
packaging_project=$(prop 'packaging_project' "$i18n_file")
adding_permission=$(prop 'adding_permission' "$i18n_file")
running_project=$(prop 'running_project' "$i18n_file")

clear

cat << "EOF"
---------------------------------
   _____ __         __                    ______                          _
  / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
  \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
 ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
/____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
---------------------------------
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
  exit 1
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
# Check if the cv container is running
# ---------------------------------
if [ "$(docker ps -q -f name=cv)" ]; then
  echo "$stopping_container"
  docker stop cv
fi

# ---------------------------------
# Remove the cv container if it exists
# ---------------------------------
if [ "$(docker ps -aq -f name=cv)" ]; then
  echo "$removing_container"
  docker rm cv
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

# ---------------------------------
# Packaging Docker project...
# ---------------------------------
echo "$packaging_project"
docker build --no-cache -t cv -f cv.yml .

# ---------------------------------
# Adding permission to the script inside the container
# ---------------------------------
echo "$adding_permission"
docker run --rm -v "$(pwd)":/app cv chmod +x /app/sys/callCv.sh

# ---------------------------------
# Running Docker project on port 4014...
# ---------------------------------
echo "$running_project"
docker run --rm --name cv --network shelson-network -v "$(pwd)":/app -p 4014:4014 cv
