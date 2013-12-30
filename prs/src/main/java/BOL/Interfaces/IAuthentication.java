/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL.Interfaces;

import BOLO.Token;

/**
 * Contract for BOL Authentication objects
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface IAuthentication 
{
    /**
     * Authenticates user and gets a token string as return value
     * @param username
     * @param password
     * @return string representing token UUID
     * @throws Exception 
     */
    public String authenticate( String username, String password ) throws Exception;
    public Token authenticateGetToken(String username, String password) throws Exception;
}
