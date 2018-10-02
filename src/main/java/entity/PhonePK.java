package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author emilv
 */
@Embeddable
public class PhonePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idPhone")
    private int idPhone;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Person_idPerson")
    private int personidPerson;

    public PhonePK()
    {
    }

    public PhonePK(int personidPerson)
    {
        this.personidPerson = personidPerson;
    }
    

    public int getIdPhone()
    {
        return idPhone;
    }

    public int getPersonidPerson()
    {
        return personidPerson;
    }

    public void setPersonidPerson(int personidPerson)
    {
        this.personidPerson = personidPerson;
    }

}