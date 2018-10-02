package dto;

import java.util.List;

/**
 *
 * @author emilv
 */
public class CityInfoDTO {
    private int zipCode;
    private List<AddressDTO> addresses;

    public CityInfoDTO()
    {
    }

    public CityInfoDTO(int zipCode, List<AddressDTO> addresses)
    {
        this.zipCode = zipCode;
        this.addresses = addresses;
    }

    public int getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(int zipCode)
    {
        this.zipCode = zipCode;
    }

    public List<AddressDTO> getAddresses()
    {
        return addresses;
    }
 
    
    public boolean addAddress(AddressDTO address)
    {
        return addresses.add(address);
    }

    
    public void setAddresses(List<AddressDTO> addresses)
    {
        this.addresses = addresses;
    }

    @Override
    public String toString()
    {
        return "CityInfoDTO{" + "zipCode=" + zipCode + ", addresses=" + addresses + '}';
    }
    
    
}