package ir.dalit.api;

import ir.dalit.model.entity.Person;
import ir.dalit.model.repository.PersonRepository;
import ir.dalit.model.service.PersonService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;


@Path("/person")
@ApplicationScoped
public class PersonAPI {

    @Inject
    private PersonService personService;

    @Path("/save")
    @Produces("application/json")
    @GET
    public Object save(@QueryParam("name") String name , @QueryParam("family") String family){
        Person person = new Person().setName(name).setFamily(family);
        return personService.save(person);
    }
}
