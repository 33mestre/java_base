@echo off

REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - MS Windows Version
REM Server Installation and Execution Script
REM - Java Spring Server - port 80
REM - H2 DB Server - port 8092
REM ---------------------------------
REM This script checks for Docker installation, installs it if necessary,
REM creates a Docker network, packages, and runs the Docker project.
REM ---------------------------------

echo.
echo ---------------------------------
echo      _____ __         __                    ______                          _
echo     / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
echo     \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
echo    ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
echo   /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
echo ---------------------------------
echo Currency Conversion API - MS Windows Version
echo Server Installation and Execution Script
echo - Java Spring Server - port 80
echo - H2 DB Server - port 8092
echo ---------------------------------
echo.

echo Checking Docker installation...

REM ---------------------------------
REM Check if Docker is installed
REM ---------------------------------
docker --version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo Docker not found. Installing Docker...
    powershell -Command "Start-Process powershell -ArgumentList 'Invoke-WebRequest -UseBasicParsing -Uri https://get.docker.com/ -OutFile install-docker.ps1; .\install-docker.ps1' -Verb RunAs"
    IF %ERRORLEVEL% NEQ 0 (
        echo Docker installation failed. Please install manually (https://docs.docker.com/desktop/install/windows-install/).
        exit /b 1
    )
    echo Docker installed successfully.
) ELSE (
    echo Docker previously installed.
)

echo Checking if Docker daemon is running...

REM ---------------------------------
REM Check if Docker daemon is running
REM ---------------------------------
docker info >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo Docker daemon is not running. Please start the Docker daemon.
    exit /b 1
)

REM ---------------------------------
REM Creating and Starting a Docker Network
REM ---------------------------------
echo Checking for existing Docker network...
docker network inspect sognisport-network >nul 2>nul
if %errorlevel% neq 0 (
    echo Network sognisport-network does not exist, creating...
    docker network create sognisport-network
) else (
    echo Network sognisport-network already exists, skipping creation.
)

echo Checking existing containers...

REM ---------------------------------
REM Check if the sognisport-app container is running
REM ---------------------------------
docker ps -q -f name=sognisport-app >nul 2>&1
IF NOT "%ERRORLEVEL%"=="0" (
    echo Stopping sognisport-app container...
    docker stop sognisport-app
)

REM ---------------------------------
REM Remove the sognisport-app container if it exists
REM ---------------------------------
docker ps -aq -f name=sognisport-app >nul 2>&1
IF NOT "%ERRORLEVEL%"=="0" (
    echo Removing sognisport-app container...
    docker rm sognisport-app
)

REM ---------------------------------
REM Packaging Docker project...
REM ---------------------------------
echo Packaging Docker project...
docker build -t sognisport-app -f ..\docker\Dockerfile ..

REM ---------------------------------
REM Running Docker project on port 8080...
REM ---------------------------------
echo Running Docker project on port 8080...
docker run --rm --name sognisport-app --network sognisport-network -p 8080:8080 sognisport-app
