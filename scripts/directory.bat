@echo off
REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - Unix / Linux Version
REM Script to generate the DIRECTORY.md file with i18n support
REM ---------------------------------
REM This script sets up and runs the generation of the DIRECTORY.md file for the Currency Conversion API project in a Docker environment.
REM It performs the following steps:
REM 1. Checks for the existence of the project.properties file.
REM 2. Reads the i18n key from project.properties to determine the language.
REM 3. Loads the corresponding internationalization file for messages.
REM 4. Verifies Docker installation and exits if not found.
REM 5. Ensures the Docker daemon is running.
REM 6. Checks for and creates a Docker network if necessary.
REM 7. Stops and removes any existing Docker container named "directory".
REM 8. Packages the project into a Docker image using directory-docker.yml.
REM 9. Adds execution permission to the script inside the container.
REM 10. Runs a Docker container to generate the DIRECTORY.md file.
REM ---------------------------------
REM Calls directory-docker.yml Dockerfile
REM ---------------------------------
REM The directory-docker.yml Dockerfile is responsible for:
REM - Generating the project DIRECTORY.md
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
set i18n_file=i18n\script_directory_%i18n%.properties

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

REM Check if the directory container is running
for /f "tokens=*" %%i in ('docker ps -q -f name=directory') do set DIRECTORY_RUNNING=%%i
if defined DIRECTORY_RUNNING (
    echo %stopping_container%
    docker stop directory
)

REM Remove the directory container if it exists
for /f "tokens=*" %%i in ('docker ps -aq -f name=directory') do set DIRECTORY_EXISTS=%%i
if defined DIRECTORY_EXISTS (
    echo %removing_container%
    docker rm directory
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

REM Packaging Docker project...
echo %packaging_project%
docker build -t directory -f directory-docker.yml .

REM Adding permission to the script inside the container
docker run --rm -v %cd%:/app directory chmod +x /app/sys/callDirectory.bat

REM Running Docker project on port 4001...
echo %running_project%
docker run --rm --name directory --network shelson-network -v %cd%:/app -p 4001:4001 directory
