/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL.security;

import DAL.Interfaces.IUserDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

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

        
        if( !username.equalsIgnoreCase("administrator"))
        {        
            DEL.User del_user = new DEL.User();
            try
            {
            del_user = userDAO.getUser(username);
            }
            catch( Exception e)
            {
                throw new UsernameNotFoundException("Error getting username.", e);
            } 
            user = new User(del_user.getUsername(), del_user.getPassword(), true, true, true, true, user_authorities);   
            return user;
        }
        else
        {
            String default_admin_password = "apps3cur3";
            CreateAdminDBUser();            
            user = new User(username, default_admin_password, true, true, true, true, user_authorities);   
            return user;
        }
         
    }

    /**
     * Creates the administrator user *if* there isn't one already.
     * @param username
     * @param default_admin_password 
     */
    private void CreateAdminDBUser() 
    {
        try 
        {
            String default_admin_password = "apps3cur3";
            if( !userDAO.exists("administrator", default_admin_password))
                userDAO.createUser("administrator", default_admin_password);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(UserAuthService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
