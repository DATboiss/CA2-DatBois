package dto;

/**
 *
 * @author emilv
 */
public class AddressDTO {
    private String street;
    private String additionalInfo;

    public AddressDTO()
    {
    }

    public AddressDTO(String street, String additionalInfo)
    {
        this.street = street;
        this.additionalInfo = additionalInfo;
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

    @Override
    public String toString()
    {
        return "AddressDTO{" + "street=" + street + ", additionalInfo=" + additionalInfo + '}';
    }
    
    
    

}