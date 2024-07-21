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

echo "Selecione uma opção:"
echo "[1] - Server"
echo "[2] - Console"
echo "[3] - DB"
echo "[4] - Directory"
echo "[5] - Changelog"

read -p "Digite um número de 1 a 5: " option

case $option in
    1)
        echo "Executando o script Server..."
        ./scripts/server.sh
        ;;
    2)
        echo "Executando o script Console..."
        ./scripts/console.sh
        ;;
    3)
        echo "Executando o script DB..."
        ./scripts/db.sh
        ;;
    4)
        echo "Executando o script Directory..."
        ./scripts/directory.sh
        ;;
    5)
        echo "Executando o script Changelog..."
        ./scripts/changelog.sh
        ;;
    
    *)
        echo "Opção inválida. Por favor, escolha um número de 1 a 5."
        ;;
esac
