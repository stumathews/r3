
package DAL.Interfaces;

import java.util.ArrayList;
import org.hibernate.HibernateException;

/**
 * Contract for implementations of user data access objects
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface IUserDAO 
{
    public BOLO.User createUser(String username, String password) throws Exception;
    public DEL.User retrieve(String username, String password);
    public boolean exists(String username, String password);
    public BOLO.User getUser(String username, String password) throws HibernateException, Exception;
    public ArrayList<DEL.User> getAllUsers();
    public void deleteUser(String username);
    public DEL.User getUser(String username) throws Exception;
    public BOLO.User toBOLO( DEL.User del_user);
    public DEL.User toDEL( BOLO.User bolo_user);
}
