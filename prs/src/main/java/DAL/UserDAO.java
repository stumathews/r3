
package DAL;

import DAL.Interfaces.IUserDAO;
import DEL.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



/**
 * This represents the data access layer User class.
 * Its primary objective is to interface with the data entity later and construct BOLO objects for the BOL layer.
 * This is capable of being injected as a dependency as it interfaces with IUser
 */
public class UserDAO implements IUserDAO 
{
        private SessionFactory sessionFactory;
        
        @Autowired
        public void setSessionFactory( SessionFactory sessionFactory)
        {
            this.sessionFactory = sessionFactory;
        }
	/**
	 * Creates a user
	 * @param username
	 * @param password
	 * @return BOLO.User
	 * @throws Exception if cannot persist user to the database
	 */        
	public  BOLO.User createUser(String username, String password) throws Exception
	{
		DEL.User del_user = new DEL.User();
		del_user.setUsername(username);
		del_user.setPassword(password);
		
		Session session = sessionFactory.getCurrentSession();		
		session.persist(del_user);
				
		String query = String.format("from User where username = '%s' and password = '%s'", username, password);
		List results = session.createQuery(query).list();
		
		
		for( DEL.User result_user : (List<DEL.User>)results)
		{
			BOLO.User bol_user = new BOLO.User();
				bol_user.setUsername(result_user.getUsername());
				bol_user.setPassword(result_user.getPassword());
				return bol_user;
		}
		throw new Exception("There was a problem creating the user.");
		
	}
        
	/***
         * Gets a User database entity
         * @param username
         * @param password
         * @return a DAL.user         
         */
	public  DEL.User retrieve(String username, String password)
	{
            Session session = sessionFactory.getCurrentSession();

            String query = String.format("from User where username = '%s' and password = '%s'",username,password);
            List<?> results = session.createQuery(query).list();

            for( DEL.User user : (List<DEL.User>)results){			
                    return user;
            }
            return null;
		
	}
        
        /***
         * Checks to see if the user exists in the database
         * @param username
         * @param password
         * @return true if the user exists, false otherwise         
         */
	public  boolean exists(String username, String password)
	{
            Session session = sessionFactory.getCurrentSession();

            String query = String.format("from User where username = '%s' and password = '%s'",username,password);
            List results = session.createQuery(query).list();

            if( !results.isEmpty() ) 
                    return true;
            else
                    return false;
	}
        
        /***
         * Gets a user with matching password
         * @param username
         * @param password
         * @return a User business Object representing the username/password combination
         * @throws HibernateException
         * @throws Exception 
         */
	public  BOLO.User getUser(String username, String password) throws HibernateException, Exception 
        {		
            Session session = sessionFactory.getCurrentSession();

            String query = String.format("from User where username = '%s' and password = '%s'", username, password);
            
            List<?> results = session.createQuery(query).list();

            for( DEL.User user : (List<DEL.User>)results){
                    BOLO.User bol_user = new BOLO.User();
                    bol_user.setPassword(user.getPassword());
                    bol_user.setUsername(user.getUsername());
                    return bol_user;
            }		
            return null;
		
	}
        
        /***
         * Gets all the users in the database
         * @return A list of user entities         
         */
	public  ArrayList<DEL.User> getAllUsers()
	{
            ArrayList<DEL.User> users = new ArrayList<DEL.User>();
            
            Session session = sessionFactory.getCurrentSession();
            users =(ArrayList<DEL.User>) session.createQuery("from User").list();

            return users;
		
	}
        /***
         * Deletes a user from the database.
         * @param username the username for the user entity to delete
         * @throws Exception 
         */
	public void deleteUser(String username)
        {
            Session session = sessionFactory.getCurrentSession();				
            session.createQuery(String.format("delete from User where username = '%s'", username)).executeUpdate();                
	}
        
        /***
         * Fetches a named user object from the database
         * @param username
         * @return DEL.User object belonging to the specified username passed in.
         * @throws Exception when cannot find user or a problem attempting to was encountered
         */
        public DEL.User getUser(String username) throws Exception 
        {
            List<User> results = new ArrayList<User>();
            User user = new User();
            Session session = sessionFactory.getCurrentSession();
            
            results = (List<User>) session.createQuery(String.format("from User where username = '%s'",username)).list();
            if( results.isEmpty())
                throw new Exception(String.format("Could not fetch user from database: No results found for username '%s'", username));

            // we only expect one result, so if we get multiple error out too
            if( results.size() > 1)
                throw new Exception(String.format("Could not fetch user from database: Multiple results found for username '%s'", username));
            
            user = results.get(0);                
            
            return user;
        }
        
        /***
         * Converts a DEL.user object into the User Business Object
         * @param del_user the database entity
         * @return Business Object representing a single user
         */
        public BOLO.User toBOLO( DEL.User del_user)
        {
            BOLO.User bolo_user = new BOLO.User();
            
            bolo_user.setUsername(del_user.getUsername());
            bolo_user.setPassword(del_user.getPassword());
            
            return bolo_user;
        }
              
        /***
         * Converts a User Business Object into a database entity
         * @param bolo_user The user business object
         * @return a user database entity         
         */
        public DEL.User toDEL(BOLO.User bolo_user) {
        DEL.User del_user = new DEL.User();
            del_user.setUsername(bolo_user.getUsername());
            del_user.setPassword(bolo_user.getPassword());            
        return del_user;
    }
}
