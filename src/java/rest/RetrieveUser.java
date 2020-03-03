/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import io.jsonwebtoken.Claims;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import services.AccountService;
import services.JWT;

/**
 *
 * @author 703174
 */
@Path("RetrieveUser")
public class RetrieveUser {

    @Context
    private UriInfo context;

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
        try{
        claims = JWT.decodeJWT(token);
        } catch(Exception e){
            return "Authentication error, bad token";
        } 
        String username = claims.get("username", String.class);
        AccountService as = new AccountService();
        return as.getUser(username).toJSON().toString();
    }
}
