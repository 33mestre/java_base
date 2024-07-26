@echo off
REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - Windows Version
REM Project cv generation script with i18n support
REM ---------------------------------
REM This script generates the project's cv by setting up a Docker environment.
REM It performs the following steps:
REM 1. Checks for the existence of the project.properties file.
REM 2. Reads the i18n key from project.properties to determine the language.
REM 3. Loads the corresponding internationalization file for messages.
REM 4. Verifies Docker installation and ensures the Docker daemon is running.
REM 5. Checks for and creates a Docker network if necessary.
REM 6. Packages the project into a Docker image.
REM 7. Runs a Docker container to generate the project cv.
REM ---------------------------------
REM Calls cv.yml Dockerfile
REM ---------------------------------
REM The cv.yml Dockerfile is responsible for:
REM - Setting up a Docker environment to run a Groovy application
REM - For the purpose of generating the project's cv.
REM ---------------------------------

REM ---------------------------------
REM Function to read properties file
REM ---------------------------------
setlocal EnableDelayedExpansion
set "prop_file=project.properties"

for /F "tokens=1,* delims==" %%A in ('findstr /R /N "^" %prop_file% ^| findstr /R /N "^%1="') do set "prop_value=%%B"

REM ---------------------------------
REM Check if project.properties exists
REM ---------------------------------
if not exist project.properties (
    echo Error: project.properties file not found.
    exit /b 1
)

REM ---------------------------------
REM Read i18n value from project.properties
REM ---------------------------------
call :prop i18n
set "i18n=%prop_value%"

REM ---------------------------------
REM Check if i18n is empty
REM ---------------------------------
if "%i18n%"=="" (
    echo Error: i18n key not found in project.properties.
    exit /b 1
)

REM ---------------------------------
REM Set path to the i18n properties file
REM ---------------------------------
set "i18n_file=i18n\script_cv_%i18n%.properties"

REM ---------------------------------
REM Check if the i18n properties file exists
REM ---------------------------------
if not exist "%i18n_file%" (
    echo Error: Internationalization file %i18n_file% not found.
    exit /b 1
)

REM ---------------------------------
REM Read properties from the i18n file
REM ---------------------------------
call :prop header
set "header=%prop_value%"
call :prop menu_title
set "menu_title=%prop_value%"
call :prop checking_docker
set "checking_docker=%prop_value%"
call :prop docker_not_found
set "docker_not_found=%prop_value%"
call :prop docker_installed
set "docker_installed=%prop_value%"
call :prop checking_daemon
set "checking_daemon=%prop_value%"
call :prop daemon_not_running
set "daemon_not_running=%prop_value%"
call :prop stopping_container
set "stopping_container=%prop_value%"
call :prop removing_container
set "removing_container=%prop_value%"
call :prop creating_network
set "creating_network=%prop_value%"
call :prop network_exists
set "network_exists=%prop_value%"
call :prop packaging_project
set "packaging_project=%prop_value%"
call :prop adding_permission
set "adding_permission=%prop_value%"
call :prop running_project
set "running_project=%prop_value%"

cls

echo ---------------------------------
echo    _____ __         __                    ______                          _
echo   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
echo   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
echo  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
echo /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
echo ---------------------------------
echo %header%
echo %menu_title%
echo ---------------------------------

echo %checking_docker%

cd /d %~dp0\..

REM ---------------------------------
REM Check if Docker is installed
REM ---------------------------------
where docker >nul 2>&1
if errorlevel 1 (
    echo %docker_not_found%
    exit /b 1
) else (
    echo %docker_installed%
)

REM ---------------------------------
REM Check if Docker daemon is running
REM ---------------------------------
echo %checking_daemon%

docker info >nul 2>&1
if errorlevel 1 (
    echo %daemon_not_running%
    exit /b 1
)

REM ---------------------------------
REM Check if the cv container is running
REM ---------------------------------
docker ps -q -f name=cv >nul 2>&1
if not errorlevel 1 (
    echo %stopping_container%
    docker stop cv
)

REM ---------------------------------
REM Remove the cv container if it exists
REM ---------------------------------
docker ps -aq -f name=cv >nul 2>&1
if not errorlevel 1 (
    echo %removing_container%
    docker rm cv
)

REM ---------------------------------
REM Check if the Docker network already exists
REM ---------------------------------
docker network inspect shelson-network >nul 2>&1
if errorlevel 1 (
    echo %creating_network%
    docker network create shelson-network
) else (
    echo %network_exists%
)

REM ---------------------------------
REM Packaging Docker project...
REM ---------------------------------
echo %packaging_project%
docker build --no-cache -t cv -f cv.yml .

REM ---------------------------------
REM Adding permission to the script inside the container
REM ---------------------------------
echo %adding_permission%
docker run --rm -v "%cd%":/app cv chmod +x /app/sys/callCv.sh

REM ---------------------------------
REM Running Docker project on port 4014...
REM ---------------------------------
echo %running_project%
docker run --rm --name cv --network shelson-network -v "%cd%":/app -p 4014:4014 cv

goto :eof

:prop
setlocal EnableDelayedExpansion
set "prop_file=%i18n_file%"

for /F "tokens=1,* delims==" %%A in ('findstr /R /N "^" !prop_file! ^| findstr /R /N "^%1="') do set "prop_value=%%B"
endlocal & set "prop_value=%prop_value%"
goto :eof
