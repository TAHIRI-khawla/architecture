@echo off
echo ========================================
echo Demarrage des services backend
echo ========================================
echo.

start "Docteur Service" cmd /k "cd docteur-service && mvnw.cmd spring-boot:run"
timeout /t 3 /nobreak >nul

start "Patient Service" cmd /k "cd patient-service && mvnw.cmd spring-boot:run"
timeout /t 3 /nobreak >nul

start "Rendez-vous Service" cmd /k "cd rdv-service && mvnw.cmd spring-boot:run"
timeout /t 3 /nobreak >nul

start "Notification Service" cmd /k "cd notification-service && mvnw.cmd spring-boot:run"
timeout /t 3 /nobreak >nul

echo.
echo ========================================
echo Tous les services backend sont en cours de demarrage...
echo ========================================
echo.
echo Services:
echo - Docteur Service: http://localhost:8081
echo - Patient Service: http://localhost:8084
echo - Rendez-vous Service: http://localhost:8082
echo - Notification Service: http://localhost:8083
echo.
echo Fermez cette fenetre pour arreter tous les services.
pause

