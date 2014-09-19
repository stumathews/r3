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
    public void setTokenDAO(ITokenDAO tokenDAO)
    {
        this.tokenDAO = tokenDAO;
    }
    
    @Autowired
    public void setUserDAO(IUserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    public BOLO.User createUser(String username, String password) throws Exception
    {		
        if(!userDAO.exists(username, password))
            return userDAO.createUser(username, password);	
        else
            return userDAO.getUser(username, password);
    }
    
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

    public BOLO.User getUser(String username) throws Exception 
    {
      DEL.User user = userDAO.getUser(username);
      return userDAO.toBOLO(user);
    }
	

}
