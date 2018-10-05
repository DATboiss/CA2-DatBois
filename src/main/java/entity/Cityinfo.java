package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "cityinfo")
public class Cityinfo implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCityInfo")
    private Integer idCityInfo;

    @Size(max = 4)
    @Column(name = "zipCode")
    private String zipCode;

    @Size(max = 45)
    @Column(name = "city")
    private String city;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "cityinfo", fetch = FetchType.EAGER)
    private List<Address> addressCollection = new ArrayList();

    public Cityinfo()
    {
    }

    public Cityinfo(String zipCode, String city)
    {
        this.zipCode = zipCode;
        this.city = city;
        this.addressCollection = new ArrayList();
    }

    public Integer getIdCityInfo()
    {
        return idCityInfo;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public List<Address> getAddressCollection()
    {
        return addressCollection;
    }
   
    public void addAddress(Address a)
    {
        a.setCityinfo(this);
        addressCollection.add(a);
    }

    public void setAddressCollection(List<Address> addressCollection)
    {
        this.addressCollection = addressCollection;
    }
    
    

//    @Override
//    public String toString()
//    {
//        return "Cityinfo{" + "idCityInfo=" + idCityInfo + ", zipCode=" + zipCode + ", city=" + city + ", addressCollection=" + addressCollection + '}';
//    }

    @Override
    public int hashCode()
    {
        int hash = 7;
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
        final Cityinfo other = (Cityinfo) obj;
        if (!Objects.equals(this.idCityInfo, other.idCityInfo))
        {
            return false;
        }
        return true;
    }

}
