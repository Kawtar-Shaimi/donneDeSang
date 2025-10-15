package com.donnedesang.dao;

import com.donnedesang.model.Donor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class DonorDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("donneDeSangPU");

    public List<Donor> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Donor> donors = em.createQuery("SELECT d FROM Donor d", Donor.class).getResultList();
        em.close();
        return donors;
    }

    // ===== Nouvelle méthode findById =====
    public Optional<Donor> findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Donor donor = em.find(Donor.class, id);
        em.close();
        return Optional.ofNullable(donor);
    }

    public void update(Donor donor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(donor);
        em.getTransaction().commit();
        em.close();
    }
}
