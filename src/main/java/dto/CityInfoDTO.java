package dto;

/**
 *
 * @author emilv
 */
public class CityInfoDTO {
    private int zipCode;

    public CityInfoDTO()
    {
    }

    public CityInfoDTO(int zipCode)
    {
        this.zipCode = zipCode;
    }

    public int getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(int zipCode)
    {
        this.zipCode = zipCode;
    }

    @Override
    public String toString()
    {
        return "CityInfoDTO{" + "zipCode=" + zipCode + '}';
    }
    
    
}