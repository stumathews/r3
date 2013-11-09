
package BSL.Interfaces;

/**
 * Contract for implementations of Login Business Service Layer objects
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface ILoginAdmin 
{
    public String authenticate( String username, String password) throws Exception;    
}
