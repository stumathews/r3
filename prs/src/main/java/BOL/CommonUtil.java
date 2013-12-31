/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BOL;

import BOL.security.UserAuthService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Quick and easy way to log message to the logger the right way.
 * @author Stuart
 */
public class CommonUtil implements BOL.Interfaces.ICommonUtil
{

    public void justLogMessage(String message) 
    {
        Logger.getLogger("General message Logger").log(Level.WARNING,message);
    }

    public void justLogException(String message, Exception exception) 
    {
        Logger.getLogger("General Exception Logger").log(Level.SEVERE, message, new Exception(message,exception));
    }
}
