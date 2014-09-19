
package BSL;

import BOL.Interfaces.IProduct;
import BOL.Interfaces.IServiceAuthoriser;
import BSL.Interfaces.IProductAdmin;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ProductAdmin implements IProductAdmin
{   
  @Autowired
  public IProduct productLogic;

  @Autowired 
  public IServiceAuthoriser serviceAuthorisor; // Authorisation provider
  
  @Transactional
  public void deleteProductByID(String token, String productID) throws Exception
  {
      getAccess().authorise(token);     
      getLogic().deleteProductsByID(productID);        
  }
  
  @Transactional
  public ArrayList<BOLO.Product> getAllProducts(String token) throws Exception
  {
      getAccess().authorise(token);               
      return getLogic().getAllProducts(token);       
  }
  
  @Transactional
  public void addProduct(String token, BOLO.Product prod) throws Exception
  {        
      getAccess().authorise(token);
      getLogic().addProduct(prod);
  }

  @Transactional
  public BOLO.Product getProductByID(String token, String productID) throws Exception 
  {
      getAccess().authorise(token);
      return getLogic().getProductById(token, productID);
  }

  private IProduct getLogic()
  {
    return this.productLogic;
  }

  private IServiceAuthoriser getAccess()
  {
    return this.serviceAuthorisor;
  }

  @Transactional
  public BOLO.Product MakeProductFormFromId(String token, String productID) throws Exception 
  {
    getAccess().authorise(token);
    return getLogic().MakeProductFormFromId(productID);
  }
    
    
}
