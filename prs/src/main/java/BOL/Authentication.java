package BOL;

import BOL.Interfaces.IAuthentication;
import BOLO.Token;
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
    private static final int DEFAULT_MINS_VALID = 15;

    public String authenticate( String username, String password ) throws Exception
    {	        
      if( !areCredentialsValid( username, password ) )
      {
          throw new Exception("Invalid Username or password.");
      }
      else
      {		
          boolean store_token = true; //we want to persist it moving forward
          String token = null;			
          token = createToken(store_token, username, password).getTokenString();			
          return token;
      }
    }

    
    private BOLO.Token createToken(boolean auto_persist, String username, String password) throws Exception
    {
      UUID 		uuid = UUID.randomUUID();
      BOLO.Token  token = new BOLO.Token();
      Date 		date = new java.util.Date();
      Timestamp 	tstamp = new java.sql.Timestamp( date.getTime() );		        

      token.setTokenString(uuid.toString());
      token.setIssued_time(tstamp);
      token.setMins_valid(DEFAULT_MINS_VALID);

       if(auto_persist == true)
              tokenDAO.storeToken(token, username, password );	
      return token;

    }

  
    private static boolean areCredentialsValid(String username, String password) throws Exception
    {   
      /* This allows us to create te first user using the Administrator 
      credentials */	
      boolean valid = username.equalsIgnoreCase("Administrator") && password.equals("apps3cur3");

      // Right, if details are still invalid, look for the user's password in the DB
      if( !valid )
      {			
        // Get get the user by checking if a user with specific password exists in db:
        if( userDAO.getUser( username, password ) != null)
          return true;
        else
          return false;			
      }

      return valid;
    }

    public Token authenticateGetToken(String username, String password) throws Exception
    {        
      if( !areCredentialsValid( username, password ) )
      {
          throw new Exception("Invalid Username or password.");
      }
      else
      {		
          boolean store_token = true; //we want to persist it moving forward
          BOLO.Token token = createToken(store_token, username, password);
          return token;
      }
    }

}
