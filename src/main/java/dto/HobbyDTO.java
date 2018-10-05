package dto;

import entity.Hobby;

/**
 *
 * @author wtfak
 */
public class HobbyDTO
{
    private String name;
    private String description;

    public HobbyDTO()
    {

    }

    public HobbyDTO(Hobby h)
    {
        this.name = h.getName();
        this.description = h.getDescription();
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
    
    
    

}
