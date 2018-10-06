package entity;

import dto.PersonDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "personCollection", fetch = FetchType.EAGER)
    private List<Hobby> hobbyCollection = new ArrayList();

    @JoinColumn(name = "Phone_idPhone", referencedColumnName = "idPhone")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "person", fetch = FetchType.EAGER)
    private List<Phone> phoneCollection = new ArrayList();

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

    public List<Hobby> getHobbyCollection()
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

    public void setHobbyCollection(List<Hobby> hobbyCollection)
    {
        this.hobbyCollection = hobbyCollection;
    }

    public List<Phone> getPhoneCollection()
    {
        return phoneCollection;
    }
    
    public void addPhone(Phone phone)
    {
        phone.setPerson(this);
        phoneCollection.add(phone);
    }

    public void setPhoneCollection(List<Phone> phoneCollection)
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

    
    
//    @Override
//    public String toString()
//    {
//        String s = "Person{" + "idPerson=" + idPerson + ",\n email=" + email + ", \nfirstName=" + firstName + ", \nlastName=" + lastName + ", \nhobbyCollection=";
//        for (Hobby hobby : hobbyCollection)
//        {
//            s+= hobby + "\n";
//        }
//        for (Phone  phone : phoneCollection)
//        {
//            s+= phone + "\n";
//        }
//        s += ", address=" + address + '}';
//        return s;
//    }
//    

    @Override
    public int hashCode()
    {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.idPerson, other.idPerson))
        {
            return false;
        }
        return true;
    }


    public Person setValues(Person p)
    {
        this.email = p.getEmail();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.address = p.getAddress();
        this.phoneCollection = p.getPhoneCollection();
        for (Phone phone : phoneCollection)
        {
            phone.setPerson(this);
        }
        this.hobbyCollection = p.getHobbyCollection();
        //Need to set person in each hobby
        return this;
    }
}
