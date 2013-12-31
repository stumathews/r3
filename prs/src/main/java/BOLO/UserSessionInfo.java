package BOLO;


import java.io.Serializable;

/**
 * Tracks information about the current user session.
 * @author Stuart
 */
public class UserSessionInfo  implements Serializable
{
    private BOLO.User loggedInUser;
    private BOLO.Token sessionToken;  

    public User getLoggedInUser() 
    {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) 
    {
        this.loggedInUser = loggedInUser;
    }

    public Token getSessionToken() 
    {
        return sessionToken;
    }

    public void setSessionToken(Token sessionToken) 
    {
        this.sessionToken = sessionToken;
    }
      
}
