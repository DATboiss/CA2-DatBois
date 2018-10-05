package facade;

import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebastian
 */
public class HobbyFacade
{
    
    private EntityManagerFactory emf;
    private AddressFacade af = new AddressFacade(emf);

    public HobbyFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    
}//CLASS
