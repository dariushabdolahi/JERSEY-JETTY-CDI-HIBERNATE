package ir.dalit.model.repository;
import ir.dalit.model.entity.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serializable;

@ApplicationScoped
public class PersonRepository implements Serializable {

    @Inject
    private EntityManager entityManager;

    public Person insert(Person person){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(person);
        transaction.commit();
        entityManager.close();
        return person;
    }

}
