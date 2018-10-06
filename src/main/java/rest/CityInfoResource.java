package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CityInfoDTO;
import facade.AddressFacade;
import facade.PersonFacade;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author wtfak
 */
@Path("cityinfo")
public class CityInfoResource
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private PersonFacade pf = new PersonFacade(Persistence.createEntityManagerFactory("pu"));
    private AddressFacade af = new AddressFacade(Persistence.createEntityManagerFactory("pu"));
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CityInfoResource
     */
    public CityInfoResource()
    {
    }

    /**
     * Retrieves representation of an instance of rest.CityInfoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson()
    {
        List<CityInfoDTO> c = null;
        c = af.getZipcodes();
        if (!c.isEmpty())
        {
            return Response.ok(GSON.toJson(c)).build();
        } else
        {
            throw new NoPersonException("No zipcodes were found in our database");
        }
    }

    /**
     * PUT method for updating or creating an instance of CityInfoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content)
    {
    }
}
