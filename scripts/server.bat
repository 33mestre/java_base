@echo off

REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - Windows Version
REM Server Installation and Execution Script
REM - Java Spring Server - port 80
REM - H2 DB Server - port 8092
REM ---------------------------------
REM This script checks for Docker installation, installs it if necessary,
REM creates a Docker network, packages, and runs the Docker project.
REM ---------------------------------

echo ---------------------------------
echo    _____ __         __                    ______                          _
echo   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
echo   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
echo  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
echo /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
echo ---------------------------------
echo Currency Conversion API - Windows Version
echo Server Installation and Execution Script
echo - Java Spring Server - port 80
echo - H2 DB Server - port 8092
echo ---------------------------------

echo Checking Docker installation...

cd /d "%~dp0\.."

REM ---------------------------------
REM Check if Docker is installed
REM ---------------------------------
where docker >nul 2>nul
IF %ERRORLEVEL% NEQ 0 (
    echo Docker not found. Installing Docker...
    IF "%OS%"=="Windows_NT" (
        powershell -Command "Start-Process 'https://desktop.docker.com/win/stable/Docker%20Desktop%20Installer.exe' -Verb RunAs"
        exit /b
    ) ELSE (
        echo Unsupported operating system. Please install Docker manually.
        exit /b 1
    )
) ELSE (
    echo Docker previously installed.
)

REM ---------------------------------
REM Check if Docker daemon is running
REM ---------------------------------
echo Checking if Docker daemon is running...
docker info >nul 2>nul
IF %ERRORLEVEL% NEQ 0 (
    echo Docker daemon is not running. Please start the Docker daemon.
    exit /b 1
)

REM ---------------------------------
REM Check if the Docker network already exists
REM ---------------------------------
docker network inspect shelson-network >nul 2>nul
IF %ERRORLEVEL% NEQ 0 (
    echo Creating and Starting a Docker Network...
    docker network create shelson-network
) ELSE (
    echo Docker network 'shelson-network' already exists.
)

echo Checking existing containers...

REM ---------------------------------
REM Check if the shelson-app container is running
REM ---------------------------------
docker ps -q -f name=shelson-app >nul
IF %ERRORLEVEL% EQU 0 (
    echo Stopping shelson-app container...
    docker stop shelson-app
)

REM ---------------------------------
REM Remove the shelson-app container if it exists
REM ---------------------------------
docker ps -aq -f name=shelson-app >nul
IF %ERRORLEVEL% EQU 0 (
    echo Removing shelson-app container...
    docker rm shelson-app
)

REM ---------------------------------
REM Packaging Docker project...
REM ---------------------------------
echo Packaging Docker project...
docker build -t shelson-app -f Dockerfile .

REM ---------------------------------
REM Running Docker project on port 8080...
REM ---------------------------------
echo Running Docker project on port 8080...
docker run --rm --name shelson-app --network shelson-network -p 8080:8080 shelson-app
