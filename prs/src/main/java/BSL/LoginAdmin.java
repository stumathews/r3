/**
 * This service is responsible for authenticating users
 */
package BSL;


import BOL.Interfaces.IAuthentication;
import BOLO.Token;
import BSL.Interfaces.ILoginAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * Provides authentication facilities. This uses authenticates logic which is normally 
 * not intended to be used by other classes other than this one, as this provides the authentication to the other classes
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class LoginAdmin implements ILoginAdmin
{    
    private IAuthentication authenticationBOL;  
    
    @Autowired
    public void setAuthentication(IAuthentication authentication) 
    {
        this.authenticationBOL = authentication;
    }
    
    /***
    * Authenticate credentials against database
    * @param username the username
    * @param password the password
    * @return a token that can be used in subsequent web service calls
    * @throws Exception when credentials are invalid
    */
    @Transactional
    public String authenticate( String username, String password) throws Exception
    {		
        // No need to check permissions to access this service. 
        // Everyone should be able to at least has to try to authenticate  without authorisation
        return authenticationBOL.authenticate(username, password);        
    }

    public Token authenticateGetToken(String username, String password) throws Exception 
    {
        return authenticationBOL.authenticateGetToken(username, password);
    }
}
