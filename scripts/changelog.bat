@echo off

REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - Unix / Linux Version
REM Project changelog generation script with i18n support
REM ---------------------------------
REM This script generates the project's changelog by setting up a Docker environment.
REM It performs the following steps:
REM 1. Checks for the existence of the project.properties file.
REM 2. Reads the i18n key from project.properties to determine the language.
REM 3. Loads the corresponding internationalization file for messages.
REM 4. Verifies Docker installation and ensures the Docker daemon is running.
REM 5. Checks for and creates a Docker network if necessary.
REM 6. Packages the project into a Docker image.
REM 7. Runs a Docker container to generate the project changelog.
REM ---------------------------------
REM Calls changelog-docker.yml Dockerfile
REM ---------------------------------
REM The changelog-docker.yml Dockerfile is responsible for:
REM - Setting up a Docker environment to run a Groovy application
REM - For the purpose of generating the project's changelog.
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
set i18n=%i18n%
set i18n_file=i18n\script_change_log_%i18n%.properties

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

echo.
echo ---------------------------------
echo    _____ __         __                    ______                          _
echo   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
echo   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
echo  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
echo /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
echo ---------------------------------
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
REM Check if the changelog container is running
REM ---------------------------------
for /f "tokens=*" %%i in ('docker ps -q -f name=changelog') do set CHANGELOG_RUNNING=%%i
if defined CHANGELOG_RUNNING (
    echo %stopping_container%
    docker stop changelog
)

REM ---------------------------------
REM Remove the changelog container if it exists
REM ---------------------------------
for /f "tokens=*" %%i in ('docker ps -aq -f name=changelog') do set CHANGELOG_EXISTS=%%i
if defined CHANGELOG_EXISTS (
    echo %removing_container%
    docker rm changelog
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

REM ---------------------------------
REM Packaging Docker project...
REM ---------------------------------
echo %packaging_project%
docker build --no-cache -t changelog -f changelog-docker.yml .

REM ---------------------------------
REM Adding permission to the script inside the container
REM ---------------------------------
echo %adding_permission%
docker run --rm -v %cd%:/app changelog chmod +x /app/sys/callChangeLog.bat

REM ---------------------------------
REM Running Docker project on port 4010...
REM ---------------------------------
echo %running_project%
docker run --rm --name changelog --network shelson-network -v %cd%:/app -p 4010:4010 changelog
