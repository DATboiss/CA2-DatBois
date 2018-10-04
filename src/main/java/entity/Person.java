package entity;

import dto.PersonDTO;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Person implements Serializable
{

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idPerson;

    @Size(max = 45)
    @Column(name = "email")
    private String email;

    @Size(max = 45)
    @Column(name = "firstName")
    private String firstName;

    @Size(max = 45)
    @Column(name = "lastName")
    private String lastName;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "personCollection")
    private Collection<Hobby> hobbyCollection;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "person")
    private Collection<Phone> phoneCollection;

    @JoinColumns(
            {
                @JoinColumn(name = "Address_idAddress", referencedColumnName = "idAddress")
                , @JoinColumn(name = "Address_CityInfo_idCityInfo", referencedColumnName = "CityInfo_idCityInfo")
            })
    @ManyToOne(cascade = CascadeType.PERSIST)
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
    
//    public Person(PersonDTO pDTO)
//    {
//        this.firstName = pDTO.getFirstName();
//        this.lastName = pDTO.getLastName();
//        this.email = pDTO.getEmail();
//        this.address = new Address(pDTO.getAddressStreet(), pDTO.getAddressAdditionalInfo(), new Cityinfo(pDTO.getZipcode(), pDTO.getCity()));
//                
//    }
    

    public Integer getIdPerson()
    {
        return idPerson;
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
    
    public void addHobby(Hobby hobby)
    {
//        hobby.addPerson(this);
        hobbyCollection.add(hobby);
    }

    public void setHobbyCollection(Collection<Hobby> hobbyCollection)
    {
        this.hobbyCollection = hobbyCollection;
    }

    public Collection<Phone> getPhoneCollection()
    {
        return phoneCollection;
    }
    
    public void addPhone(Phone phone)
    {
        phone.setPerson(this);
        phoneCollection.add(phone);
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
