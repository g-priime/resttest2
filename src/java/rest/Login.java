package rest;

import java.io.StringReader;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import services.AccountService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 703174
 */
@Path("verify")
public class Login {

    /**
     * Creates a new instance of CheckAccount
     */
    public Login() {
    }

    @Context
    private UriInfo context;

    /**
     * Retrieves representation of an instance of rest.CheckAccount
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean getJson(String content) {
        JsonParser parser = Json.createParser(new StringReader(content));

        parser.next(); // START_OBJECT

        // Username
        parser.next();       // KEY_NAME
        parser.next();       // VALUE_STRING
        String username = parser.getString();

        // Password
        parser.next();       // KEY_NAME
        parser.next();       // VALUE_STRING
        String password = parser.getString();

        AccountService as = new AccountService();
        boolean login = false;

        as.login(username, password);
        return login;
    }

    /**
     * PUT method for updating or creating an instance of RegisterAccount
     *
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String putJson(String content) {
//        return null;
//    }
}