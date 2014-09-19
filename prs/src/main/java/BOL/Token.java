/**
 * Business Logic for Tokens
 */
package BOL;

import BOL.Interfaces.IToken;
import DAL.Interfaces.ITokenDAO;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;


/** 
 * Represents Token business logic.
 * @author stuart *
 */
public class Token implements IToken
{
	private ITokenDAO tokenDAO;
        
    @Autowired
    public void setTokenDAO(ITokenDAO tokenDAO )
    {
        this.tokenDAO = tokenDAO;
    }
        

	public boolean isValid(BOLO.Token bolo_token) throws Exception
	{	
      if(hasExpired(bolo_token))
        return false;
      else
        return true;
	}
        
        

	public boolean hasExpired(BOLO.Token bolo_token)
	{
      boolean 	isValid = true;
      Date	token_timestamp = bolo_token.getIssued_time();
      int 		token_mins_valid = bolo_token.getMins_valid();
      Date 		date = new Date();
      Calendar 	cal = Calendar.getInstance();

      // Lets see what the time would be if the token expired, ie past its validity	
      cal.setTime(token_timestamp);
      cal.add(Calendar.MINUTE, token_mins_valid);

      Timestamp latest_valid_time = new Timestamp( cal.getTime().getTime());
      Timestamp time_now = new Timestamp( date.getTime());

      // Debug
      System.out.println("Time now: " + time_now.toString());
      System.out.println("Token issued time: " + token_timestamp.toString());
      System.out.println("Token expire time: " + latest_valid_time.toString());

      if( time_now.after(latest_valid_time))
              return true;
      else
              return false;		
		
	}
        

	public boolean cleanOldUserTokens(String token) throws Exception 
    {
        boolean worked = false;			
        if( isValid(token))
        {
            tokenDAO.cleanOldUserTokens(token);
            worked = true;
        }
        else
            throw new Exception(String.format( "Provided token,'%s' is not valid" , token ) );

        return worked;
	}
        
        

	public boolean isValid(String token) throws Exception 
    {
      if( token.isEmpty())
        return false;
      
      BOLO.Token found_token = tokenDAO.getToken(token);
      
      if(isValid(found_token))
              return true;
      else
              return false;		
		
	}
}
