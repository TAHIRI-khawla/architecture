import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import './App.css';
import DoctorList from './components/DoctorList';
import PatientList from './components/PatientList';
import AppointmentList from './components/AppointmentList';
import NotificationList from './components/NotificationList';

function App() {
  return (
    <Router>
      <div className="App">
        <nav className="navbar">
          <div className="container">
            <h1 className="navbar-brand">🏥 Healthcare Management System</h1>
            <ul className="navbar-nav">
              <li><Link to="/doctors">Docteurs</Link></li>
              <li><Link to="/patients">Patients</Link></li>
              <li><Link to="/appointments">Rendez-vous</Link></li>
              <li><Link to="/notifications">Notifications</Link></li>
            </ul>
          </div>
        </nav>

        <div className="container">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/doctors" element={<DoctorList />} />
            <Route path="/patients" element={<PatientList />} />
            <Route path="/appointments" element={<AppointmentList />} />
            <Route path="/notifications" element={<NotificationList />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

function Home() {
  return (
    <div className="card">
      <h2>Bienvenue dans le système de gestion de santé</h2>
      <p>Utilisez le menu de navigation pour accéder aux différentes sections :</p>
      <ul style={{ marginTop: '20px', lineHeight: '2' }}>
        <li><strong>Docteurs</strong> - Gérer les informations des médecins</li>
        <li><strong>Patients</strong> - Gérer les informations des patients</li>
        <li><strong>Rendez-vous</strong> - Gérer les rendez-vous médicaux</li>
        <li><strong>Notifications</strong> - Consulter les notifications</li>
      </ul>
    </div>
  );
}

export default App;

