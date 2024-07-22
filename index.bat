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

echo Select an option:
echo [1] - Server
echo [2] - Console
echo [3] - DB
echo [4] - Directory
echo [5] - Changelog

set /p option=Enter a number from 1 to 5:: 

if "%option%"=="1" (
    echo Running the Server script...
    call scripts\server.bat
) else if "%option%"=="2" (
    echo Running the Console script...
    call scripts\console.bat
) else if "%option%"=="3" (
    echo Running the DB script...
    call scripts\db.bat
) else if "%option%"=="4" (
    echo Running the Directory script...
    call scripts\directory.bat
) else if "%option%"=="5" (
    echo Running the Changelog script...
    call scripts\changelog.bat
) else (
    echo Invalid option. Please choose a number from 1 to 5.
)
