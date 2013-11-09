/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package COMMON.exceptions;

/**
 *
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class OutdatedTokenException extends Exception 
{

    /***
     * If we come against a token that is not valid, we throw this.
     * @param message 
     */
    public OutdatedTokenException(String message) 
    {
        super(message);
    }
    
}
