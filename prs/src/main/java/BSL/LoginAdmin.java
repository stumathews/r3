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
    @Autowired
    public IAuthentication authentication;  
        
    @Transactional
    public String authenticate( String username, String password) throws Exception
    {	        
        return getLogic().authenticate(username, password);        
    }

    public Token authenticateGetToken(String username, String password) throws Exception 
    {
        return getLogic().authenticateGetToken(username, password);
    }
    
    private IAuthentication getLogic()
    {
      return authentication;
    }
}
