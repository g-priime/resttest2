/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import io.jsonwebtoken.Claims;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import services.JWT;

/**
 * Just so I (Hans) can copy and paste stuff
 *
 * @author 703174
 */
@Path(":)")
public class Base {

    public Base() {
    }

    /**
     * Retrieves representation of an instance of rest.
     *
     * @param token
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("token") String token) {
        Claims claims;
        try {
            claims = JWT.decodeJWT(token);
        } catch (Exception e) {
            return null;
        }

        return null;
    }

    /**
     * PUT method for updating or creating an instance of rest
     *
     * @param content representation for the resource
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String putJSON(String content) {
        Claims claims;
        try {
            claims = JWT.decodeJWT(content);
        } catch (Exception e) {
            return null;
        }

        return null;
    }
}
