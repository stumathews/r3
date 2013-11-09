package BOL;

import BOL.Interfaces.IUser;
import DAL.Interfaces.ITokenDAO;
import DAL.Interfaces.IUserDAO;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Business Logic for Users in system
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class User implements IUser
{
    private  IUserDAO userDAO;
    private  ITokenDAO tokenDAO;

    @Autowired
    public void setTokenDAO(ITokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }
    
    @Autowired
    public void setUserDAO( IUserDAO userDAO)
    {
        this.userDAO = userDAO;
    }


    /**
        * Creates a user and returns it, represented as a BOLO object
        * @param username
        * @return BOLO object that represents the created user
        * @exception If error occured accessing the database.
        */
    public BOLO.User createUser(String username, String password) throws Exception
    {		
        if(!userDAO.exists(username, password))
            return userDAO.createUser(username, password);	
        else
            return userDAO.getUser(username, password);
    }

    /**
     * Cleans out old, expired tokens for user associated with provided token
     * @param token
     * @return true if successful, false if not
     * @throws Exception 
     */
    public boolean cleanOldUserTokens(String token) throws Exception
    {
        try 
        {
            tokenDAO.cleanOldUserTokens(token);
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

    
    /**
     * Deletes a user
     * @param username
     * @throws Exception 
     */
    public void deleteUser(String username) throws Exception 
    {
        try 
        {
            userDAO.deleteUser(username);
        } 
        catch (Exception e) 
        {
            throw new Exception("Unable to delete user",e);
        }
        
    }

    /**
     * Gets all the users
     * @return ArrayList<DAL.DEL.User>
     * @throws Exception 
     */
    public ArrayList<DEL.User> getAllUsers() throws Exception 
    {
        try 
        {
            return userDAO.getAllUsers();
        } 
        catch (Exception e) 
        {
            throw new Exception("Unable to get all users",e);
        }
    }
	

}
