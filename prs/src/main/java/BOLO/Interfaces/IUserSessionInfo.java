/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BOLO.Interfaces;
import BOLO.*;
/**
 *
 * @author Stuart
 */
public interface IUserSessionInfo 
{
  public User getLoggedInUser();
  public void setLoggedInUser(User loggedInUser);
  public Token getSessionToken();
  public void setSessionToken(Token sessionToken);
}
