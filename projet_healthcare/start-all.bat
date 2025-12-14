@echo off
echo ========================================
echo Demarrage de l'application Healthcare
echo ========================================
echo.

echo Demarrage des services backend...
call start-backend.bat

timeout /t 10 /nobreak >nul

echo.
echo Demarrage du frontend...
start "Frontend React" cmd /k "call start-frontend.bat"

echo.
echo ========================================
echo Application demarree!
echo ========================================
echo.
echo Frontend: http://localhost:3000
echo.
echo Appuyez sur une touche pour fermer cette fenetre...
pause

