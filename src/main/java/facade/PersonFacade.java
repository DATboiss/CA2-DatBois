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
        }finally
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
        }finally
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
        }finally
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
        }finally
        {
            em.close();
        }
        return persons;
    }
    
    public int getPersonCountHobby(String hobbyName)
    {
        EntityManager em = emf.createEntityManager();
        
        int count = 0;
        
        try
        {
            count = em.createQuery("SELECT COUNT(p) FROM Person p WHERE (SELECT h.name FROM)").getSingleResult();
        }
    }
    
    public Person editPerson(Person p)
    {
        EntityManager em = emf.createEntityManager();
        
        try
        {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        }finally
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
        }finally
        {
            em.close();
        }
        return p;
    }
    
    
    
}//CLASS
