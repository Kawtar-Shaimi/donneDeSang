package com.donnedesang.dao;

import com.donnedesang.model.Receveur;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ReceveurDAO {

    public void save(Receveur r) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(r);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void update(Receveur r) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(r);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Receveur r = em.find(Receveur.class, id);
            if (r != null) em.remove(r);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Optional<Receveur> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Receveur r = em.find(Receveur.class, id);
            return Optional.ofNullable(r);
        } finally {
            em.close();
        }
    }

    public List<Receveur> findAllOrderByPriorite() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // CRITIQUE (4), URGENT (3), NORMAL (1) => order by besoinPoches desc
            TypedQuery<Receveur> q = em.createQuery("SELECT r FROM Receveur r ORDER BY r.besoinPoches DESC", Receveur.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}