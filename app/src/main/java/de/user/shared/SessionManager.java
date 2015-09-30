package de.user.shared;

import android.app.Application;
 
public class SessionManager extends Application{
     
    private String NAME;
    private String EMAIL;
    private Long ID;
    public  Boolean LOGGED = false;

    public void createGlobalUser(String aName, String aEmail, Long id) {
    	this.NAME = aName;
    	this.EMAIL = aEmail;
    	this.ID = id;
    }
    public String getName() {
        return NAME;
    }
    public String getEmail() {
        return EMAIL;
    }
    public Long getID() {
        return ID;
    }
    public Boolean isLogged() {
    	return LOGGED;
    }
}