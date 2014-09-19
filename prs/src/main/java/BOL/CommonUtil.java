package BOL;

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
