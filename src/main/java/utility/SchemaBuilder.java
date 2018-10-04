/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import entity.Address;
import entity.Cityinfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.PersonFacade;
import java.util.HashMap;
import javax.persistence.Persistence;

/**
 *
 * @author emilv
 */
public class SchemaBuilder
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Persistence.generateSchema("pu", new HashMap<>());
        PersonFacade pf = new PersonFacade(Persistence.createEntityManagerFactory("pu"));
        Hobby h1 = new Hobby("Fodbold", "Stor sport");
        Hobby h2 = new Hobby("Karate", "Kampsport");
        Cityinfo ci = new Cityinfo("2990", "nivå");
        Address a = new Address("Hasselhøj", "108", ci);
        Person p = new Person("Test@gmail.com", "Emil", "Hermansen", a);
        Phone p1 = new Phone("25582940", "Mobil");
        p.addHobby(h1);
        p.addHobby(h2);
        p.addPhone(p1);
        pf.addPerson(p);
        
        Hobby h3 = new Hobby("Håndbold", "Stor sport");
        Phone p2 = new Phone("12345678", "Mobil");
        Cityinfo ci2 = new Cityinfo("2980", "Kokkedal");
        Address a2 = new Address("Hasselhøj", "108", ci2);
        Person pp = new Person("Adam@gmail.com", "Adam", "Saidane", a2);
        pp.addHobby(h3);
        pp.addPhone(p2);
        pf.addPerson(pp);
        
    }

}
