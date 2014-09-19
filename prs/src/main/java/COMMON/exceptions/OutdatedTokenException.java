package COMMON.exceptions;

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
