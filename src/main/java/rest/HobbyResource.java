package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dto.HobbyDTO;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Sebastian
 */
@Path("hobby")
public class HobbyResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private PersonFacade pf = new PersonFacade(Persistence.createEntityManagerFactory("pu"));
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HobbyResource
     */
    public HobbyResource() {
    }

    /**
     * Retrieves representation of an instance of rest.HobbyResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllHobbies() {
        List<HobbyDTO> hobbies = null;
        hobbies = pf.getAllHobbies();
        if (hobbies != null) {
            return Response.ok(GSON.toJson(hobbies)).build();
        } else {
            try {
                throw new NoPersonException("No hobbies were found in the database");
            } catch (NoPersonException e) {
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
     * @param hobby
     * @return a response code and representation of a Person
     */
    @GET
    @Path("{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHobbyCount(@PathParam("hobby") String hobby) {
        //Use Facade to get the person, this is just an example of the exception handling
        long count = pf.getPersonCountHobby(hobby);

        JsonObject js = new JsonObject();
        js.addProperty("count", count);
        return Response.ok(GSON.toJson(js)).build();
    }

    /**
     * PUT method for updating or creating an instance of HobbyResource
     *
     * @param content representation for the resource
     */
}
