/**
 * Represents the User service administration layer
 */
package BSL;

import BOL.Interfaces.IServiceAuthoriser;
import BOL.Interfaces.IUser;
import BSL.Interfaces.IUserAdmin;
import DEL.User;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserAdmin implements IUserAdmin
{
        
    @Autowired
    public IServiceAuthoriser serviceAuthorisor;  

    @Autowired
    public IUser userBOL;
   
    @Transactional
    public BOLO.User createUser( String token, String username, String password) throws Exception
    {
        getAccess().authorise(token);//authorise provided token
        return getLogic().createUser(username, password);        
    }
  
    @Transactional
    public ArrayList<User> getAllUsers(String token) throws Exception
    {
        getAccess().authorise(token);
        return getLogic().getAllUsers();       
    }

    @Transactional
    public void deleteUser(String token, String username) throws Exception 
    {
        getAccess().authorise(token);
        getLogic().deleteUser(username);                    
    }
    
    @Transactional
    public boolean cleanOldUserTokens(String token) throws Exception 
    {
        getAccess().authorise(token);
        return getLogic().cleanOldUserTokens(token);         
    }	
    
    @Transactional
    public BOLO.User getUser(String token, String username) throws Exception 
    {
        getAccess().authorise(token);
        return getLogic().getUser(username);
    }    
    
    private IServiceAuthoriser getAccess()
    {
      return serviceAuthorisor;
    }
    
    private IUser getLogic()
    {
      return userBOL;
    }
    
}
