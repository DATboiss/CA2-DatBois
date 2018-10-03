/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dto.PersonDTO;
import entity.Person;
import java.util.List;
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
public class PersonFacadeTest
{
    
    public PersonFacadeTest()
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
     * Test of addPerson method, of class PersonFacade.
     */
    @Test
    public void testAddPerson()
    {
        System.out.println("addPerson");
        PersonDTO p = null;
        PersonFacade instance = null;
        PersonDTO expResult = null;
        PersonDTO result = instance.addPerson(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPerson method, of class PersonFacade.
     */
    @Test
    public void testGetPerson_int()
    {
        System.out.println("getPerson");
        int id = 0;
        PersonFacade instance = null;
        PersonDTO expResult = null;
        PersonDTO result = instance.getPerson(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPerson method, of class PersonFacade.
     */
    @Test
    public void testGetPerson_String()
    {
        System.out.println("getPerson");
        String phoneNum = "";
        PersonFacade instance = null;
        PersonDTO expResult = null;
        PersonDTO result = instance.getPerson(phoneNum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPersons method, of class PersonFacade.
     */
    @Test
    public void testGetAllPersons()
    {
        System.out.println("getAllPersons");
        PersonFacade instance = null;
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getAllPersons();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editPerson method, of class PersonFacade.
     */
    @Test
    public void testEditPerson()
    {
        System.out.println("editPerson");
        Person p = null;
        PersonFacade instance = null;
        Person expResult = null;
        Person result = instance.editPerson(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePerson method, of class PersonFacade.
     */
    @Test
    public void testDeletePerson()
    {
        System.out.println("deletePerson");
        int id = 0;
        PersonFacade instance = null;
        Person expResult = null;
        Person result = instance.deletePerson(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonsByHobby method, of class PersonFacade.
     */
    @Test
    public void testGetPersonsByHobby()
    {
        System.out.println("getPersonsByHobby");
        String hobbyName = "";
        PersonFacade instance = null;
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getPersonsByHobby(hobbyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonCountHobby method, of class PersonFacade.
     */
    @Test
    public void testGetPersonCountHobby()
    {
        System.out.println("getPersonCountHobby");
        String hobbyName = "";
        PersonFacade instance = null;
        int expResult = 0;
        int result = instance.getPersonCountHobby(hobbyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonsByCity method, of class PersonFacade.
     */
    @Test
    public void testGetPersonsByCity()
    {
        System.out.println("getPersonsByCity");
        String zipCode = "";
        PersonFacade instance = null;
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getPersonsByCity(zipCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
