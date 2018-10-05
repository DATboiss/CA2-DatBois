package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author emilv
 */
@Entity
@Table(name = "hobby")
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHobby")
    private Integer idHobby;
    
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    
    @Size(max = 100)
    @Column(name = "description")
    private String description;
    
    @JoinTable(name = "hobby_has_person", joinColumns =
    {
        @JoinColumn(name = "Hobby_idHobby", referencedColumnName = "idHobby")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "Person_idPerson", referencedColumnName = "idPerson")
        , @JoinColumn(name = "Person_Address_idAddress", referencedColumnName = "Address_idAddress")
        , @JoinColumn(name = "Person_Address_CityInfo_idCityInfo", referencedColumnName = "Address_CityInfo_idCityInfo")
    })
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Collection<Person> personCollection = new ArrayList();

    public Hobby()
    {
    }

    public Hobby(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public Integer getIdHobby()
    {
        return idHobby;
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

    public Collection<Person> getPersonCollection()
    {
        return personCollection;
    }
    
    public void addPerson(Person person)
    {
        person.getHobbyCollection().add(this);
        personCollection.add(person);
    }

    public void setPersonCollection(Collection<Person> personCollection)
    {
        this.personCollection = personCollection;
    }

}