package BOL.Interfaces;

import BOLO.Product;
import java.util.ArrayList;

/**
 * A contract for implementing new product logic
 * @author Stuart Mathews <stumathews@gmail.com>
 */
public interface IProduct 
{
    public BOLO.Product MakeProductFormFromId(String productID) throws Exception;
    public Product ConvertSingle(DEL.Product product) throws Exception;
    public DEL.Product ConvertToDel(Product product) throws Exception;
    public ArrayList<BOLO.Product> getAllProducts(String token) throws Exception;
    public void addProduct(Product prod) throws Exception;
    public BOLO.Product getProductById(String token, String productID) throws Exception;
    public void deleteProductsByID(String productID) throws Exception;
    
}
