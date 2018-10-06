package facade;

import entity.Person;
import entity.Phone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebastian
 */
public class PhoneFacade
{
    
    private EntityManagerFactory emf;
    private AddressFacade af = new AddressFacade(emf);
    
    public PhoneFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    
    public Phone addPhone(Phone ph, Person p)
    {
        EntityManager em = emf.createEntityManager();
        
        p.addPhone(ph);
        try
        {
            em.getTransaction().begin();
            em.persist(ph);
            em.merge(p);
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        return ph;
    }//addPhone
    
    public Phone editphone(Phone ph, Integer personId)
    {
        EntityManager em = emf.createEntityManager();
        
        Phone phOld = em.createQuery("SELECT Phone p", Phone.class).getSingleResult();
        try
        {
            em.getTransaction().begin();
            
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        return ph;
    }//editPhone
    
}//CLASS
