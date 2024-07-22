#!/bin/bash

# ---------------------------------
# Currency Conversion API - Unix / Linux Version
# Console Terminal 
# Main Menu
# ---------------------------------

clear

cat << "EOF"
---------------------------------
    _____ __         __                    ______                          _
   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ \`/ ___/ /
  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
 /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
---------------------------------
Currency Conversion API - Unix / Linux Version
Console Terminal 
Main Menu
---------------------------------
EOF

echo "Select an option:"
echo "[1] - Server"
echo "[2] - Console"
echo "[3] - DB"
echo "[4] - Directory"
echo "[5] - Changelog"

read -p "Enter a number from 1 to 5:" option

case $option in
    1)
        echo "Running the Server script..."
        ./scripts/server.sh
        ;;
    2)
        echo "Running the Console script..."
        ./scripts/console.sh
        ;;
    3)
        echo "Running the DB script..."
        ./scripts/db.sh
        ;;
    4)
        echo "Running the Directory script..."
        ./scripts/directory.sh
        ;;
    5)
        echo "Running the Changelog script..."
        ./scripts/changelog.sh
        ;;
    *)
        echo "Invalid option. Please choose a number from 1 to 5."
        ;;
esac
