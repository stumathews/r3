
package BSL;

import BOL.Interfaces.IServiceAuthoriser;
import BSL.Interfaces.IProductAdmin;
import DEL.Product;
import DAL.Interfaces.IProductDAO;
import Website.Controllers.Common;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business Service Layer for Product(s)
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class ProductAdmin implements IProductAdmin
{
    private IProductDAO productDAO;     // DB Access Object for Products
    private IServiceAuthoriser serviceAuthorisor; // Authorisation provider
    
    @Autowired
    public void setAuth(IServiceAuthoriser auth) {
        this.serviceAuthorisor = auth;
    }
    
    @Autowired
    public void setProductDAO(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    
    /**
     * Deletes a product 
     * @param token authentication token
     * @param productID productID the id of the product to delete
     * @throws Exception if cannot delete product or authentication error
     */
    @Transactional
    public void deleteProductByID(String token, String productID) throws Exception
    {
        serviceAuthorisor.authorise(token);
        productDAO.deleteProductByID(productID);        
    }

    /**
     * Gets all products from the database
     * @param token authentication token
     * @return ArrayList of Products
     * @throws Exception if unable to get all products
     */
    @Transactional
    public ArrayList<Product> getAllProducts(String token) throws Exception
    {
        serviceAuthorisor.authorise(token);
        return productDAO.getAllProducts();        
    }

    /**
     * Adds a product to the database
     * @param token auth token
     * @param prod product to add
     * @throws Exception if unable to add product
     */
    @Transactional
    public void addProduct(String token, Product prod) throws Exception
    {        
        serviceAuthorisor.authorise(token);    
        productDAO.addProduct(prod);        
    }

    /**
     * Attempts to retrieve a product by its ID
     * @param token authentication token
     * @param productID product id to fetch
     * @return a product
     * @throws Exception if unable to retrieve product by id
     */
    @Transactional
    public Product getProductByID(String token, String productID) throws Exception 
    {
        serviceAuthorisor.authorise(token);
        return productDAO.getProductByID(productID);        
    }
    
    
}
