package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "address")
public class Address implements Serializable
{
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idAddress;

    @Size(max = 45)
    @Column(name = "Street")
    private String street;

    @Size(max = 45)
    @Column(name = "additionalInfo")
    private String additionalInfo;

    @JoinColumn(name = "CityInfo_idCityInfo", referencedColumnName = "idCityInfo")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cityinfo cityinfo;
    
    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private Collection<Person> personCollection;

    public Address()
    {
    }

    public Address(String street, String additionalInfo, Cityinfo cityinfo)
    {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityinfo = cityinfo;
    }

    public Integer getIdAddress()
    {
        return idAddress;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }

    public Cityinfo getCityinfo()
    {
        return cityinfo;
    }

    public void setCityinfo(Cityinfo cityinfo)
    {
        this.cityinfo = cityinfo;
    }

    public Collection<Person> getPersonCollection()
    {
        return personCollection;
    }
    public void addPerson(Person p)
    {
        p.setAddress(this);
        personCollection.add(p);
    }

    public void setPersonCollection(Collection<Person> personCollection)
    {
        this.personCollection = personCollection;
    }

    @Override
    public String toString()
    {
        return "Address{" + "idAddress=" + idAddress + ", street=" + street + ", additionalInfo=" + additionalInfo + ", cityinfo=" + cityinfo + ", personCollection=" + personCollection + '}';
    }
    
    

}
