import React, { useState } from 'react';
import { notificationService } from '../services/api';
import './NotificationList.css';

const NotificationList = () => {
  const [showModal, setShowModal] = useState(false);
  const [formData, setFormData] = useState({
    message: '',
    type: 'INFO'
  });
  const [alert, setAlert] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await notificationService.create(formData);
      showAlert('Notification créée avec succès', 'success');
      setShowModal(false);
      setFormData({ message: '', type: 'INFO' });
    } catch (error) {
      showAlert('Erreur lors de la création de la notification', 'error');
    }
  };

  const showAlert = (message, type) => {
    setAlert({ message, type });
    setTimeout(() => setAlert(null), 3000);
  };

  return (
    <div>
      <div className="page-header">
        <h2>Gestion des Notifications</h2>
        <button className="btn btn-primary" onClick={() => setShowModal(true)}>
          + Nouvelle Notification
        </button>
      </div>

      {alert && (
        <div className={`alert alert-${alert.type === 'error' ? 'error' : 'success'}`}>
          {alert.message}
        </div>
      )}

      <div className="card">
        <p>Les notifications sont créées automatiquement lors des opérations dans le système.</p>
        <p>Vous pouvez également créer des notifications manuellement ci-dessous.</p>
      </div>

      {showModal && (
        <div className="modal">
          <div className="modal-content">
            <div className="modal-header">
              <h3>Nouvelle Notification</h3>
              <button className="close-btn" onClick={() => setShowModal(false)}>
                ×
              </button>
            </div>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label>Type</label>
                <select
                  value={formData.type}
                  onChange={(e) => setFormData({ ...formData, type: e.target.value })}
                  required
                >
                  <option value="INFO">Information</option>
                  <option value="WARNING">Avertissement</option>
                  <option value="ERROR">Erreur</option>
                  <option value="SUCCESS">Succès</option>
                </select>
              </div>
              <div className="form-group">
                <label>Message</label>
                <textarea
                  value={formData.message}
                  onChange={(e) => setFormData({ ...formData, message: e.target.value })}
                  required
                  rows="4"
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

export default NotificationList;

