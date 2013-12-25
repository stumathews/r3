
package BOL.Interfaces;

import BOLO.Product;

/**
 * A contract for implementing new product logic
 * @author Stuart Mathews <stumathews@gmail.com>
 */
public interface IProduct 
{
    public BOLO.Product MakeProductFormFromId(String productID) throws Exception;

    public Product ConvertSingle(DEL.Product product) throws Exception;

    public DEL.Product ConvertToDel(Product product) throws Exception;
    
}
