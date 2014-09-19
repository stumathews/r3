package BOL.Interfaces;

/**
 * Blueprint for creating UserSessionManagers
 * @author Stuart
 */
public interface IUserSessionManager 
{
    public BOLO.Interfaces.IUserSessionInfo GetCurrentUserSession() throws Exception;
 
}
