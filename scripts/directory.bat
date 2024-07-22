@echo off
REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - Windows Version
REM Script to generate the DIRECTORY.md file
REM ---------------------------------

echo ---------------------------------
echo    _____ __         __                    ______                          _
echo   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
echo   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
echo  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
echo /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
echo ---------------------------------
echo Currency Conversion API - Windows Version
echo Script to generate the DIRECTORY.md file
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
REM Check if the directory container is running
REM ---------------------------------
for /f "tokens=*" %%i in ('docker ps -q -f name=directory') do set DIRECTORY_RUNNING=%%i
if defined DIRECTORY_RUNNING (
  echo Stopping directory container...
  docker stop directory
)

REM ---------------------------------
REM Remove the directory container if it exists
REM ---------------------------------
for /f "tokens=*" %%i in ('docker ps -aq -f name=directory') do set DIRECTORY_EXISTS=%%i
if defined DIRECTORY_EXISTS (
  echo Removing directory container...
  docker rm directory
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
REM Packaging Docker project...
REM ---------------------------------
echo Packaging Docker project...
docker build -t directory -f directory-docker.yml .

REM ---------------------------------
REM Adding permission to the script inside the container
REM ---------------------------------
docker run --rm -v "%cd%":/app directory chmod +x /app/sys/callDirectory.sh

REM ---------------------------------
REM Running Docker project on port 4001...
REM ---------------------------------
echo Running Docker project on port 4001...
docker run --rm --name directory --network shelson-network -v "%cd%":/app -p 4001:4001 directory
