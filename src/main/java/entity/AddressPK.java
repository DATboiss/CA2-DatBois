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
public class AddressPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idAddress")
    private int idAddress;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CityInfo_idCityInfo")
    private int cityInfoidCityInfo;

    public AddressPK()
    {
    }

    public AddressPK(int cityInfoidCityInfo)
    {
        this.cityInfoidCityInfo = cityInfoidCityInfo;
    }

    
    public int getIdAddress()
    {
        return idAddress;
    }

    public int getCityInfoidCityInfo()
    {
        return cityInfoidCityInfo;
    }

    public void setCityInfoidCityInfo(int cityInfoidCityInfo)
    {
        this.cityInfoidCityInfo = cityInfoidCityInfo;
    }


}