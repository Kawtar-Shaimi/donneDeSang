package com.donnedesang.test;

import jakarta.persistence.EntityManager;
import com.donnedesang.dao.JPAUtil;

public class TestDB {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        System.out.println("Connexion OK : " + em.isOpen());
        em.close();
    }
}