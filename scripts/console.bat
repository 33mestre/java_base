@echo off
REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - Unix / Linux Version
REM Project installation and execution script with i18n support
REM ---------------------------------
REM This script sets up and runs the Currency Conversion API application in a Docker environment.
REM It performs the following steps:
REM 1. Checks for the existence of the project.properties file.
REM 2. Reads the i18n key from project.properties to determine the language.
REM 3. Loads the corresponding internationalization file for messages.
REM 4. Verifies Docker installation and installs it if necessary.
REM 5. Ensures the Docker daemon is running.
REM 6. Checks for and creates a Docker network if necessary.
REM 7. Packages the project into a Docker image.
REM 8. Runs a Docker container to execute the Currency Conversion API application.
REM ---------------------------------
REM Calls console-app-docker.yml Dockerfile
REM ---------------------------------
REM The console-app-docker.yml Dockerfile is responsible for:
REM - Setting up a Docker environment to run a Java console application
REM - Allowing the testing of the Currency Conversion API application.
REM ---------------------------------

REM ---------------------------------
REM Check if project.properties exists
REM ---------------------------------
if not exist "project.properties" (
    echo Error: project.properties file not found.
    exit /b 1
)

REM Function to read properties from the file
for /f "tokens=1,2 delims==" %%a in (project.properties) do (
    set "%%a=%%b"
)

REM ---------------------------------
REM Read i18n value from project.properties
REM ---------------------------------
if "%i18n%"=="" (
    echo Error: i18n key not found in project.properties.
    exit /b 1
)

REM ---------------------------------
REM Set path to the i18n properties file
REM ---------------------------------
set i18n_file=i18n\script_console_%i18n%.properties

REM ---------------------------------
REM Check if the i18n properties file exists
REM ---------------------------------
if not exist "%i18n_file%" (
    echo Error: Internationalization file %i18n_file% not found.
    exit /b 1
)

REM Read properties from the i18n file
for /f "tokens=1,2 delims==" %%a in (%i18n_file%) do (
    set "%%a=%%b"
)

cls

echo ---------------------------------
echo    _____ __         __                    ______                          _
echo   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
echo   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
echo  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
echo /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/

echo %header_windows%
echo %menu_title%
echo ---------------------------------

echo %checking_docker%

cd /d "%~dp0\.."

REM ---------------------------------
REM Check if Docker is installed
REM ---------------------------------
where docker > nul
if %errorlevel% neq 0 (
    echo %docker_not_found%
    REM Docker installation
    echo Unsupported operating system. Please install Docker manually.
    exit /b 1
) else (
    echo %docker_installed%
)

REM ---------------------------------
REM Check if Docker daemon is running
REM ---------------------------------
echo %checking_daemon%
docker info > nul 2>&1
if %errorlevel% neq 0 (
    echo %daemon_not_running%
    exit /b 1
)

REM ---------------------------------
REM Check if the Docker network already exists
REM ---------------------------------
docker network inspect shelson-network > nul 2>&1
if %errorlevel% neq 0 (
    echo %creating_network%
    docker network create shelson-network
) else (
    echo %network_exists%
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
echo %packaging_project%
docker build -t console-docker -f console-app-docker.yml .

REM ---------------------------------
REM Running Docker project...
REM ---------------------------------
echo %running_project%
docker run -it --rm --name console-docker --network shelson-network console-docker
