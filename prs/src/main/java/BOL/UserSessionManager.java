/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BOL;
import BOL.Interfaces.IAuthentication;
import BOL.Interfaces.IUserSessionManager;
import BOL.security.UserAuthService;
import BOLO.UserSessionInfo;
import BSL.Interfaces.IUserAdmin;
import DAL.Interfaces.IUserDAO;
import com.sun.xml.ws.security.impl.policy.Constants;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * Objects to control, manages details about the current user session
 * @author Stuart
 */
public class UserSessionManager implements IUserSessionManager
{
    private UserSessionInfo userSessionInfo = null;
    private IUserAdmin userAdmin;
    private IAuthentication authenticationBOL; 
    private IUserDAO userDAO;

    @Autowired
    public void setIUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    @Autowired
    public void setUserAdmin(IUserAdmin userAdmin) {
        this.userAdmin = userAdmin;
    }
        
    @Autowired
    public void setAuthentication(IAuthentication authentication) 
    {
        this.authenticationBOL = authentication;
    }

    
    
    @Transactional
    public BOLO.UserSessionInfo GetCurrentUserSession() throws Exception 
    {
        if( userSessionInfo == null)
        {
            userSessionInfo = new UserSessionInfo();            
            try
            {
                org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
                if( !auth.isAuthenticated())
                    throw new Exception("Expected user to be logged in while creating user session");
                
                if( auth.getName().isEmpty())
                    throw new Exception("User object is empty");

                UserDetails userDetails = (UserDetails) auth.getPrincipal();
                if( userDetails == null)
                    throw new Exception("Problem while trying to obtain userDetail object from auth principal");
                 
                try
                {
                    DEL.User dUser = userDAO.getUser(auth.getName());
                    BOLO.User bUser = new BOLO.User();
                    
                    bUser.setPassword(dUser.getPassword());
                    bUser.setUsername(dUser.getUsername());
                    
                    BOLO.Token token =  authenticationBOL.authenticateGetToken( dUser.getUsername(), dUser.getPassword());

                    userSessionInfo.setLoggedInUser(bUser);
                    userSessionInfo.setSessionToken(token);
                }
                catch(Exception error)
                {
                    
                    throw new Exception("Unable to find user while getting current user session", error);
                }
            }
            catch(Exception error)
            {
                userSessionInfo = null;
                Logger.getLogger(UserAuthService.class.getName()).log(Level.SEVERE, null, new Exception("Creating user session ",error));
                throw new Exception("Creating user session ",error);
            }
        }       
        
        return userSessionInfo;
    }

    public String getTokenString() throws Exception 
    {
        return GetCurrentUserSession().getSessionToken().getTokenString();
    }
    
}
