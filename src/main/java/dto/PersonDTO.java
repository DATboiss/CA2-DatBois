package dto;

import entity.Address;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author adams
 */
public class PersonDTO
{

    private String firstName;
    private String lastName;
    private String email;
    private String addressStreet;
    private String addressAdditionalInfo;
    private String zipcode;
    private String city;
    private List<String> phoneNumber;
    private List<String> hobbies;

    public PersonDTO()
    {
    }

    public PersonDTO(Person p)
    {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.email = p.getEmail();
        this.addressStreet = p.getAddress().getStreet();
        this.addressAdditionalInfo = p.getAddress().getAdditionalInfo();
        this.zipcode = p.getAddress().getCityinfo().getZipCode();
        this.city = p.getAddress().getCityinfo().getCity();
        this.phoneNumber = p.getPhoneCollection().stream().map(pho -> pho.getNumber()).collect(Collectors.toList());
        this.hobbies = p.getHobbyCollection().stream().map(hob -> hob.getName()).collect(Collectors.toList());
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

    public String getAddressStreet()
    {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet)
    {
        this.addressStreet = addressStreet;
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

    public String getAddressAdditionalInfo()
    {
        return addressAdditionalInfo;
    }

    public void setAddressAdditionalInfo(String addressAdditionalInfo)
    {
        this.addressAdditionalInfo = addressAdditionalInfo;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
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
