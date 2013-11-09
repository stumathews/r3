
package BOL.Interfaces;

/**
 * Authorises a provided token. This might be based on user role, time of day etc...
 * Expired tokens will not be authorised.
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface IServiceAuthoriser 
{
    /**
     * Authorises a provided token. This might be based on user role, time of day etc...
     * Expired tokens will not be authorised.
     * @param token
     * @throws Exception that indicates an invalid token.
     */
    public void authorise(String token) throws Exception;
}
