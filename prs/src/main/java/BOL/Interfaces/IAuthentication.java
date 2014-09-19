package BOL.Interfaces;

import BOLO.Token;

/**
 * Contract for BOL Authentication objects
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface IAuthentication 
{    
    public String authenticate( String username, String password ) throws Exception;
    public Token authenticateGetToken(String username, String password) throws Exception;
}
