package facade;

import entity.Cityinfo;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author wtfak
 */
public class AddressFacade
{

    private EntityManagerFactory emf;

    public AddressFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public List<Person> getPersonFromCity(String city)
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            Query persons = em.createQuery("SELECT p FROM Person p WHERE p.address.cityinfo.city =:city");
            em.getTransaction().commit();
            return persons.getResultList();
        } finally
        {
            em.close();
        }

    }

    public List<Cityinfo> getZipcodes()
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            Query zipcodes = em.createQuery("SELECT  c.zip FROM Cityinfo c");
            em.getTransaction().commit();
            return zipcodes.getResultList();
        } finally
        {
            em.close();
        }

    }
}
