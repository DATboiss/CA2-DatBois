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
    private AddressDTO address;
    private String zipcode;
    private String city;
    private List<PhoneDTO> phoneNumber;
    private List<HobbyDTO> hobbies;

    public PersonDTO()
    {
    }

    public PersonDTO(Person p)
    {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.email = p.getEmail();
        this.address = new AddressDTO(p.getAddress());
        this.phoneNumber = p.getPhoneCollection().stream().map(pho -> new PhoneDTO(pho.getNumber(), pho.getDescription())).collect(Collectors.toList());
        this.hobbies = p.getHobbyCollection().stream().map(hob -> new HobbyDTO(hob.getName(), hob.getDescription())).collect(Collectors.toList());
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

    public AddressDTO getAddress()
    {
        return address;
    }

    public void setAddress(AddressDTO address)
    {
        this.address = address;
    }

    public List<PhoneDTO> getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(List<PhoneDTO> phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public List<HobbyDTO> getHobbies()
    {
        return hobbies;
    }

    public void setHobbies(List<HobbyDTO> hobbies)
    {
        this.hobbies = hobbies;
    }
    
    

}//CLASS
