package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entity.Person;
import facade.AddressFacade;
import facade.PersonFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author adams
 */
@Path("person")
public class PersonResource
{

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private PersonFacade pf = new PersonFacade(Persistence.createEntityManagerFactory("pu"));
    private AddressFacade af = new AddressFacade(Persistence.createEntityManagerFactory("pu"));
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource()
    {
    }

    /**
     * Retrieves representation of all Persons in the database
     *
     * @return a response code and a representation of a Person
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersons()
    {
        //Use Facade to get the person, this is just an example of the exception handling
        List<PersonDTO> p = null;
        p = pf.getAllPersons();
        if (!p.isEmpty())
        {
            return Response.ok(GSON.toJson(p)).build();
        } else
        {
            try
            {
                throw new NoPersonException("No persons were found in the database");
            } catch (NoPersonException e)
            {
                ExceptionError ee = new ExceptionError(e, 206, false);
                String errorJson = GSON.toJson(ee);
                return Response.status(206).entity(errorJson).build();
            }
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") int id)
    {
        //Use Facade to get the person, this is just an example of the exception handling
        PersonDTO p = null;
        pf.getPerson(id);
        if (p != null)
        {
            return Response.ok(GSON.toJson(p)).build();
        } else
        {
            try
            {
                throw new NoPersonException("No persons with the given id was found");
            } catch (NoPersonException e)
            {
                ExceptionError ee = new ExceptionError(e, 406, false);
                String errorJson = GSON.toJson(ee);
                return Response.status(406).entity(errorJson).build();
            }
        }
//        return GSON.toJson(pf.getPerson(id));
    }

    /**
     * Retrieves representation of all instances of Person with the given
     * address
     *
     *
     * @param address representation of an address
     * @return a response code and representation of a Person
     */
    @GET
    @Path("address/{address}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonWithAddress(@PathParam("address") String address)
    {
        //Use Facade to get the person, this is just an example of the exception handling
        List<PersonDTO> p = null;
        p = pf.getPersonByAddress(address);
        if (!p.isEmpty())
        {
            return Response.ok(GSON.toJson(p)).build();
        } else
        {
            try
            {
                throw new NoPersonException("No persons with the given address was found");
            } catch (NoPersonException e)
            {
                ExceptionError ee = new ExceptionError(e, 406, false);
                String errorJson = GSON.toJson(ee);
                return Response.status(406).entity(errorJson).build();
            }
        }
    }

    /**
     * Retrieves representation of an instance of Person with the given phone
     * number
     *
     *
     * @param phoneNumber representaion of a phone number
     * @return a response code and representation of a Person
     */
    @GET
    @Path("phoneNumber/{phoneNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonWithPhoneNumber(@PathParam("phoneNumber") String phoneNumber)
    {
        //Use Facade to get the person, this is just an example of the exception handling
        PersonDTO p = null;
        p = pf.getPersonByPhoneNumber(phoneNumber);
        if (p != null)
        {
            return Response.ok(GSON.toJson(p)).build();
        } else
        {
            try
            {
                throw new NoPersonException("No persons with the given phone number was found");
            } catch (NoPersonException e)
            {
                ExceptionError ee = new ExceptionError(e, 406, false);
                String errorJson = GSON.toJson(ee);
                return Response.status(406).entity(errorJson).build();
            }
        }
    }

    /**
     * Retrieves representation of all instances of Person with the given zip
     * code
     *
     *
     * @param zipCode representaion of a zipCode
     * @return a response code and representation of a Person
     */
    @GET
    @Path("zipCode/{zipCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonWithZipCode(@PathParam("zipCode") String zipCode)
    {
        //Use Facade to get the person, this is just an example of the exception handling
        List<PersonDTO> p = null;
        p = pf.getPersonsByCity(zipCode);
        if (!p.isEmpty())
        {
            return Response.ok(GSON.toJson(p)).build();
        } else
        {
            try
            {
                throw new NoPersonException("No persons with the given zip was found");
            } catch (NoPersonException e)
            {
                ExceptionError ee = new ExceptionError(e, 406, false);
                String errorJson = GSON.toJson(ee);
                return Response.status(406).entity(errorJson).build();
            }
        }
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonWithName(@PathParam("name") String name)
    {
        //Use Facade to get the person, this is just an example of the exception handling
        List<PersonDTO> p = pf.getPersonByName(name);
        if (!p.isEmpty())
        {
            return Response.ok(GSON.toJson(p)).build();
        } else
        {
            try
            {
                throw new NoPersonException("No persons with the given name was found");
            } catch (NoPersonException e)
            {
                ExceptionError ee = new ExceptionError(e, 406, false);
                String errorJson = GSON.toJson(ee);
                return Response.status(406).entity(errorJson).build();
            }
        }
    }

    /**
     * Retrieves representation of all Person objects, only returning contact
     * information
     *
     * @return a response code and representation of a PersonDTO
     */
    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonWithContactInfo()
    {
        //Use Facade to get the person, this is just an example of the exception handling
        List<PersonDTO> p = null;
        p = pf.getAllPersons();
        if (p != null)
        {
            return Response.ok(GSON.toJson(p)).build();
        } else
        {
            try
            {
                throw new NoPersonException("No persons were found in the database");
            } catch (NoPersonException e)
            {
                ExceptionError ee = new ExceptionError(e, 206, false);
                String errorJson = GSON.toJson(ee);
                return Response.status(206).entity(errorJson).build();
            }
        }
    }

    /**
     * Retrieves representation of all instances of Person with the given zip
     * code
     *
     *
     * @param zipCode representaion of a zipCode
     * @return a response code and representation of a Person
     */
    @GET
    @Path("hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonWithHobby(@PathParam("hobby") String hobby)
    {
        //Use Facade to get the person, this is just an example of the exception handling
        List<PersonDTO> p = null;
        p = pf.getPersonsByHobby(hobby);
        if (!p.isEmpty())
        {
            return Response.ok(GSON.toJson(p)).build();
        } else
        {
            try
            {
                throw new NoPersonException("No persons with the given hobby was found");
            } catch (NoPersonException e)
            {
                ExceptionError ee = new ExceptionError(e, 406, false);
                String errorJson = GSON.toJson(ee);
                return Response.status(406).entity(errorJson).build();
            }
        }
    }

    /**
     * POST method for creating an instance of Person
     *
     * @param json
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postPerson(String json)
    {
        Person p = null;
        p = GSON.fromJson(json, Person.class);
        System.out.println(p);
        if (p != null)
        {
            pf.addPerson(p);
        } else
        {
            try
            {
                throw new NoPersonException("Attempt to add person was unsuccesful. Please contact support");
            } catch (NoPersonException e)
            {
                ExceptionError ee = new ExceptionError(e, 406, false);
                String errorJson = GSON.toJson(ee);
                return Response.status(406).entity(errorJson).build();
            }
        }
        return Response.ok().build();
    }

    /**
     * PUT method for updating or creating an instance of Person
     *
     * @param content representation for the resource
     * @param id representation of the Person's id
     */
    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putPerson(String json)
    {
        Person p = null;
        p = GSON.fromJson(json, Person.class);
        if (p != null)
        {
            pf.editPerson(p);
        } else
        {
            try
            {
                throw new NoPersonException("Attempt to update person was unsuccesful. Please contact support");
            } catch (NoPersonException e)
            {
                ExceptionError ee = new ExceptionError(e, 406, false);
                String errorJson = GSON.toJson(ee);
                return Response.status(406).entity(errorJson).build();
            }
        }
        return Response.ok().build();
    }

    /**
     * DELETE method for deleting instance of Person
     *
     * @param id representation of the Person's id
     */
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") int id)
    {
        Person p = null;
        p = pf.deletePerson(id);
        if (p == null)
        {
            try
            {
                throw new NoPersonException("Attempt to delete person was unsuccesful. Please contact support");
            } catch (NoPersonException e)
            {
                ExceptionError ee = new ExceptionError(e, 406, false);
                String errorJson = GSON.toJson(ee);
                return Response.status(406).entity(errorJson).build();
            }
        }
        return Response.ok().build();
    }

}
