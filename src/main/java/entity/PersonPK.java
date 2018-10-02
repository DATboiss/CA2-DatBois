package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author emilv
 */
@Embeddable
public class PersonPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idPerson")
    private int idPerson;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Address_idAddress")
    private int addressidAddress;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Address_CityInfo_idCityInfo")
    private int addressCityInfoidCityInfo;

    public PersonPK()
    {
    }

    public PersonPK(int addressidAddress, int addressCityInfoidCityInfo)
    {
        this.addressidAddress = addressidAddress;
        this.addressCityInfoidCityInfo = addressCityInfoidCityInfo;
    }

    public int getIdPerson()
    {
        return idPerson;
    }

    public int getAddressidAddress()
    {
        return addressidAddress;
    }

    public void setAddressidAddress(int addressidAddress)
    {
        this.addressidAddress = addressidAddress;
    }

    public int getAddressCityInfoidCityInfo()
    {
        return addressCityInfoidCityInfo;
    }

    public void setAddressCityInfoidCityInfo(int addressCityInfoidCityInfo)
    {
        this.addressCityInfoidCityInfo = addressCityInfoidCityInfo;
    }


}