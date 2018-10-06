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
import java.util.Map;
import javax.persistence.Cache;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Hobby h1 = new Hobby("Fodbold", "Stor sport");
        Hobby h2 = new Hobby("Karate", "Kampsport");
        em.persist(h1);
        em.persist(h2);
        
        
        
//        PersonFacade pf = new PersonFacade(Persistence.createEntityManagerFactory("pu"));
//        Cityinfo ci = new Cityinfo("2990", "nivå");
//        Address a = new Address("Hasselhøj 108", "", ci);
//        Person p = new Person("Test@gmail.com", "Emil", "Hermansen", a);
//        Phone p1 = new Phone("25582940", "Mobil");
//        p.addHobby(h1);
//        p.addHobby(h2);
//        p.addPhone(p1);
//        pf.addPerson(p);
//        
//        Hobby h3 = new Hobby("Håndbold", "Stor sport");
//        Phone p2 = new Phone("12345678", "Mobil");
//        Cityinfo ci2 = new Cityinfo("2980", "Kokkedal");
//        Address a2 = new Address("Testvej 1", "3.mf", ci2);
//        Person pp = new Person("Adam@gmail.com", "Adam", "Saidane", a2);
//        pp.addHobby(h3);
//        pp.addPhone(p2);
//        pf.addPerson(pp);
        
    }

}
