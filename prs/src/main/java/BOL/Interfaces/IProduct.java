
package BOL.Interfaces;

/**
 * A contract for implementing new product logic
 * @author Stuart Mathews <stumathews@gmail.com>
 */
public interface IProduct 
{
    public BOLO.Product MakeProductFormFromId(String productID) throws Exception;
    
}
