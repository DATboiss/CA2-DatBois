package entity;

import dto.PersonDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "personCollection", fetch = FetchType.EAGER)
    private Collection<Hobby> hobbyCollection = new ArrayList();

    @JoinColumn(name = "Phone_idPhone", referencedColumnName = "idPhone")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "person", fetch = FetchType.EAGER)
    private Collection<Phone> phoneCollection = new ArrayList();

    @JoinColumns(
            {
                @JoinColumn(name = "Address_idAddress", referencedColumnName = "idAddress")
                , @JoinColumn(name = "Address_CityInfo_idCityInfo", referencedColumnName = "CityInfo_idCityInfo")
            })
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Address address;

    public Person()
    {
    }

    public Person(String email, String firstName, String lastName, Address address)
    {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
    
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
    
//    public void addHobby1(Hobby hobby)
//    {
//        hobby.addPerson2(this);
//        hobbyCollection.add(hobby);
//    }
    
    public void addHobby(Hobby hobby)
    {
        hobby.getPersonCollection().add(this);
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

    @Override
    public String toString()
    {
        String s = "Person{" + "idPerson=" + idPerson + ",\n email=" + email + ", \nfirstName=" + firstName + ", \nlastName=" + lastName + ", \nhobbyCollection=";
        for (Hobby hobby : hobbyCollection)
        {
            s+= hobby + "\n";
        }
        for (Phone  phone : phoneCollection)
        {
            s+= phone + "\n";
        }
        s += ", address=" + address + '}';
        return s;
    }
    
}
