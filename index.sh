#!/bin/bash

# ---------------------------------
#    _____ __         __                    ______                          _
#   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
#   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
#  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
# /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
# ---------------------------------
# Currency Conversion API - Unix / Linux Version
# Console Terminal 
# Main Menu
# ---------------------------------
# This script provides a console terminal menu for managing the Currency Conversion API project.
# It performs the following steps:
# 1. Checks for the existence of the project.properties file.
# 2. Reads the i18n key from project.properties to determine the language.
# 3. Loads the corresponding internationalization file for messages.
# 4. Displays a menu with options to run various project scripts.
# 5. Verifies the existence of each script before attempting to run it.
# 6. Verifies the existence of properties in the i18n file.
# ---------------------------------
# Each option in the menu calls the corresponding script that manages the specific Docker container:
#
# - [1] Server: Java Spring Server on port 80 and H2 DB Server on port 8092.
# - [2] Console: Docker environment to run a Java console application, allowing testing of the Currency Conversion API application.
# - [3] DB: DB Tester Installation and Execution Script (SELECT CURRENCY_CONVERSIONS from H2 DB).
# - [4] Directory: Script to generate the DIRECTORY.md file.
# - [5] Changelog: Script that:
#   - Obtains the list of Git commits in the format: hash, date, author, and message.
#   - Defines and increments the project version according to the commits.
#   - Updates the 'CHANGELOG.md' file with a detailed log of all changes.
#   - Updates the '.env' file with the new project version.
#   - Ignores commits containing the keyword "AUTO_COMMIT".
# ---------------------------------

# Verifica se o arquivo project.properties existe
if [ ! -f project.properties ]; then
    echo "Error: project.properties file not found."
    exit 1
fi

# Lê o valor da chave i18n do arquivo project.properties
i18n=$(grep "^i18n=" project.properties | cut -d'=' -f2)
if [ -z "$i18n" ]; then
    echo "Error: i18n key not found in project.properties."
    exit 1
fi

# Define o caminho para o arquivo de internacionalização
i18n_file="i18n/script_index_$i18n.properties"

# Verifica se o arquivo de internacionalização existe
if [ ! -f "$i18n_file" ]; then
    echo "Error: Internationalization file $i18n_file not found."
    exit 1
fi

# Função para ler o arquivo de propriedades
function prop {
    grep "^${1}=" "$i18n_file" | cut -d'=' -f2
}

clear

cat << "EOF"
---------------------------------
    _____ __         __                    ______                          _
   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ \`/ ___/ /
  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
 /____/_/ /_/\___/_/____/\____/\____/  /_/    \___/_/  /_/   \__,_/_/  /_/
---------------------------------
EOF

echo "$(prop 'header')"
echo "$(prop 'menu_title')"
echo "---------------------------------"

echo "$(prop 'select_option')"
echo "$(prop 'blank_line')"
echo "$(prop 'server_option')"
echo "$(prop 'console_option')"
echo "$(prop 'db_option')"
echo "$(prop 'directory_option')"
echo "$(prop 'changelog_option')"

read -p "$(prop 'enter_number') " option

# Verifica se os scripts existem
function check_script {
    if [ ! -f "$1" ]; then
        echo "Error: $1 not found."
        exit 1
    fi
}

case $option in
    1)
        check_script "./scripts/server.sh"
        echo "$(prop 'running_server')"
        ./scripts/server.sh
        ;;
    2)
        check_script "./scripts/console.sh"
        echo "$(prop 'running_console')"
        ./scripts/console.sh
        ;;
    3)
        check_script "./scripts/db.sh"
        echo "$(prop 'running_db')"
        ./scripts/db.sh
        ;;
    4)
        check_script "./scripts/directory.sh"
        echo "$(prop 'running_directory')"
        ./scripts/directory.sh
        ;;
    5)
        check_script "./scripts/changelog.sh"
        echo "$(prop 'running_changelog')"
        ./scripts/changelog.sh
        ;;
    *)
        echo "$(prop 'invalid_option')"
        ;;
esac
