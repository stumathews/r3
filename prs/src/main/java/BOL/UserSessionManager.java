package BOL;

import BOL.Interfaces.IAuthentication;
import BOL.Interfaces.ICommonUtil;
import BOL.Interfaces.IUserSessionManager;
import BOLO.Interfaces.IUserSessionInfo;
import BOLO.UserSessionInfo;
import BSL.Interfaces.IUserAdmin;
import DAL.Interfaces.IUserDAO;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Objects to control, manages details about the current user session
 * @author Stuart
 */
@Component
public class UserSessionManager implements IUserSessionManager, Serializable
{
    private IUserSessionInfo userSessionInfo; // this is a scoped proxy session object. There is a unique one instantiated for each session   
    private IUserAdmin userAdmin;
    private IAuthentication authenticationBOL; 
    private IUserDAO userDAO;    
    private ICommonUtil commonUtil;

    @Autowired
    public void setCommonUtil(ICommonUtil commonUtil) {
        this.commonUtil = commonUtil;
    }

    @Autowired
    public void setUserSessionInfo(IUserSessionInfo userSessionInfo) 
    {
       this.userSessionInfo = userSessionInfo;
    }
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

    
    /**
     * Gets the current Session Token for the user. 
     * @return
     * @throws Exception 
     */ 
    @Transactional
    public BOLO.Interfaces.IUserSessionInfo GetCurrentUserSession() throws Exception 
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
                    DEL.User dUser =  userDAO.getUser(auth.getName());
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
                commonUtil.justLogException("Error creating user session", error);
                throw new Exception("Creating user session ",error);
            }
              
        
        return userSessionInfo;
    }   
    
}
