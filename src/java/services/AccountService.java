/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.UserDB;
import models.User;

/**
 *
 * @author 553185
 */
public class AccountService {

    public AccountService() {
    }

    /**
     *
     * @param username
     * @return true if an account with the specified username exists
     */
    public boolean checkUsername(String username) {
        UserDB udb = new UserDB();
        return udb.checkUsername(username);
    }

    public boolean checkEmail(String email) {
        UserDB udb = new UserDB();
        return udb.checkEmail(email);
    }

    public boolean register(User user) {
        UserDB udb = new UserDB();
        if (udb.insert(user) > 0) {
            return true;
        }
        return false;
    }

    public boolean updateUser(User user) {
        UserDB udb = new UserDB();
        if (udb.updateUser(user) > 0) {
            return true;
        }
        return false;
    }

    public String changePassword(String username, String password) {
        UserDB udb = new UserDB();

        if (udb.changePassword(username, password) > 0) {
            return JWT.createJWT("testid", username, "accountService", 0);
        }
        
        return null;
    }

}
