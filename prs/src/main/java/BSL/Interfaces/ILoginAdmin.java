
package BSL.Interfaces;

import BOLO.Token;

/**
 * Contract for implementations of Login Business Service Layer objects
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface ILoginAdmin 
{
    public String authenticate( String username, String password) throws Exception;    

    public Token authenticateGetToken(String administrator, String apps3cur3) throws Exception;
}
