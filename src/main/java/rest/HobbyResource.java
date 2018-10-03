package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Hobby;
import facade.PersonFacade;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Sebastian
 */
@Path("hobby")
public class HobbyResource
{

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private PersonFacade pf = new PersonFacade(Persistence.createEntityManagerFactory("pu"));
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HobbyResource
     */
    public HobbyResource()
    {
    }

    /**
     * Retrieves representation of an instance of rest.HobbyResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllHobbies()
    {
        List<Hobby> hobbies = null;
        hobbies = pf.getAllHobbies();
        if (hobbies != null)
        {
            return Response.ok(GSON.toJson(hobbies)).build();
        } else
        {
            throw new NoPersonException("No persons were found in our database");
        }
    }

    /**
     * PUT method for updating or creating an instance of HobbyResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content)
    {
    }
}
