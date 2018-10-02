package facade;

import dto.PersonDTO;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebastian
 */
public class PersonFacade
{

    private EntityManagerFactory emf;

    public PersonFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public PersonDTO addPerson(PersonDTO p)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        return p;
    }

    public PersonDTO getPerson(int id)
    {
        EntityManager em = emf.createEntityManager();
        PersonDTO p = null;

        try
        {
            p = em.find(PersonDTO.class, id);
        } finally
        {
            em.close();
        }
        return p;
    }

    public PersonDTO getPerson(String phoneNum)
    {
        EntityManager em = emf.createEntityManager();
        PersonDTO p = null;

        try
        {
            p = em.createNamedQuery("Person.findByPhoneNumber", PersonDTO.class).getSingleResult();
        } finally
        {
            em.close();
        }
        return p;
    }

    public List<PersonDTO> getAllPersons()
    {
        EntityManager em = emf.createEntityManager();
        List<PersonDTO> persons = null;

        try
        {
            persons = em.createNamedQuery("Person.findAll", PersonDTO.class).getResultList();
        } finally
        {
            em.close();
        }
        return persons;
    }

    public Person editPerson(Person p)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        return p;
    }

    public Person deletePerson(int id)
    {
        EntityManager em = emf.createEntityManager();
        Person p = null;

        try
        {
            em.getTransaction().begin();
            p = em.find(Person.class, id);
            em.remove(p);
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        return p;
    }

    public List<PersonDTO> getPersonsByHobby(String hobbyName)
    {
        EntityManager em = emf.createEntityManager();
        List<PersonDTO> persons = null;

        try
        {
            em.getTransaction().begin();
            persons = em.createNamedQuery("Person.findByHobby", PersonDTO.class).setParameter("name", hobbyName).getResultList();
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        return persons;
    }
    
     public List<PersonDTO> getPersonsByCity(String zipCode)
    {
        EntityManager em = emf.createEntityManager();
        List<PersonDTO> persons = null;

        try
        {
            em.getTransaction().begin();
            persons = em.createNamedQuery("Person.findByZipCode", PersonDTO.class).setParameter("zipCode", zipCode).getResultList();
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        return persons;
    }

}//CLASS
