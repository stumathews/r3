package BSL.Interfaces;

import java.util.ArrayList;

/**
 * Contract for Product BSL objects
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface IProductAdmin
{
    public void deleteProductByID(String token, String productID) throws Exception;
    public ArrayList<BOLO.Product> getAllProducts(String token) throws Exception;
    public void addProduct(String token, BOLO.Product prod) throws Exception;    
    public BOLO.Product getProductByID(String token, String productID) throws Exception;
    public BOLO.Product MakeProductFormFromId(String token,String productID) throws Exception;
}
