package facade;

import dto.CityInfoDTO;
import dto.PersonDTO;
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

    public List<PersonDTO> getPersonFromZip(String zip)
    {
        EntityManager em = getEntityManager();
        
        try
        {
            em.getTransaction().begin();
            return em.createQuery("SELECT new PersonDTO(p) FROM Person p JOIN Address a JOIN Cityinfo c WHERE p.address.cityinfo.zipCode = :zip")
            .setParameter("zip", zip).getResultList();
        } finally
        {
            em.close();
        }

    }

    public List<CityInfoDTO> getZipcodes()
    {
        EntityManager em = getEntityManager();
        try
        {
            Query zips = em.createQuery("SELECT new dto.CityInfoDTO(c.zipCode, c.city) FROM Cityinfo c");
            return zips.getResultList();
        } finally
        {
            em.close();
        }

    }
    
  
}
