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
import models.Dog;
import services.DogService;
import services.JWT;

/**
 *
 * @author 703174
 */
@Path("updateDog")
public class UpdateDog {
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String putJson(String content) {
        String token = JWT.getToken(content);
        Claims claims;
        try{
        claims = JWT.decodeJWT(token);
        } catch(Exception e){
            return "Authentication error, bad token";
        } 
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Dog dog = gson.fromJson(content, Dog.class);
        DogService ds = new DogService();
        if (ds.updateDog(dog)) {
            return "Updated";
        }
        
        return "Not Updated";
    }
}
