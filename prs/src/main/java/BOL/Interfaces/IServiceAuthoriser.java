package BOL.Interfaces;

/**
 * Authorises a provided token. This might be based on user role, time of day etc...
 * Expired tokens will not be authorised.
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface IServiceAuthoriser 
{    
    public void authorise(String token) throws Exception;
}
