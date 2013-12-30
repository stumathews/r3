
package BSL.Interfaces;

import DEL.User;
import java.util.ArrayList;

/**
 * Contract for BSL User objects
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface IUserAdmin 
{
    public BOLO.User createUser( String token, String username, String password) throws Exception;
    public ArrayList<User> getAllUsers(String string) throws Exception;
    public void deleteUser(String token, String username) throws Exception;
    public boolean cleanOldUserTokens(String token) throws Exception;
    public BOLO.User getUser(String token, String username) throws Exception;
    
}
