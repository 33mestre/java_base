@echo off
REM ---------------------------------
REM    _____ __         __                    ______                          _
REM   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
REM   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
REM  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
REM /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
REM ---------------------------------
REM Currency Conversion API - Microsoft® Windows Version
REM Console Terminal 
REM Main Menu
REM ---------------------------------
REM This script provides a console terminal menu for managing the Currency Conversion API project.
REM It performs the following steps:
REM 1. Checks for the existence of the project.properties file.
REM 2. Reads the i18n key from project.properties to determine the language.
REM 3. Loads the corresponding internationalization file for messages.
REM 4. Displays a menu with options to run various project scripts.
REM 5. Verifies the existence of each script before attempting to run it.
REM 6. Verifies the existence of properties in the i18n file.
REM ---------------------------------
REM Each option in the menu calls the corresponding script that manages the specific Docker container:
REM
REM - [1] Server: Java Spring Server on port 80 and H2 DB Server on port 8092.
REM - [2] Console: Docker environment to run a Java console application, allowing testing of the Currency Conversion API application.
REM - [3] DB: DB Tester Installation and Execution Script (SELECT CURRENCY_CONVERSIONS from H2 DB).
REM - [4] Directory: Script to generate the DIRECTORY.md file.
REM - [5] Changelog: Script that:
REM   - Obtains the list of Git commits in the format: hash, date, author, and message.
REM   - Defines and increments the project version according to the commits.
REM   - Updates the 'CHANGELOG.md' file with a detailed log of all changes.
REM   - Updates the '.env' file with the new project version.
REM   - Ignores commits containing the keyword "AUTO_COMMIT".
REM ---------------------------------

REM Check if project.properties exists
if not exist project.properties (
    echo Error: project.properties file not found.
    exit /b 1
)

REM Read i18n value from project.properties
setlocal enabledelayedexpansion
for /f "tokens=1,2 delims==" %%a in (project.properties) do (
    if "%%a"=="i18n" set i18n=%%b
)

if "%i18n%"=="" (
    echo Error: i18n key not found in project.properties.
    exit /b 1
)

REM Set path to the i18n properties file
set i18n_file=i18n\script_index_%i18n%.properties

REM Check if the i18n properties file exists
if not exist "%i18n_file%" (
    echo Error: Internationalization file %i18n_file% not found.
    exit /b 1
)

REM Function to read properties from the i18n file
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
echo ---------------------------------

echo %header_windows%
echo %menu_title%
echo ---------------------------------

echo %select_option%
echo %blank_line%
echo %server_option%
echo %console_option%
echo %db_option%
echo %directory_option%
echo %changelog_option%

set /p option=%enter_number%

REM Function to check if a script exists
:check_script
if not exist "%1" (
    echo Error: %1 not found.
    exit /b 1
)

switch "%option%" (
    case "1":
        call :check_script scripts\server.bat
        echo %running_server%
        call scripts\server.bat
        exit /b
    case "2":
        call :check_script scripts\console.bat
        echo %running_console%
        call scripts\console.bat
        exit /b
    case "3":
        call :check_script scripts\db.bat
        echo %running_db%
        call scripts\db.bat
        exit /b
    case "4":
        call :check_script scripts\directory.bat
        echo %running_directory%
        call scripts\directory.bat
        exit /b
    case "5":
        call :check_script scripts\changelog.bat
        echo %running_changelog%
        call scripts\changelog.bat
        exit /b
    default:
        echo %invalid_option%
        exit /b
)
