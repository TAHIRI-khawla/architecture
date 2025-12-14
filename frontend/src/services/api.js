import axios from 'axios';

// Configuration des URLs des services backend
const API_BASE_URLS = {
  doctor: 'http://localhost:8086',
  patient: 'http://localhost:8084',
  appointment: 'http://localhost:8082',
  notification: 'http://localhost:8083'
};

// Service pour les docteurs
export const doctorService = {
  getAll: () => axios.get(`${API_BASE_URLS.doctor}/api/doctors`),
  getById: (id) => axios.get(`${API_BASE_URLS.doctor}/api/doctors/${id}`),
  create: (doctor) => axios.post(`${API_BASE_URLS.doctor}/api/doctors`, doctor),
  update: (id, doctor) => axios.put(`${API_BASE_URLS.doctor}/api/doctors/${id}`, doctor),
  delete: (id) => axios.delete(`${API_BASE_URLS.doctor}/api/doctors/${id}`),
  search: (specialite) => axios.get(`${API_BASE_URLS.doctor}/api/doctors/search`, {
    params: { specialite }
  })
};

// Service pour les patients
export const patientService = {
  getAll: () => axios.get(`${API_BASE_URLS.patient}/api/patients`),
  getById: (id) => axios.get(`${API_BASE_URLS.patient}/api/patients/${id}`),
  create: (patient) => axios.post(`${API_BASE_URLS.patient}/api/patients`, patient),
  delete: (id) => axios.delete(`${API_BASE_URLS.patient}/api/patients/${id}`)
};

// Service pour les rendez-vous
export const appointmentService = {
  getAll: () => axios.get(`${API_BASE_URLS.appointment}/api/appointments`),
  getByDoctor: (doctorId) => axios.get(`${API_BASE_URLS.appointment}/api/appointments/doctor/${doctorId}`),
  create: (appointment) => axios.post(`${API_BASE_URLS.appointment}/api/appointments`, appointment)
};

// Service pour les notifications
export const notificationService = {
  create: (notification) => axios.post(`${API_BASE_URLS.notification}/notifications`, notification)
};

