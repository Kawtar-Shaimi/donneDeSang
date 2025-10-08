package com.donnedesang.dao;

import com.donnedesang.model.Receiver;

import jakarta.persistence.*;
import java.util.List;

public class ReceiverDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("donnedesangPU");

    // Ajouter un receveur
    public void create(Receiver receiver) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(receiver);
        tx.commit();
        em.close();
    }

    // Modifier un receveur
    public void update(Receiver receiver) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(receiver);
        tx.commit();
        em.close();
    }

    // Supprimer un receveur
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Receiver receiver = em.find(Receiver.class, id);
        if (receiver != null) {
            em.remove(receiver);
        }
        tx.commit();
        em.close();
    }

    // Trouver un receveur par ID
    public Receiver findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Receiver receiver = em.find(Receiver.class, id);
        em.close();
        return receiver;
    }

    // Lister tous les receveurs
    public List<Receiver> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Receiver> receivers = em.createQuery("SELECT r FROM Receiver r", Receiver.class).getResultList();
        em.close();
        return receivers;
    }
}
