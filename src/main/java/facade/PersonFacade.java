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

    public Person addPerson(Person p)
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

    public PersonDTO getPersonByPhoneNumber(String phoneNum)
    {
        EntityManager em = emf.createEntityManager();
        PersonDTO p = null;

        try
        {
            p = em.createNamedQuery("SELECT dto.PersonDTO"
                    + "(p.firstName, p.lastName, p.email, p.address, p.phoneCollection, p.hobbyCollection) "
                    + "FROM Person p WHERE (SELECT ph.number FROM p.phoneCollection ph = :number)", PersonDTO.class).setParameter("number", phoneNum).getSingleResult();
        } finally
        {
            em.close();
        }
        return p;
    }

    public List<PersonDTO> getPersonByName(String name)
    {
        String[] nameArr = name.split(" ");
        String firstName = nameArr[0];
        String lastName = nameArr[(nameArr.length) - 1];
        EntityManager em = emf.createEntityManager();
        List<PersonDTO> persons = null;

        try
        {
            persons = em.createNamedQuery("SELECT dto.PersonDTO"
                    + "(p.firstName, p.lastName, p.email, p.address, p.phoneCollection, p.hobbyCollection) "
                    + "FROM Person p WHERE p.firstName = :firstName AND p.lastName = :lastName", PersonDTO.class)
                    .setParameter("firstName", firstName).setParameter("lastName", lastName).getResultList();
        } finally
        {
            em.close();
        }
        return persons;
    }

    public List<PersonDTO> getPersonsByHobby(String hobbyName)
    {
        EntityManager em = emf.createEntityManager();
        List<PersonDTO> persons = null;

        try
        {
            em.getTransaction().begin();
            persons = em.createNamedQuery("SELECT dto.PersonDTO"
                    + "(p.firstName, p.lastName, p.email, p.address, p.phoneCollection, p.hobbyCollection) "
                    + "FROM Person p WHERE (SELECT h.name FROM p.hobbyCollection h = :name", PersonDTO.class)
                    .setParameter("name", hobbyName).getResultList();
            em.getTransaction().commit();
        } finally
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
            count = (int) em.createQuery("SELECT COUNT(p) FROM Person p WHERE (SELECT h.name FROM p.hobbyCollection h = :hobbyName)")
                    .setParameter("hobbyName", hobbyName).getSingleResult();
        } finally
        {
            em.close();
        }
        return count;
    }

    public List<PersonDTO> getPersonsByCity(String zipCode)
    {
        EntityManager em = emf.createEntityManager();
        List<PersonDTO> persons = null;

        try
        {
            em.getTransaction().begin();
            persons = em.createNamedQuery("SELECT p FROM Person p JOIN Address JOIN Cityinfo c WHERE p.address.cityinfo.zipCode = :zipCode", PersonDTO.class).setParameter("zipCode", zipCode).getResultList();
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        return persons;
    }

    public List<PersonDTO> getPersonByAddress(String address)
    {
        EntityManager em = emf.createEntityManager();
        List<PersonDTO> persons = null;

        try
        {
            em.getTransaction().begin();
            persons = em.createQuery("SELECT dto.PersonDTO"
                    + "(p.firstName, p.lastName, p.email, p.address, p.phoneCollection, p.hobbyCollection) "
                    + "FROM Person p JOIN Address a WHERE p.address.street = :address").setParameter("address", address).getResultList();
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
        return persons;
    }

    public List<PersonDTO> getAllPersons()
    {
        EntityManager em = emf.createEntityManager();
        List<PersonDTO> persons = null;

        try
        {
            persons = em.createNamedQuery("SELECT dto.PersonDTO"
                    + "(p.firstName, p.lastName, p.email, p.address, p.phoneCollection, p.hobbyCollection) "
                    + "FROM Person p", PersonDTO.class).getResultList();
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

}//CLASS
