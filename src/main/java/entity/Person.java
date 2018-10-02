package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author emilv
 */
@Entity
@Table(name = "person")
@NamedQueries(
{
    @NamedQuery(name = "Person.findAll", 
            query = "SELECT dto.PersonDTO(p.firstName, p.lastName, p.email, p.address.street, p.address.cityinfo, p.phoneCollection, p.hobbyCollection) "
                    + "FROM Person p")
    , @NamedQuery(name = "Person.findByHobby", 
            query = "SELECT dto.PersonDTO(p.firstName, p.lastName, p.email, p.address.street, p.address.cityinfo, p.phoneCollection, p.hobbyCollection) "
                    + "FROM Person p WHERE (SELECT h.name FROM p.hobbyCollection h = :name")
    , @NamedQuery(name = "Person.findByIdPerson", query = "SELECT p FROM Person p WHERE p.personPK.idPerson = :idPerson")
    , @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email")
    , @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName")
    , @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName")
    , @NamedQuery(name = "Person.findByAddressidAddress", query = "SELECT p FROM Person p WHERE p.personPK.addressidAddress = :addressidAddress")
    , @NamedQuery(name = "Person.findByZipCode", query = "SELECT p FROM Person p JOIN Address JOIN CityInfo c WHERE p.address.cityinfo.zipCode = :zipCode")
    , @NamedQuery(name = "Person.findByPhoneNumber", query = "SELECT dto.PersonDTO"
            + "(p.firstName, p.lastName, p.email, p.address.street, p.address.cityinfo, p.phoneCollection, p.hobbyCollection) "
            + "FROM Person p WHERE (SELECT ph.number FROM p.phoneCollection ph = :number)")
})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonPK personPK;
    
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    
    @Size(max = 45)
    @Column(name = "firstName")
    private String firstName;
   
    @Size(max = 45)
    @Column(name = "lastName")
    private String lastName;
    
    @ManyToMany(mappedBy = "personCollection")
    private Collection<Hobby> hobbyCollection;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Collection<Phone> phoneCollection;
   
    @JoinColumns(
    {
        @JoinColumn(name = "Address_idAddress", referencedColumnName = "idAddress", insertable = false, updatable = false)
        , @JoinColumn(name = "Address_CityInfo_idCityInfo", referencedColumnName = "CityInfo_idCityInfo", insertable = false, updatable = false)
    })
    @ManyToOne(optional = false)
    private Address address;

    public Person()
    {
    }

    public Person(String email, String firstName, String lastName, Collection<Hobby> hobbyCollection, Collection<Phone> phoneCollection, Address address)
    {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbyCollection = hobbyCollection;
        this.phoneCollection = phoneCollection;
        this.address = address;
    }
    

    public PersonPK getPersonPK()
    {
        return personPK;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Collection<Hobby> getHobbyCollection()
    {
        return hobbyCollection;
    }

    public void setHobbyCollection(Collection<Hobby> hobbyCollection)
    {
        this.hobbyCollection = hobbyCollection;
    }

    public Collection<Phone> getPhoneCollection()
    {
        return phoneCollection;
    }

    public void setPhoneCollection(Collection<Phone> phoneCollection)
    {
        this.phoneCollection = phoneCollection;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }


}