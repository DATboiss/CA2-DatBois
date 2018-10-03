package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityinfo")
    private Collection<Address> addressCollection;

    public Cityinfo()
    {
    }

    public Cityinfo(String zipCode, String city, Collection<Address> addressCollection)
    {
        this.zipCode = zipCode;
        this.city = city;
        this.addressCollection = addressCollection;
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

    public Collection<Address> getAddressCollection()
    {
        return addressCollection;
    }

    public void setAddressCollection(Collection<Address> addressCollection)
    {
        this.addressCollection = addressCollection;
    }

}
