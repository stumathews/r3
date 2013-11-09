package BOL;

import BOL.Interfaces.IAuthentication;
import DAL.Interfaces.ITokenDAO;
import DAL.Interfaces.IUserDAO;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Business Logic for authentication
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class Authentication implements IAuthentication
{
        
    private static ITokenDAO tokenDAO;        
    private static IUserDAO userDAO;

    @Autowired
    public void setTokenDAO( ITokenDAO tokenDAO )
    {
        this.tokenDAO = tokenDAO;
    }        

    @Autowired        
    public void setUserDAO( IUserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    /**
        * Default number of minutes a token is valid for.
        */
    private static final int DEFAULT_MINS_VALID = 15;

    /***
        * Authenticate username and password, provide token for 15 minutes if valid credentials are found.
        * @param username the username
        * @param password the password
        * @return token that can be used in subsequent web service calls
        */

    public String authenticate( String username, String password ) throws Exception
    {		
        boolean password_is_correct = areCredentialsValid( username, password );
        if( password_is_correct == false )
        {
            throw new Exception("Invalid Username or password.");
        }
        else
        {		
            boolean store_token = true; //we want to persist it moving forward
            String token = null;			
            token = makeToken(store_token, username, password);			
            return token;
        }
    }


    /** 
        * Produce business logic to create  a UUID that represents a token, store it into the database and give it a DEFAULT_MINS_VALID minute expiry
        * @param auto_persist indicates if we should persist the token entity that is created.
        * @return a string representing the token
        * @throws Exception if persisting causes a problem.
        */

    private static String makeToken(boolean auto_persist, String username, String password) throws Exception 
    {

            UUID 		uuid = UUID.randomUUID();
            BOLO.Token  token = new BOLO.Token();
            Date 		date = new java.util.Date();
            Timestamp 	tstamp = new java.sql.Timestamp( date.getTime() );		        

            token.setToken(uuid.toString());
            token.setIssued_time(tstamp);
            token.setMins_valid(DEFAULT_MINS_VALID);				

            // Ask the Data Access layer to store it for us.
            if(auto_persist == true)
                    tokenDAO.storeToken(token, username, password );				
            return token.getToken();
    }


    /** Checks to see if the provided user name and password match anything in the system
        * @param username
        * @param password
        * @return true if credentials found and match in system, else assume invalid credentials
        */        
    private static boolean areCredentialsValid(String username, String password) throws Exception
    {
            boolean valid = false;

            /* This allows us to create te first user using the Administrator 
            credentials */	
            valid = username.equalsIgnoreCase("Administrator") && password.equals("apps3cur3");

            // Right, if details are still invalid, look for the user's password in the DB
            if( valid == false )
            {			
                // Get get the user by checking if a user with specific password exists in db:
                if( userDAO.getUser( username, password ) != null)
                        return true;
                else
                        return false;			
            }

            return valid;
    }


}
