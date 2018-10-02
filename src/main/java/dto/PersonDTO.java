package dto;

import entity.Hobby;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author adams
 */
public class PersonDTO {
    
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String cityInfo;
    private List<String> phoneNumber;
    private List<String> hobbies;

    public PersonDTO()
    {
    }

    public PersonDTO(String firstName, String lastName, String email, String address, String cityInfo, List<Phone> phoneNumber, List<Hobby> hobbies)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.cityInfo = cityInfo;
        this.phoneNumber = phoneNumber.stream().map(p -> p.getNumber()).collect(Collectors.toList());
        this.hobbies = hobbies.stream().map(h -> h.getName()).collect(Collectors.toList());
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCityInfo()
    {
        return cityInfo;
    }

    public void setCityInfo(String cityInfo)
    {
        this.cityInfo = cityInfo;
    }

    public List<String> getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(List<String> phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getHobbies()
    {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies)
    {
        this.hobbies = hobbies;
    }

    
    
    
}
