@echo off
echo ========================================
echo Demarrage du frontend React
echo ========================================
echo.

cd frontend
if not exist node_modules (
    echo Installation des dependances...
    call npm install
    echo.
)

echo Demarrage de l'application React...
echo Frontend sera accessible sur http://localhost:3000
echo.
call npm start

