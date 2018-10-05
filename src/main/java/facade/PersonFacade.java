package facade;

import dto.PersonDTO;
import entity.Cityinfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebastian
 */
public class PersonFacade {

    private EntityManagerFactory emf;
    private AddressFacade af = new AddressFacade(emf);

    public PersonFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public Person addPerson(Person p)
    {
        EntityManager em = emf.createEntityManager();
        String zipCode = p.getAddress().getCityinfo().getZipCode();
        Cityinfo ci = em.createQuery("SELECT c FROM Cityinfo c WHERE c.zipCode = :zipCode", Cityinfo.class).setParameter("zipCode", zipCode).getSingleResult();
        p.getAddress().setCityinfo(ci);
        if (p.getPhoneCollection() != null)
        {
            System.out.println("####################vi kommer herind");
            Collection<Phone> phoneArr = p.getPhoneCollection();
            for (Phone phone : phoneArr)
            {
                System.out.println("###### vikommer også herind");
                phone.setPerson(p);
            }
        }
        try
        {
            if (p.getPhoneCollection() != null)
            {
                Collection<Phone> phoneArr = new ArrayList();
                for (Phone phone : phoneArr)
                {
                    phone.setPerson(p);
                }
            }
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
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
            p = em.createQuery("SELECT NEW dto.PersonDTO(p) FROM Person p JOIN Phone ph WHERE ph MEMBER OF P.phoneCollection AND ph.number = :number", PersonDTO.class)
                    .setParameter("number", phoneNum).getSingleResult();
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
            p = em.createQuery("SELECT NEW dto.PersonDTO(p) FROM Person p WHERE p.idPerson = :id", PersonDTO.class)
                    .setParameter("id", id).getSingleResult();
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
            persons = em.createQuery("SELECT NEW dto.PersonDTO(p) FROM Person p WHERE p.firstName = :firstName AND p.lastName = :lastName", PersonDTO.class)
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
            persons = em.createQuery("SELECT NEW dto.PersonDTO(p) FROM Person p JOIN Hobby h WHERE h.name = :name", PersonDTO.class)
                    .setParameter("name", hobbyName).getResultList();
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
            count = (int) em.createQuery("SELECT COUNT(p) FROM Person p JOIN Hobby h WHERE h.name = :hobbyName")
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
            persons = em.createQuery("SELECT NEW dto.PersonDTO(p) FROM Person p WHERE p MEMBER OF p.address.personCollection AND p.address.cityinfo.zipCode = :zipCode", PersonDTO.class).setParameter("zipCode", zipCode).getResultList();
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
            persons = em.createQuery("SELECT NEW dto.PersonDTO(p) FROM Person p JOIN Address a WHERE p.address.street = :address").setParameter("address", address).getResultList();
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
            persons = em.createQuery("SELECT NEW dto.PersonDTO(p) FROM Person p", PersonDTO.class).getResultList();
        } finally
        {
            em.close();
        }
        return persons;
    }

    public Person editPerson(Person p)
    {
        EntityManager em = emf.createEntityManager();
        String zipCode = p.getAddress().getCityinfo().getZipCode();
        Cityinfo ci = em.createQuery("SELECT c FROM Cityinfo c WHERE c.zipCode = :zipCode", Cityinfo.class).setParameter("zipCode", zipCode).getSingleResult();
        p.getAddress().setCityinfo(ci);

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

    public List<PersonDTO> getPersonByHobby(String Hobby)
    {
        EntityManager em = emf.createEntityManager();
        List<PersonDTO> persons = null;

        try
        {
            persons = em.createQuery("SELECT NEW dto.PersonDTO(p) FROM Person p JOIN Hobby h WHERE h.name = :hobbyName")
                    .setParameter("hobbyName", Hobby).getResultList();
        } finally
        {
            em.close();
        }
        return persons;
    }

    public List<Hobby> getAllHobbies()
    {
        EntityManager em = emf.createEntityManager();
        List<Hobby> hobbies = null;

        try
        {
            hobbies = em.createQuery("SELECT h FROM Hobby h", Hobby.class).getResultList();
        } finally
        {
            em.close();
        }
        return hobbies;
    }

}//CLASS
