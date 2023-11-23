package dk.dbc.helloworld;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello-world")
public class HelloResource {

    @GET
    public String ping() {
        return "Hello, World!";
    }
}
