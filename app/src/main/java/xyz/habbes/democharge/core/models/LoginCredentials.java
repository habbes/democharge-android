package xyz.habbes.democharge.core.models;

/**
 * stores email and password credentials
 * passed to the API for logging in
 * Created by Habbes on 13/10/2016.
 */
public class LoginCredentials {
    public String email;
    public String password;

    /**
     * create credentials with specified email and password
     * @param email
     * @param password
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public LoginCredentials(String email, String password){
        this.email = email;
        this.password = password;
    }

    /**
     * create credentials with null email and password
     * @author Habbes
     * @added 13.10.2016
     * @version 1
     */
    public LoginCredentials(){
        this(null, null);
    }
}
