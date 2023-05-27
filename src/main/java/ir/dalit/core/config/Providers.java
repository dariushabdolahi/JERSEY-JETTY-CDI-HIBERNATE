package ir.dalit.core.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class Providers {

    private static final Logger logger = LoggerFactory.getLogger(Providers.class);
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("persistence");

    @RequestScoped
    @Produces
    public EntityManager entityManager() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        logger.info(">>> EntityManager Injected");
        return entityManager;
    }

}
