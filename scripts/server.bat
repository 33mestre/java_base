@echo off
REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - Unix / Linux Version
REM Server Installation and Execution Script
REM - Java Spring Server - port 8080
REM - H2 DB Server - port 8092
REM ---------------------------------
REM This script sets up and runs the server environment for the Currency Conversion API project in a Docker container.
REM It performs the following steps:
REM 1. Checks for the existence of the project.properties file.
REM 2. Reads the i18n key from project.properties to determine the language.
REM 3. Loads the corresponding internationalization file for messages.
REM 4. Verifies Docker installation and installs it if necessary.
REM 5. Ensures the Docker daemon is running.
REM 6. Checks for and creates a Docker network if necessary.
REM 7. Stops and removes any existing Docker container named "shelson-app".
REM 8. Packages the project into a Docker image using Dockerfile.
REM 9. Runs the Docker container with the server on port 8080.
REM ---------------------------------

REM Check if project.properties exists
if not exist project.properties (
    echo Error: project.properties file not found.
    exit /b 1
)

REM Function to read properties file
for /f "tokens=1,2 delims==" %%a in (project.properties) do (
    if "%%a"=="i18n" set i18n=%%b
)

REM Read i18n value from project.properties
if "%i18n%"=="" (
    echo Error: i18n key not found in project.properties.
    exit /b 1
)

REM Set path to the i18n properties file
set i18n_file=i18n\script_server_%i18n%.properties

REM Check if the i18n properties file exists
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

REM Check if Docker is installed
where docker > nul
if %errorlevel% neq 0 (
    echo %docker_not_found%
    REM Docker installation
    REM TODO: Add Docker installation commands for Windows if needed
    echo Unsupported operating system. Please install Docker manually.
    exit /b 1
) else (
    echo %docker_installed%
)

echo %checking_daemon%

REM Check if Docker daemon is running
docker info > nul 2>&1
if %errorlevel% neq 0 (
    echo %daemon_not_running%
    exit /b 1
)

echo Checking existing containers...

REM Check if the Docker network already exists
docker network inspect shelson-network > nul 2>&1
if %errorlevel% neq 0 (
    echo %creating_network%
    docker network create shelson-network
) else (
    echo %network_exists%
)

REM Check if the shelson-app container is running
for /f "tokens=*" %%i in ('docker ps -q -f name=shelson-app') do set SHELSON_APP_RUNNING=%%i
if defined SHELSON_APP_RUNNING (
    echo %stopping_container%
    docker stop shelson-app
)

REM Remove the shelson-app container if it exists
for /f "tokens=*" %%i in ('docker ps -aq -f name=shelson-app') do set SHELSON_APP_EXISTS=%%i
if defined SHELSON_APP_EXISTS (
    echo %removing_container%
    docker rm shelson-app
)

echo %packaging_project%
docker build -t shelson-app -f Dockerfile .

echo %running_project%
docker run --rm --name shelson-app --network shelson-network -p 8080:8080 shelson-app
