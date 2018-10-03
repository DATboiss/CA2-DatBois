/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Address;
import entity.Cityinfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emilv
 */
public class AddressFacadeTest
{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public AddressFacadeTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of getPersonFromZip method, of class AddressFacade.
     */
    @Test
    public void testGetPersonFromZip()
    {
        Cityinfo c = new Cityinfo("4600", "Køge");
        Address a = new Address("Kirkegårdsvej 3", "4.th", c);
        Hobby h = new Hobby("Øl", "Bajer", new ArrayList());
        Person p = new Person("Amadsen1702@gmail.com", "Anders", "Madsen", new ArrayList(), new ArrayList(), a);
        Phone ph = new Phone("53570037", "mobil", p);

        AddressFacade af = new AddressFacade(emf);
        PersonFacade pf = new PersonFacade(emf);
        pf.addPerson(p);
        System.out.println("getPersonFromZip");
        List<Person> result = af.getPersonFromZip("4600");
        assertEquals(result.size(), 1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZipcodes method, of class AddressFacade.
     */
    @Test
    public void testGetZipcodes()
    {
        AddressFacade af = new AddressFacade(emf);
        System.out.println("getZipcodes");
        AddressFacade instance = null;
        List<Cityinfo> result = af.getZipcodes();
        assertEquals(1352, result.size());
    }

}
