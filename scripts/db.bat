@echo off
REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - Unix / Linux Version
REM DB Tester Installation and Execution Script with i18n support
REM ---------------------------------
REM This script sets up and runs the DB Tester for the Currency Conversion API application in a Docker environment.
REM It performs the following steps:
REM 1. Checks for the existence of the project.properties file.
REM 2. Reads the i18n key from project.properties to determine the language.
REM 3. Loads the corresponding internationalization file for messages.
REM 4. Verifies Docker installation and installs it if necessary.
REM 5. Ensures the Docker daemon is running.
REM 6. Checks for and creates a Docker network if necessary.
REM 7. Packages the DB Tester project into a Docker image.
REM 8. Runs a Docker container to execute the DB Tester, which:
REM    - Installs, configures, and starts the H2 Database server.
REM    - Runs a Java program that performs a SELECT query on the CURRENCY_CONVERSIONS table in the H2 database.
REM ---------------------------------
REM Calls db-docker.yml Dockerfile
REM ---------------------------------
REM The db-docker.yml Dockerfile is responsible for:
REM - Installing, configuring, and starting the H2 Database server
REM - Running a Java program that performs a SELECT query on the CURRENCY_CONVERSIONS table in the H2 database.
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
set i18n_file=i18n\script_db_%i18n%.properties

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

echo %header%
echo %menu_title_windows%
echo ---------------------------------

echo %checking_docker%

cd /d "%~dp0\.."

REM Check if Docker is installed
where docker > nul
if %errorlevel% neq 0 (
    echo %docker_not_found%
    REM Docker installation
    echo Unsupported operating system. Please install Docker manually.
    exit /b 1
) else (
    echo %docker_installed%
)

REM Check if Docker daemon is running
echo %checking_daemon%
docker info > nul 2>&1
if %errorlevel% neq 0 (
    echo %daemon_not_running%
    exit /b 1
)

REM Check if the Docker network already exists
docker network inspect shelson-network > nul 2>&1
if %errorlevel% neq 0 (
    echo %creating_network%
    docker network create shelson-network
) else (
    echo %network_exists%
)

echo Checking existing containers...

REM Check if the db-docker container is running
for /f "tokens=*" %%i in ('docker ps -q -f name=db-docker') do set DB_DOCKER_RUNNING=%%i
if defined DB_DOCKER_RUNNING (
    echo Stopping db-docker container...
    docker stop db-docker
)

REM Remove the db-docker container if it exists
for /f "tokens=*" %%i in ('docker ps -aq -f name=db-docker') do set DB_DOCKER_EXISTS=%%i
if defined DB_DOCKER_EXISTS (
    echo Removing db-docker container...
    docker rm db-docker
)

REM Packaging Docker project...
echo %packaging_project%
docker build -t db-docker -f db-docker.yml .

REM Running Docker project on port 8083...
echo %running_project%
docker run --rm --name db-docker --network shelson-network -p 8083:8083 db-docker
