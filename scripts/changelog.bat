@echo off

REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - Windows Version
REM Project changelog generation script
REM ---------------------------------
REM This script is executed by the Docker change log.
REM This script checks for Docker installation, ensures the Docker daemon
REM is running, creates a Docker network, packages the project into a Docker image,
REM and runs a Docker container to generate the project changelog.
REM ---------------------------------

echo ---------------------------------
echo    _____ __         __                    ______                          _
echo   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
echo   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
echo  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
echo /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
echo ---------------------------------
echo Currency Conversion API - Windows Version
echo Project changelog generation script
echo ---------------------------------

echo Checking Docker installation...

REM Navigate to the project root directory
cd /d "%~dp0\.."

REM ---------------------------------
REM Check if Docker is installed
REM ---------------------------------
where docker > nul
if %errorlevel% neq 0 (
  echo Docker not found. Please install Docker manually (https://docs.docker.com/desktop/install/windows-install/).
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
REM Check if the changelog container is running
REM ---------------------------------
for /f "tokens=*" %%i in ('docker ps -q -f name=changelog') do set CHANGELOG_RUNNING=%%i
if defined CHANGELOG_RUNNING (
  echo Stopping changelog container...
  docker stop changelog
)

REM ---------------------------------
REM Remove the changelog container if it exists
REM ---------------------------------
for /f "tokens=*" %%i in ('docker ps -aq -f name=changelog') do set CHANGELOG_EXISTS=%%i
if defined CHANGELOG_EXISTS (
  echo Removing changelog container...
  docker rm changelog
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

REM ---------------------------------
REM Packaging Docker project...
REM ---------------------------------
echo Packaging Docker project...
docker build --no-cache -t changelog -f changelog-docker.yml .

REM ---------------------------------
REM Adding permission to the script inside the container
REM ---------------------------------
docker run --rm -v %cd%:/app changelog chmod +x /app/sys/callChangeLog.bat

REM ---------------------------------
REM Running Docker project on port 4010...
REM ---------------------------------
echo Running Docker project on port 4010...
docker run --rm --name changelog --network shelson-network -v %cd%:/app -p 4010:4010 changelog
