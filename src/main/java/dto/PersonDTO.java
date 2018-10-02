package dto;

import java.util.ArrayList;
import java.util.List;

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
    private String hobby;

    public PersonDTO()
    {
    }

    public PersonDTO(String firstName, String lastName, String email, String address, String cityInfo, List<String> phoneNumber, String hobby)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.cityInfo = cityInfo;
        this.phoneNumber = phoneNumber;
        this.hobby = hobby;
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

    public String getHobby()
    {
        return hobby;
    }

    public void setHobby(String hobby)
    {
        this.hobby = hobby;
    }
    
    
}
