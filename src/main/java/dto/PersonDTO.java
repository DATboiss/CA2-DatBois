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
    private AddressDTO address;
    private List<HobbyDTO> hobbies = new ArrayList();

    public PersonDTO(String firstName, String lastName, String email, AddressDTO address, List<HobbyDTO> hobbies)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.hobbies = hobbies;
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

    public AddressDTO getAddress()
    {
        return address;
    }

    public void setAddress(AddressDTO address)
    {
        this.address = address;
    }

    public List<HobbyDTO> getHobbies()
    {
        return hobbies;
    }
    public boolean addHobbies(HobbyDTO hobby)
    {
        return hobbies.add(hobby);
    }

    public void setHobbies(List<HobbyDTO> hobbies)
    {
        this.hobbies = hobbies;
    }

    @Override
    public String toString()
    {
        return "PersonDTO{" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", address=" + address + ", hobbies=" + hobbies + '}';
    }
    
    
    
    
    
}
