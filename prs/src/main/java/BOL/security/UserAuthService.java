package BOL.security;

import DAL.Interfaces.IUserDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is our custom Authentication Provider for spring-security.
 * Its responsibility is to provide the Authorities belonging to a user in
 * the form of MyGantedAuthority objects and a User object which will bring 
 * together the username,password and and Authorities for the user.
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
@Repository
public class UserAuthService implements UserDetailsService
{
     
    private IUserDAO userDAO;
   
    
    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    
    /**
     * Constructs a UserDetail object by querying the db for the username provided
     * @param username
     * @return UserDetail representing the usernames details including password
     * @throws UsernameNotFoundException if no user was found at all.
     */
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDetails user = null;
        
        List<DefaultUserAuthority> user_authorities = new ArrayList<DefaultUserAuthority>();
        /*
        * Add a authority for this user - we dont have authorities inplemented in our app yet,
        * allways assume that a valid username/password has DefaultUserAuthority Role
        */
        DefaultUserAuthority default_authority = new DefaultUserAuthority();
        user_authorities.add(default_authority);  
        // Construct a valid UserDetail object which spring-security expects...

        boolean isAdmin = username.equalsIgnoreCase("administrator");
        
        if(!isAdmin)
        {  
            try
            {
                DEL.User del_user = userDAO.getUser(username);
                user = new User(del_user.getUsername(), del_user.getPassword(), true, true, true, true, user_authorities); 
            }
            catch( Exception e)
            {
                throw new UsernameNotFoundException("Error getting username.", e);
            }            
        }
        else
        {    
            try
            {
                return CreateAdminDBUser(user_authorities);  
            }
            catch(Exception error)
            {
                throw new UsernameNotFoundException("Error creating admin user",error);
            }
        }
        
        return user;
         
    }

    /**
     * Creates the administrator user *if* there isn't one already.
     * @param username
     * @param default administrator password 
     */
    private UserDetails CreateAdminDBUser(List<DefaultUserAuthority> user_authorities) throws Exception
    {        
        String default_admin_password = "apps3cur3";
        String default_admin_username = "administrator";
        if( !userDAO.exists(default_admin_username, default_admin_password))
            userDAO.createUser(default_admin_username, default_admin_password);

        return new User(default_admin_username, default_admin_password, true, true, true, true, user_authorities);  
        
    }
    
}
