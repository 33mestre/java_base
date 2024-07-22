@echo off

REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - MS Windows Version
REM Project changelog generation script
REM ---------------------------------
REM This script checks for Docker installation, ensures the Docker daemon
REM is running, creates a Docker network, packages the project into a Docker image,
REM and runs a Docker container to generate the project changelog.
REM ---------------------------------

echo ---------------------------------
echo     _____ __         __                    ______                          _
echo    / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
echo    \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
echo   ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
echo  /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
echo ---------------------------------
echo Currency Conversion API - Versão MS Windows
echo Script de geração de changelog do projeto
echo ---------------------------------

echo Verificando instalação do Docker...

REM ---------------------------------
REM Verifica se o Docker está instalado
REM ---------------------------------
docker --version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
  echo Docker não encontrado. Por favor, instale o Docker manualmente (https://docs.docker.com/desktop/install/windows-install/).
  exit /b 1
) ELSE (
  echo Docker previamente instalado.
)

echo Verificando se o Docker daemon está em execução...
docker info >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
  echo Docker daemon não está em execução. Por favor, inicie o Docker daemon.
  exit /b 1
)

echo Verificando containers existentes...

REM ---------------------------------
REM Verifica se o container changelog está rodando
REM ---------------------------------
docker ps -q -f name=changelog >nul 2>&1
IF %ERRORLEVEL% EQU 0 (
  echo Parando container changelog...
  docker stop changelog
)

REM ---------------------------------
REM Remove o container changelog se existir
REM ---------------------------------
docker ps -aq -f name=changelog >nul 2>&1
IF %ERRORLEVEL% EQU 0 (
  echo Removendo container changelog...
  docker rm changelog
)

REM ---------------------------------
REM Empacotando projeto Docker...
REM ---------------------------------
echo Empacotando projeto Docker...
docker build -t changelog -f ..\docker\changelog-docker.yml ..

REM ---------------------------------
REM Adicionando permissão ao script dentro do container...
REM ---------------------------------
echo Adicionando permissão ao script dentro do container...
docker run --rm -v %cd%:/app changelog chmod +x /app/sys/callChangeLog.sh

REM ---------------------------------
REM Executando projeto Docker na porta 4010...
REM ---------------------------------
echo Executando projeto Docker na porta 4010...
docker run --rm --name changelog --network shelson-network -v %cd%:/app -p 4010:4010 changelog
