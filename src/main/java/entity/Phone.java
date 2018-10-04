package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author emilv
 */
@Entity
@Table(name = "phone")
public class Phone implements Serializable
{

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idPhone;

    @Column(name = "number")
    private String number;

    @Size(max = 45)
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "Person_idPerson", referencedColumnName = "idPerson")
    @ManyToOne
    private Person person;

    public Phone()
    {
    }

    public Phone(String number, String description)
    {
        this.number = number;
        this.description = description;
    }

    public Integer getIdPhone()
    {
        return idPhone;
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

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

}
