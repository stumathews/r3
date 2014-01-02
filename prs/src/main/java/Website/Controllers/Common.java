/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Website.Controllers;

import BOL.Interfaces.IServiceAuthoriser;
import BSL.Interfaces.ILoginAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Common functionality shared across controllers
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */

@Component
public class Common 
{
    private static ILoginAdmin loginAdmin;  
    
    
    @Autowired
    public void setLoginAdmin(ILoginAdmin loginAdmin) {
        Common.loginAdmin = loginAdmin;
    }
    
    /**
    * Returns the authentication token based on administrator credentials. Uses 
    * LoginAdmin authentication facility
    * @return
    * @throws Exception 
    */
    public static String GetGenAdminAuthToken() throws Exception 
    {                
        return loginAdmin.authenticate("administrator", "apps3cur3");
    }
    /**
     * Gets a token object back while authenticating
     * @return BOLO.Token
     * @throws Exception 
     */
    public static BOLO.Token GenAdminToken() throws Exception
    {        
        return loginAdmin.authenticateGetToken("administrator", "apps3cur3");
    }
}
