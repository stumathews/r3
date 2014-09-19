package BOL.Interfaces;

import DEL.User;
import java.util.ArrayList;

/**
 * Contract for implementing new User business logic
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface IUser 
{
    public BOLO.User createUser(String username, String password) throws Exception;
    public void deleteUser(String username) throws Exception;
    public ArrayList<User> getAllUsers() throws Exception;
    public boolean cleanOldUserTokens(String token) throws Exception;
    public BOLO.User getUser(String username) throws Exception;
}
