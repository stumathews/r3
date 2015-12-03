package DAL.Interfaces;

import org.hibernate.Session;

/**
 *
 * @author Stuart
 */
public interface IBaseRepository 
{
    public Session getCurrentSession();
}
    
