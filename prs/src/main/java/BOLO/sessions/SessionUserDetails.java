/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOLO.sessions;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Keeps track of user information during their session
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionUserDetails implements ISessionUserDetails
{
    private boolean isAuthenticated = false;

    public boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
    
    
}
