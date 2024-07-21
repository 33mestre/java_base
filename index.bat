@echo off
REM ---------------------------------
REM Currency Conversion API - Windows Version
REM Console Terminal 
REM Main Menu
REM ---------------------------------

cls

echo ---------------------------------
echo    _____ __         __                    ______                          _
echo   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
echo   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
echo  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
echo /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
echo ---------------------------------
echo Currency Conversion API - Windows Version
echo Console Terminal 
echo Main Menu
echo ---------------------------------

echo Selecione uma opcao:
echo [1] - Server
echo [2] - Console
echo [3] - DB
echo [4] - Directory
echo [5] - Changelog

set /p option=Digite um numero de 1 a 5: 

if "%option%"=="1" (
    echo Executando o script Server...
    call scripts\server.bat
) else if "%option%"=="2" (
    echo Executando o script Console...
    call scripts\console.bat
) else if "%option%"=="3" (
    echo Executando o script DB...
    call scripts\db.bat
) else if "%option%"=="4" (
    echo Executando o script Directory...
    call scripts\directory.bat
) else if "%option%"=="5" (
    echo Executando o script Changelog...
    call scripts\changelog.bat
) else (
    echo Opcao invalida. Por favor, escolha um numero de 1 a 5.
)
