package WSL.Interfaces;

/**
 * AdminFacade contract
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface IAdminFacade 
{    
    public String Authenticate( String username, String password) throws Exception;    
    public BOLO.Token AuthenticateGetToken( String username, String password) throws Exception;   
}
