package com.donnedesang.dao;

import com.donnedesang.model.Donneur;
import com.donnedesang.model.StatutDisponibilite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class DonneurDAO {

    public void save(Donneur d) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void update(Donneur d) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(d);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Donneur d = em.find(Donneur.class, id);
            if (d != null) em.remove(d);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Optional<Donneur> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Donneur d = em.find(Donneur.class, id);
            return Optional.ofNullable(d);
        } finally {
            em.close();
        }
    }

    public List<Donneur> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Donneur> q = em.createQuery("SELECT d FROM Donneur d", Donneur.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    // donneurs disponibles
    public List<Donneur> findDisponibles() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Donneur> q = em.createQuery("SELECT d FROM Donneur d WHERE d.statut = :stat", Donneur.class);
            q.setParameter("stat", com.donnedesang.model.StatutDisponibilite.DISPONIBLE);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}