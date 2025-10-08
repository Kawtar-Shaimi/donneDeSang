package com.donnedesang;

import com.donnedesang.model.Donor;

import jakarta.persistence.*;
import java.util.List;

public class DonorDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("donnedesangPU");

    // Ajouter un donneur
    public void create(Donor donor) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(donor);
        tx.commit();
        em.close();
    }

    // Modifier un donneur
    public void update(Donor donor) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(donor);
        tx.commit();
        em.close();
    }

    // Supprimer un donneur
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Donor donor = em.find(Donor.class, id);
        if (donor != null) {
            em.remove(donor);
        }
        tx.commit();
        em.close();
    }

    // Trouver un donneur par ID
    public Donor findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Donor donor = em.find(Donor.class, id);
        em.close();
        return donor;
    }

    // Lister tous les donneurs
    public List<Donor> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Donor> donors = em.createQuery("SELECT d FROM Donor d", Donor.class).getResultList();
        em.close();
        return donors;
    }
}