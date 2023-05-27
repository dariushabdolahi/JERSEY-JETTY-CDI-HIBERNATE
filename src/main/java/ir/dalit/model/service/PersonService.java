package ir.dalit.model.service;

import ir.dalit.model.entity.Person;
import ir.dalit.model.repository.PersonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PersonService {

    @Inject
    private PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.insert(person);
    }

}
