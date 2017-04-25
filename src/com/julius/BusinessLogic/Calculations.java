package com.julius.BusinessLogic;

import com.julius.DAO.Database;

/**
 * Created by Julius on 29-03-2017.
 */
public class Calculations implements BLInterface {
    Database db = Database.getDbCon();
    @Override
    public int validate(String name, String pass) {
        return db.getLevel(name, pass);
    }

    public void message(String name, String message){
        db.postMessage(name, message);
    }

    public String getMessages(){
        return db.messages();
    }

    public void createUser(String name, String password){
        db.addUser(name, password);
    }

    public void createAdmin(String name, String password){
        db.addAdmin(name, password);
    }
}
