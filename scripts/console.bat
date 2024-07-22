@echo off
REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - Windows Version
REM Project installation and execution script
REM ---------------------------------
REM This script checks for Docker installation, installs it if necessary,
REM creates a Docker network, packages, and runs the Docker project.
REM ---------------------------------

echo.
echo ---------------------------------
echo    _____ __         __                    ______                          _
echo   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
echo   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
echo  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
echo /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
echo.
echo ---------------------------------
echo Currency Conversion API - Windows Version
echo Project installation and execution script
echo ---------------------------------

echo Checking Docker installation...

REM Navigate to the project root directory
cd /d "%~dp0\.."

REM ---------------------------------
REM Check if Docker is installed
REM ---------------------------------
where docker > nul
if %errorlevel% neq 0 (
  echo Docker not found. Installing Docker...
  echo Unsupported operating system for automatic Docker installation. Please install Docker manually.
  exit /b 1
) else (
  echo Docker previously installed.
)

REM ---------------------------------
REM Check if Docker daemon is running
REM ---------------------------------
echo Checking if Docker daemon is running...
docker info > nul 2>&1
if %errorlevel% neq 0 (
  echo Docker daemon is not running. Please start the Docker daemon.
  exit /b 1
)

REM ---------------------------------
REM Check if the Docker network already exists
REM ---------------------------------
docker network inspect shelson-network > nul 2>&1
if %errorlevel% neq 0 (
  echo Creating and Starting a Docker Network...
  docker network create shelson-network
) else (
  echo Docker network 'shelson-network' already exists.
)

echo Checking existing containers...

REM ---------------------------------
REM Check if the console-docker container is running
REM ---------------------------------
for /f "tokens=*" %%i in ('docker ps -q -f name=console-docker') do set CONSOLE_DOCKER_RUNNING=%%i
if defined CONSOLE_DOCKER_RUNNING (
  echo Stopping console-docker container...
  docker stop console-docker
)

REM ---------------------------------
REM Remove the console-docker container if it exists
REM ---------------------------------
for /f "tokens=*" %%i in ('docker ps -aq -f name=console-docker') do set CONSOLE_DOCKER_EXISTS=%%i
if defined CONSOLE_DOCKER_EXISTS (
  echo Removing console-docker container...
  docker rm console-docker
)

REM ---------------------------------
REM Packaging Docker project...
REM ---------------------------------
echo Packaging Docker project...
docker build -t console-docker -f console-app-docker.yml .

REM ---------------------------------
REM Running Docker project...
REM ---------------------------------
echo Running Docker project...
docker run -it --rm --name console-docker --network shelson-network console-docker
