/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.Claims;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import models.User;
import services.AccountService;
import services.JWT;

/**
 *
 * @author 703174
 */
@Path("update")
public class UpdateAccount {
    /**
     * PUT method for updating or creating an instance of
     *
     * @param content representation for the resource
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String putJson(String content) {
        Claims claims;
        try {
            claims = JWT.decodeJWT(JWT.getToken(content));
        } catch (Exception e) {
            return "Authentication error, bad token";
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        User user = gson.fromJson(content, User.class);
        AccountService as = new AccountService();
        if (as.updateUser(user)) {
            return "Updated";
        }

        return "Not Updated";
    }
}
