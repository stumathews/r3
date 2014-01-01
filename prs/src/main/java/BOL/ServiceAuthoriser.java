
package BOL;

import BOL.Interfaces.ICommonUtil;
import BOL.Interfaces.IServiceAuthoriser;
import BOL.Interfaces.IToken;
import BOLO.R3GlobalConfig;
import BSL.Interfaces.IUserAdmin;
import COMMON.exceptions.OutdatedTokenException;
import DAL.Interfaces.ITokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Jsr250SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * Business logic to authorise login tokens
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class ServiceAuthoriser implements IServiceAuthoriser
{
    private ITokenDAO tokenDAO;
    private IToken tokenBOL;
    private IUserAdmin userAdmin;
    private BOLO.R3GlobalConfig r3config;
    private ICommonUtil commonUtil;

    @Autowired
    public void setCommonUtil(ICommonUtil commonUtil) {
      this.commonUtil = commonUtil;
    }

    @Autowired
    public void setR3config(R3GlobalConfig r3config) {
      this.r3config = r3config;
    }

    @Autowired
    public void setUserAdmin(IUserAdmin userAdmin) {
        this.userAdmin = userAdmin;
    }

    @Autowired
    public void setTokenBOL(IToken tokenBOL) {        
        this.tokenBOL = tokenBOL;
    }   
        
    @Autowired
    public void setTokenDAO(ITokenDAO tokenDAO ) {
        this.tokenDAO = tokenDAO;
    }
    
    /***
    * Verify that the login token is correct
    * @param tokenString from the client
    */
    public void authorise(String tokenString) throws Exception
    {       	
        // Try and get token from the database, proving that this user has authenticated.                        
        BOLO.Token bolo_token = tokenDAO.getToken(tokenString); 
        
        if( bolo_token == null)
        {
          String errorMsgString = "Invalid token";
          commonUtil.justLogMessage(errorMsgString);
          // Throw it outta here
          throw new Exception(errorMsgString);
        }
        
        boolean shouldValidateToken = r3config.isValidateToken();        
        if(shouldValidateToken) // should we validate token with timeout setting in token?
        {
          boolean isInvalidToken = !tokenBOL.isValid(bolo_token);          
          if(isInvalidToken)
          {
              //SecurityContextHolder.clearContext(); // logs out the current user.
              //userAdmin.cleanOldUserTokens(token);
              throw new OutdatedTokenException("Token is outdated. Please re-authenticate.");

          }  
        }

    }
}
