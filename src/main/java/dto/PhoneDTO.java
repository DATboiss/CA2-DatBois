package dto;

/**
 *
 * @author Sebastian
 */
public class PhoneDTO
{
 private String number;
 private String description;

    public PhoneDTO(String number, String description)
    {
        this.number = number;
        this.description = description;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
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
