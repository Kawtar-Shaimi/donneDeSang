package com.donnedesang.service;

import com.donnedesang.model.Donor;
import com.donnedesang.model.Receiver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.stream.Collectors;

public class DonorService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("donnedesangPU");

    // Ajouter un donneur
    public void addDonor(Donor donor) {
        // 🔹 Déterminer le statut selon les conditions
        if (donor.getAge() < 18 || donor.getAge() > 65 || donor.getWeight() < 50) {
            donor.setStatus("NON_ELIGIBLE");
        } else if (donor.isHasContraindications()) {
            donor.setStatus("NON_DISPONIBLE");
        } else {
            donor.setStatus("DISPONIBLE");
        }

        // 🔹 Sauvegarde dans la base
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(donor);
        em.getTransaction().commit();
        em.close();
    }


    // Récupérer un donneur par ID
    public Donor getDonorById(Long id) {
        EntityManager em = emf.createEntityManager();
        Donor donor = em.find(Donor.class, id);
        em.close();
        return donor;
    }

    // Récupérer tous les donneurs
    public List<Donor> getAllDonors() {
        EntityManager em = emf.createEntityManager();
        List<Donor> donors = em.createQuery("SELECT d FROM Donor d", Donor.class).getResultList();
        em.close();
        return donors;
    }

    // Mettre à jour un donneur
    public void updateDonor(Donor donor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(donor);
        em.getTransaction().commit();
        em.close();
    }

    // ===== Méthodes nécessaires pour les tests =====

    // Mettre à jour le statut d’un donneur
    public void updateDonorStatus(Donor donor) {
        donor.setStatus("Assigned");
        updateDonor(donor);
    }

    // Trouver les donneurs compatibles pour un receveur
    public List<Donor> findCompatibleDonors(List<Donor> donors, Receiver receiver) {
        return donors.stream()
                .filter(d -> d.getBloodGroup().equals(receiver.getBloodGroup()))
                .collect(Collectors.toList());
    }

    // Assigner un donneur à un receveur
    public void assignDonorToReceiver(Donor donor, Receiver receiver) {
        receiver.addDonor(donor);
        donor.setStatus("Assigned");
        updateDonor(donor);
    }
}