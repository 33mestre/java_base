@echo off

REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - MS Windows Version
REM DB Tester Installation and Execution Script
REM (SELECT CURRENCY_CONVERSIONS) from H2 DB
REM ---------------------------------

echo ---------------------------------
echo     _____ __         __                    ______                          _
echo    / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
echo    \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
echo   ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
echo /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
echo ---------------------------------
echo Currency Conversion API - MS Windows Version
echo DB Tester Installation and Execution Script
echo (SELECT CURRENCY_CONVERSIONS) from H2 DB
echo ---------------------------------

echo Checking Docker installation...

REM ---------------------------------
REM Check if Docker is installed
REM ---------------------------------
docker --version >nul 2>&1
if %errorlevel% neq 0 (
  echo Docker not found. Please install Docker manually (https://docs.docker.com/desktop/install/windows-install/).
  exit /b 1
) else (
  echo Docker previously installed.
)

echo Checking if Docker daemon is running...

REM ---------------------------------
REM Check if Docker daemon is running
REM ---------------------------------
docker info >nul 2>&1
if %errorlevel% neq 0 (
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
REM Check if the db-docker container is running
REM ---------------------------------
for /f "tokens=*" %%i in ('docker ps -q -f name=db-docker') do (
  if not "%%i"=="" (
    echo Stopping db-docker container...
    docker stop db-docker
  )
)

REM ---------------------------------
REM Remove the db-docker container if it exists
REM ---------------------------------
for /f "tokens=*" %%i in ('docker ps -aq -f name=db-docker') do (
  if not "%%i"=="" (
    echo Removing db-docker container...
    docker rm db-docker
  )
)

REM ---------------------------------
REM Packaging Docker project...
REM ---------------------------------
echo Packaging Docker project...
docker build -t db-docker -f ..\docker\db-docker.yml ..

REM ---------------------------------
REM Running Docker project on port 8083...
REM ---------------------------------
echo Running Docker project on port 8083...

echo ---------------------------------
echo.
echo SELECT * FROM CURRENCY_CONVERSIONS;
echo.
echo.
docker run --rm --name db-docker --network sognisport-network -p 8083:8083 db-docker
