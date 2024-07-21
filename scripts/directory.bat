@echo off

REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - MS Windows Version
REM Script to generate the DIRECTORY.md file
REM ---------------------------------
REM This script checks for Docker installation, ensures the Docker daemon
REM is running, creates a Docker network, packages the project into a Docker image,
REM and runs a Docker container to generate the project's DIRECTORY.md file.
REM ---------------------------------

echo ---------------------------------
echo     _____ __         __                    ______                          _
echo    / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
echo    \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
echo   ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
echo  /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
echo ---------------------------------
echo Currency Conversion API - MS Windows Version
echo Script to generate the DIRECTORY.md file
echo ---------------------------------

echo Checking Docker installation...

REM ---------------------------------
REM Check if Docker is installed
REM ---------------------------------
docker --version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
  echo Docker not found. Please install Docker manually (https://docs.docker.com/desktop/install/windows-install/).
  exit /b 1
) ELSE (
  echo Docker previously installed.
)

echo Checking if Docker daemon is running...
docker info >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
  echo Docker daemon is not running. Please start the Docker daemon.
  exit /b 1
)

echo Checking existing containers...

REM ---------------------------------
REM Check if the directory container is running
REM ---------------------------------
docker ps -q -f name=directory >nul 2>&1
IF %ERRORLEVEL% EQU 0 (
  echo Stopping directory container...
  docker stop directory
)

REM ---------------------------------
REM Remove the directory container if it exists
REM ---------------------------------
docker ps -aq -f name=directory >nul 2>&1
IF %ERRORLEVEL% EQU 0 (
  echo Removing directory container...
  docker rm directory
)

REM ---------------------------------
REM Packaging Docker project...
REM ---------------------------------
echo Packaging Docker project...
docker build -t directory -f ..\docker\directory-docker.yml ..

REM ---------------------------------
REM Adding permission to the script inside the container...
REM ---------------------------------
echo Adding permission to the script inside the container...
docker run --rm -v %cd%:/app directory chmod +x /app/sys/callDirectory.sh

REM ---------------------------------
REM Running Docker project on port 4001...
REM ---------------------------------
echo Running Docker project on port 4001...
docker run --rm --name directory --network sognisport-network -v %cd%:/app -p 4001:4001 directory
