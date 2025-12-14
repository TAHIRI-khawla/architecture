import React, { useState, useEffect } from 'react';
import { appointmentService, doctorService, patientService } from '../services/api';
import './AppointmentList.css';

const AppointmentList = () => {
  const [appointments, setAppointments] = useState([]);
  const [doctors, setDoctors] = useState([]);
  const [patients, setPatients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);
  const [formData, setFormData] = useState({
    doctorId: '',
    patientName: '',
    patientPhone: '',
    dateTime: ''
  });
  const [alert, setAlert] = useState(null);

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    try {
      setLoading(true);
      const [appointmentsRes, doctorsRes, patientsRes] = await Promise.all([
        appointmentService.getAll(),
        doctorService.getAll(),
        patientService.getAll()
      ]);
      setAppointments(appointmentsRes.data);
      setDoctors(doctorsRes.data);
      setPatients(patientsRes.data);
    } catch (error) {
      showAlert('Erreur lors du chargement des données', 'error');
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await appointmentService.create(formData);
      showAlert('Rendez-vous créé avec succès', 'success');
      setShowModal(false);
      setFormData({ doctorId: '', patientName: '', patientPhone: '', dateTime: '' });
      loadData();
    } catch (error) {
      showAlert('Erreur lors de la création du rendez-vous', 'error');
    }
  };

  const getDoctorName = (doctorId) => {
    const doctor = doctors.find(d => d.id === doctorId);
    return doctor ? doctor.nom : 'Inconnu';
  };

  const formatDateTime = (dateTime) => {
    if (!dateTime) return '';
    const date = new Date(dateTime);
    return date.toLocaleString('fr-FR');
  };

  const showAlert = (message, type) => {
    setAlert({ message, type });
    setTimeout(() => setAlert(null), 3000);
  };

  if (loading && appointments.length === 0) {
    return <div className="card">Chargement...</div>;
  }

  return (
    <div>
      <div className="page-header">
        <h2>Gestion des Rendez-vous</h2>
        <button className="btn btn-primary" onClick={() => setShowModal(true)}>
          + Nouveau Rendez-vous
        </button>
      </div>

      {alert && (
        <div className={`alert alert-${alert.type === 'error' ? 'error' : 'success'}`}>
          {alert.message}
        </div>
      )}

      {appointments.length === 0 ? (
        <div className="card empty-state">
          <p>Aucun rendez-vous trouvé</p>
        </div>
      ) : (
        <div className="card">
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Docteur</th>
                <th>Patient</th>
                <th>Téléphone</th>
                <th>Date/Heure</th>
                <th>Statut</th>
              </tr>
            </thead>
            <tbody>
              {appointments.map((appointment) => (
                <tr key={appointment.id}>
                  <td>{appointment.id}</td>
                  <td>{getDoctorName(appointment.doctorId)}</td>
                  <td>{appointment.patientName}</td>
                  <td>{appointment.patientPhone}</td>
                  <td>{formatDateTime(appointment.dateTime)}</td>
                  <td>
                    <span className={`status-badge status-${appointment.status?.toLowerCase()}`}>
                      {appointment.status}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {showModal && (
        <div className="modal">
          <div className="modal-content">
            <div className="modal-header">
              <h3>Nouveau Rendez-vous</h3>
              <button className="close-btn" onClick={() => setShowModal(false)}>
                ×
              </button>
            </div>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label>Docteur</label>
                <select
                  value={formData.doctorId}
                  onChange={(e) => setFormData({ ...formData, doctorId: e.target.value })}
                  required
                >
                  <option value="">Sélectionner un docteur</option>
                  {doctors.map((doctor) => (
                    <option key={doctor.id} value={doctor.id}>
                      {doctor.nom} - {doctor.specialite}
                    </option>
                  ))}
                </select>
              </div>
              <div className="form-group">
                <label>Nom du Patient</label>
                <input
                  type="text"
                  value={formData.patientName}
                  onChange={(e) => setFormData({ ...formData, patientName: e.target.value })}
                  required
                />
              </div>
              <div className="form-group">
                <label>Téléphone du Patient</label>
                <input
                  type="tel"
                  value={formData.patientPhone}
                  onChange={(e) => setFormData({ ...formData, patientPhone: e.target.value })}
                  required
                />
              </div>
              <div className="form-group">
                <label>Date et Heure</label>
                <input
                  type="datetime-local"
                  value={formData.dateTime}
                  onChange={(e) => setFormData({ ...formData, dateTime: e.target.value })}
                  required
                />
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" onClick={() => setShowModal(false)}>
                  Annuler
                </button>
                <button type="submit" className="btn btn-primary">
                  Créer
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default AppointmentList;

