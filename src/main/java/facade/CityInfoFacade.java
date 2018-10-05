package facade;

import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebastian
 */
public class CityInfoFacade
{
    
    private EntityManagerFactory emf;
    private AddressFacade af = new AddressFacade(emf);

    public CityInfoFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    
}//CLASS
