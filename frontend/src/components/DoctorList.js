import React, { useState, useEffect } from 'react';
import { doctorService } from '../services/api';
import './DoctorList.css';

const DoctorList = () => {
  const [doctors, setDoctors] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);
  const [editingDoctor, setEditingDoctor] = useState(null);
  const [formData, setFormData] = useState({ nom: '', specialite: '' });
  const [searchSpecialite, setSearchSpecialite] = useState('');
  const [alert, setAlert] = useState(null);

  useEffect(() => {
    loadDoctors();
  }, []);

  const loadDoctors = async () => {
    try {
      setLoading(true);
      const response = await doctorService.getAll();
      setDoctors(response.data);
    } catch (error) {
      showAlert('Erreur lors du chargement des docteurs', 'error');
    } finally {
      setLoading(false);
    }
  };

  const handleSearch = async () => {
    try {
      setLoading(true);
      const response = await doctorService.search(searchSpecialite);
      setDoctors(response.data);
    } catch (error) {
      showAlert('Erreur lors de la recherche', 'error');
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingDoctor) {
        await doctorService.update(editingDoctor.id, formData);
        showAlert('Docteur mis à jour avec succès', 'success');
      } else {
        await doctorService.create(formData);
        showAlert('Docteur créé avec succès', 'success');
      }
      setShowModal(false);
      setFormData({ nom: '', specialite: '' });
      setEditingDoctor(null);
      loadDoctors();
    } catch (error) {
      showAlert('Erreur lors de l\'enregistrement', 'error');
    }
  };

  const handleEdit = (doctor) => {
    setEditingDoctor(doctor);
    setFormData({ nom: doctor.nom, specialite: doctor.specialite });
    setShowModal(true);
  };

  const handleDelete = async (id) => {
    if (window.confirm('Êtes-vous sûr de vouloir supprimer ce docteur ?')) {
      try {
        await doctorService.delete(id);
        showAlert('Docteur supprimé avec succès', 'success');
        loadDoctors();
      } catch (error) {
        showAlert('Erreur lors de la suppression', 'error');
      }
    }
  };

  const handleNew = () => {
    setEditingDoctor(null);
    setFormData({ nom: '', specialite: '' });
    setShowModal(true);
  };

  const showAlert = (message, type) => {
    setAlert({ message, type });
    setTimeout(() => setAlert(null), 3000);
  };

  if (loading && doctors.length === 0) {
    return <div className="card">Chargement...</div>;
  }

  return (
    <div>
      <div className="page-header">
        <h2>Gestion des Docteurs</h2>
        <button className="btn btn-primary" onClick={handleNew}>
          + Nouveau Docteur
        </button>
      </div>

      {alert && (
        <div className={`alert alert-${alert.type === 'error' ? 'error' : 'success'}`}>
          {alert.message}
        </div>
      )}

      <div className="card">
        <div className="search-bar">
          <input
            type="text"
            placeholder="Rechercher par spécialité..."
            value={searchSpecialite}
            onChange={(e) => setSearchSpecialite(e.target.value)}
            onKeyPress={(e) => e.key === 'Enter' && handleSearch()}
          />
          <button className="btn btn-secondary" onClick={handleSearch}>
            Rechercher
          </button>
          <button className="btn btn-secondary" onClick={loadDoctors}>
            Réinitialiser
          </button>
        </div>
      </div>

      {doctors.length === 0 ? (
        <div className="card empty-state">
          <p>Aucun docteur trouvé</p>
        </div>
      ) : (
        <div className="card">
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Spécialité</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {doctors.map((doctor) => (
                <tr key={doctor.id}>
                  <td>{doctor.id}</td>
                  <td>{doctor.nom}</td>
                  <td>{doctor.specialite}</td>
                  <td>
                    <button
                      className="btn btn-secondary"
                      onClick={() => handleEdit(doctor)}
                      style={{ marginRight: '10px' }}
                    >
                      Modifier
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={() => handleDelete(doctor.id)}
                    >
                      Supprimer
                    </button>
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
              <h3>{editingDoctor ? 'Modifier le Docteur' : 'Nouveau Docteur'}</h3>
              <button className="close-btn" onClick={() => setShowModal(false)}>
                ×
              </button>
            </div>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label>Nom</label>
                <input
                  type="text"
                  value={formData.nom}
                  onChange={(e) => setFormData({ ...formData, nom: e.target.value })}
                  required
                />
              </div>
              <div className="form-group">
                <label>Spécialité</label>
                <input
                  type="text"
                  value={formData.specialite}
                  onChange={(e) => setFormData({ ...formData, specialite: e.target.value })}
                  required
                />
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" onClick={() => setShowModal(false)}>
                  Annuler
                </button>
                <button type="submit" className="btn btn-primary">
                  {editingDoctor ? 'Mettre à jour' : 'Créer'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default DoctorList;

