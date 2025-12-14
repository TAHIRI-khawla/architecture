# Healthcare Management System - Frontend

Application React pour la gestion du système de santé.

## Installation

```bash
cd frontend
npm install
```

## Démarrage

```bash
npm start
```

L'application sera accessible sur http://localhost:3000

## Structure

- `src/components/` - Composants React
  - `DoctorList.js` - Gestion des docteurs
  - `PatientList.js` - Gestion des patients
  - `AppointmentList.js` - Gestion des rendez-vous
  - `NotificationList.js` - Gestion des notifications

- `src/services/` - Services API
  - `api.js` - Configuration des appels API vers les microservices

## Configuration

Les URLs des services backend sont configurées dans `src/services/api.js`:
- Docteur Service: http://localhost:8081
- Patient Service: http://localhost:8084
- Rendez-vous Service: http://localhost:8082
- Notification Service: http://localhost:8083

Assurez-vous que tous les services backend sont démarrés avant d'utiliser l'application.

