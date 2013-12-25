/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Interfaces;
import DEL.Product;
import java.util.ArrayList;

/**
 * The interface that concerns itself with accessing data for Products.
 * Concrete classes implement this interface.
 * @author Stuart
 */
public interface IProductDAO 
{
    public ArrayList<DEL.Product> getAllProducts() throws Exception;
    public void addProduct(DEL.Product product) throws Exception;
    public void deleteProductByID(String productID) throws Exception;
    public Product getProductByID(String productID) throws Exception;
    public BOLO.Product Convert(DEL.Product delprod) throws Exception;
}
