package com.donnedesang.test;

import com.donnedesang.model.*;
import jakarta.persistence.*;

public class MatchTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("donnedesangPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Récupérer un donneur et un receveur existants
            Donor donor = em.find(Donor.class, 1L);      // ID 1 à adapter selon ta base
            Receiver receiver = em.find(Receiver.class, 1L); // ID 1 à adapter

            if (donor != null && receiver != null) {
                // Créer et enregistrer un match
                Match match = new Match(donor, receiver);
                em.persist(match);
                System.out.println("✅ Match ajouté avec succès !");
            } else {
                System.out.println("⚠️ Donor ou Receiver introuvable !");
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx.isActive()) tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}