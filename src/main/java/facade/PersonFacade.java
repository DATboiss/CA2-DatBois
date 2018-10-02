package facade;

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
    
    public Person addPerson(Person p)
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
    
    public Person getPerson(int id)
    {
        EntityManager em = emf.createEntityManager();
        Person p = null;
        
        try
        {
            p = em.find(Person.class, id);
        }finally
        {
            em.close();
        }
        return p;
    }
    
    public List<Person> getAllPersons()
    {
        EntityManager em = emf.createEntityManager();
        List<Person> persons = null;
        
        try
        {
            persons = em.createNamedQuery("Person.findAll", Person.class).getResultList();
        }finally
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
