package dto;

import java.util.List;

/**
 *
 * @author emilv
 */
public class HobbyDTO
{
    private String name;
    private String description;
    private List<PersonDTO> persons;

    public HobbyDTO()
    {
    }

    public HobbyDTO(String name, String description, List<PersonDTO> persons)
    {
        this.name = name;
        this.description = description;
        this.persons = persons;
    }

    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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
        return "HobbyDTO{" + "name=" + name + ", description=" + description + ", persons=" + persons + '}';
    }


}
