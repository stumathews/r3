/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL.Interfaces;

/**
 * Contract for implementing new token business logic
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface IToken 
{
    public boolean isValid(BOLO.Token bolo_token) throws Exception;
    public boolean isValid(String token) throws Exception;
    public boolean hasExpired(BOLO.Token bolo_token);
    public boolean cleanOldUserTokens(String token) throws Exception;
}
