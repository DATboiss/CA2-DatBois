package dto;

import dto.PersonDTO;
import dto.CityInfoDTO;

import java.util.List;

/**
 *
 * @author emilv
 */
public class AddressDTO {
    private String street;
    private String additionalInfo;
    private CityInfoDTO cityInfo;
    private List<PersonDTO> persons;

    public AddressDTO()
    {
    }

    public AddressDTO(String street, String additionalInfo, CityInfoDTO cityInfo, List<PersonDTO> persons)
    {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfo = cityInfo;
        this.persons = persons;
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

    public CityInfoDTO getCityInfo()
    {
        return cityInfo;
    }

    public void setCityInfo(CityInfoDTO cityInfo)
    {
        this.cityInfo = cityInfo;
    }

    public List<PersonDTO> getPersons()
    {
        return persons;
    }
    
    public boolean addPersons(PersonDTO person)
    {
        return persons.add(person);
    }

    public void setPersons(List<PersonDTO> persons)
    {
        this.persons = persons;
    }


    @Override
    public String toString()
    {
        return "AddressDTO{" + "street=" + street + ", additionalInfo=" + additionalInfo + '}';
    }
    
    
    

}