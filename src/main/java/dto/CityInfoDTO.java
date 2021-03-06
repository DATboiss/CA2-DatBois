package dto;

import entity.Cityinfo;
import java.util.List;

/**
 *
 * @author emilv
 */
public class CityInfoDTO {
    private String zipCode;
    private String city;

    public CityInfoDTO()
    {
    }

    public CityInfoDTO(String zipCode, String city)
    {
        this.zipCode = zipCode;
        this.city = city;
    }
    
    public CityInfoDTO(Cityinfo cityInfo)
    {
        this.zipCode = cityInfo.getZipCode();
        this.city = cityInfo.getCity();
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
    
    

    
}