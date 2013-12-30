/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BOL.Interfaces;

/**
 * Blueprint for creating UserSessionManagers
 * @author Stuart
 */
public interface IUserSessionManager 
{

    /**
     * Returns the current user session, if none exists one is created.
     * @return UserSessionInfo
     * @throws Exception 
     */
    public BOLO.UserSessionInfo GetCurrentUserSession() throws Exception;

    public String getTokenString() throws Exception;
    
}
