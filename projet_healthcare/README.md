# Healthcare Management System

Système de gestion de santé avec architecture microservices.

## Architecture

Le projet est composé de :
- **4 microservices backend** (Spring Boot)
- **1 application frontend** (React)

### Services Backend

1. **docteur-service** (Port 8081)
   - Gestion des médecins
   - Endpoints: `/api/doctors`

2. **patient-service** (Port 8084)
   - Gestion des patients
   - Endpoints: `/api/patients`

3. **rdv-service** (Port 8082)
   - Gestion des rendez-vous
   - Endpoints: `/api/appointments`

4. **notification-service** (Port 8083)
   - Gestion des notifications
   - Endpoints: `/notifications`

### Frontend

- **React Application** (Port 3000)
  - Interface utilisateur pour gérer tous les services

## Démarrage rapide

### Option 1 : Démarrer tout automatiquement

```bash
start-all.bat
```

### Option 2 : Démarrer manuellement

#### Backend (dans 4 terminaux séparés)

```bash
# Terminal 1 - Docteur Service
cd docteur-service
mvnw spring-boot:run

# Terminal 2 - Patient Service
cd patient-service
mvnw spring-boot:run

# Terminal 3 - Rendez-vous Service
cd rdv-service
mvnw spring-boot:run

# Terminal 4 - Notification Service
cd notification-service
mvnw spring-boot:run
```

#### Frontend

```bash
cd frontend
npm install
npm start
```

## URLs

- Frontend: http://localhost:3000
- Docteur Service: http://localhost:8081
- Patient Service: http://localhost:8084
- Rendez-vous Service: http://localhost:8082
- Notification Service: http://localhost:8083

## Prérequis

- Java 17+
- Maven
- Node.js 16+
- npm

## Communication entre services

Tous les services communiquent entre eux via OpenFeign. Les clients Feign sont configurés dans chaque service pour appeler les autres microservices.

