
package BSL.Interfaces;

import DEL.Characteristic;
import DEL.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Contract for Product BSL objects
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface IProductAdmin
{
    public void deleteProductByID(String token, String productID) throws Exception;
    public ArrayList<Product> getAllProducts(String token) throws Exception;
    public void addProduct(String token, DEL.Product prod) throws Exception;    
    public Product getProductByID(String token, String productID) throws Exception;

   
    
}
