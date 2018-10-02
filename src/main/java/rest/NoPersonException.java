package rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author adams
 */
public class NoPersonException extends WebApplicationException {

    public NoPersonException(String msg)
    {
        super(Response.status(406).entity(msg).build());
    }
}
