/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL.Interfaces;

/**
 * Contract for BOL Authentication objects
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface IAuthentication 
{
    public String authenticate( String username, String password ) throws Exception;
}
