# Guide de Démarrage - Healthcare Management System

## Problèmes CORS résolus ✅

La configuration CORS a été ajoutée à tous les services backend pour permettre les requêtes depuis le frontend React (http://localhost:3000).

## Démarrage des Services

### 1. Démarrer les Services Backend

Vous devez démarrer les 4 services backend dans des terminaux séparés :

#### Terminal 1 - Docteur Service (Port 8081)
```bash
cd projet_healthcare/docteur-service
mvnw.cmd spring-boot:run
```

#### Terminal 2 - Patient Service (Port 8084)
```bash
cd projet_healthcare/patient-service
mvnw.cmd spring-boot:run
```

#### Terminal 3 - Rendez-vous Service (Port 8082)
```bash
cd projet_healthcare/rdv-service
mvnw.cmd spring-boot:run
```

#### Terminal 4 - Notification Service (Port 8083)
```bash
cd projet_healthcare/notification-service
mvnw.cmd spring-boot:run
```

### 2. Vérifier que les services sont démarrés

Attendez que chaque service affiche :
```
Started [ServiceName]Application in X.XXX seconds
```

Vous pouvez tester les services en ouvrant dans votre navigateur :
- http://localhost:8081/api/doctors
- http://localhost:8084/api/patients
- http://localhost:8082/api/appointments
- http://localhost:8083/notifications

### 3. Démarrer le Frontend React

Une fois tous les services backend démarrés, dans un nouveau terminal :

```bash
cd projet_healthcare/frontend
npm install
npm start
```

Le frontend sera accessible sur http://localhost:3000

## Utilisation des Scripts Batch (Windows)

### Option 1 : Démarrer tout automatiquement
Double-cliquez sur `start-all.bat`

### Option 2 : Démarrer séparément
- `start-backend.bat` - Démarre les 4 services backend
- `start-frontend.bat` - Démarre le frontend React

## Résolution des Erreurs

### ERR_CONNECTION_REFUSED
- **Cause** : Le service backend n'est pas démarré
- **Solution** : Vérifiez que tous les services backend sont démarrés et en cours d'exécution

### CORS Policy Error
- **Cause** : Configuration CORS manquante (maintenant résolu ✅)
- **Solution** : Redémarrez les services backend pour appliquer la nouvelle configuration CORS

### ERR_EMPTY_RESPONSE
- **Cause** : Le service est en cours de démarrage ou a crashé
- **Solution** : Vérifiez les logs du service et attendez qu'il soit complètement démarré

## URLs des Services

- **Frontend** : http://localhost:3000
- **Docteur Service** : http://localhost:8081
- **Patient Service** : http://localhost:8084
- **Rendez-vous Service** : http://localhost:8082
- **Notification Service** : http://localhost:8083

## Ordre de Démarrage Recommandé

1. Démarrer tous les services backend (attendre qu'ils soient complètement démarrés)
2. Vérifier que les services répondent (tester les URLs ci-dessus)
3. Démarrer le frontend React
4. Ouvrir http://localhost:3000 dans votre navigateur

