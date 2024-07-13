package org.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jdk.jfr.Percentage;

public class AbstractDAO {
    private static EntityManagerFactory entityManagerFactory = null;

    public static EntityManager entityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("market");
        }
        EntityManager em =entityManagerFactory.createEntityManager();
        return em;
    }
}
