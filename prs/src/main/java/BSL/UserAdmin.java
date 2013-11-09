/**
 * Represents the User service administration layer
 */
package BSL;

import BOL.Interfaces.IServiceAuthoriser;
import BOL.Interfaces.IUser;
import BSL.Interfaces.IUserAdmin;
import DEL.User;
import DAL.Interfaces.IUserDAO;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business Service layer for User entities
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class UserAdmin implements IUserAdmin
{
    @Autowired
    private IServiceAuthoriser serviceAuthorisor;  
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private IUser userBOL;
    
    /***
    * Creates a new user
    * @param token the token that represents your authenticated status
    * @param username the intended name of the new user to be created.
    */
    @Transactional
    public BOLO.User createUser( String token, String username, String password) throws Exception
    {
        serviceAuthorisor.authorise(token);//authorise provided token
        return userBOL.createUser(username, password);        
    }

    /**
     * Gets all the users in the database
     * @param token auth token
     * @return list of user entities in the database
     * @throws Exception if error getting entities or auth issue
     */
    @Transactional
    public ArrayList<User> getAllUsers(String token) throws Exception
    {
        serviceAuthorisor.authorise(token);
        return userBOL.getAllUsers();       
    }


    /**
     * Deletes a named user
     * @param token authentication token
     * @param username named user to delete
     * @throws Exception 
     */
    @Transactional
    public void deleteUser(String token, String username) throws Exception 
    {
        serviceAuthorisor.authorise(token);
        userBOL.deleteUser(username);                    
    }

    @Transactional
    public boolean cleanOldUserTokens(String token) throws Exception 
    {
        serviceAuthorisor.authorise(token);
        return userBOL.cleanOldUserTokens(token);         
    }	
}
