package com.donnedesang.dao;

import com.donnedesang.model.Match;
import jakarta.persistence.*;
import java.util.List;

public class MatchDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("donnedesangPU");

    public void create(Match match) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(match);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Match> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Match> matches = em.createQuery("SELECT m FROM Match m", Match.class).getResultList();
        em.close();
        return matches;
    }
}