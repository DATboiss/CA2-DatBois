package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author emilv
 */
@Entity
@Table(name = "phone")
@NamedQueries(
        {
            @NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p")
            , @NamedQuery(name = "Phone.findByIdPhone", query = "SELECT p FROM Phone p WHERE p.phonePK.idPhone = :idPhone")
            , @NamedQuery(name = "Phone.findByNumber", query = "SELECT p FROM Phone p WHERE p.number = :number")
            , @NamedQuery(name = "Phone.findByDescription", query = "SELECT p FROM Phone p WHERE p.description = :description")
            , @NamedQuery(name = "Phone.findByPersonidPerson", query = "SELECT p FROM Phone p WHERE p.phonePK.personidPerson = :personidPerson")
        })
public class Phone implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PhonePK phonePK;
   
    @Column(name = "number")
    private String number;
    
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    
    @JoinColumn(name = "Person_idPerson", referencedColumnName = "idPerson", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person person;

    public Phone()
    {
    }

    public Phone(String number, String description, Person person)
    {
        this.number = number;
        this.description = description;
        this.person = person;
    }

    public PhonePK getPhonePK()
    {
        return phonePK;
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
