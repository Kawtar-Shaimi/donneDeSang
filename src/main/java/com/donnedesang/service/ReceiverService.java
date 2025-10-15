package com.donnedesang.service;

import com.donnedesang.model.Receiver;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ReceiverService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("donneDeSangPU");

    public void addReceiver(Receiver receiver) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(receiver);
        em.getTransaction().commit();
        em.close();
    }

    public Receiver getReceiverById(Long id) {
        EntityManager em = emf.createEntityManager();
        Receiver receiver = em.find(Receiver.class, id);
        em.close();
        return receiver;
    }

    public List<Receiver> getAllReceivers() {
        EntityManager em = emf.createEntityManager();
        List<Receiver> receivers = em.createQuery("SELECT r FROM Receiver r", Receiver.class).getResultList();
        em.close();
        return receivers;
    }

    public void updateReceiver(Receiver receiver) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(receiver);
        em.getTransaction().commit();
        em.close();
    }
}
