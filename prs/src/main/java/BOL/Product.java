package BOL;

import BOL.Interfaces.ICharacteristic;
import DAL.Interfaces.IProductDAO;
import BOL.Interfaces.IProduct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Product business logic
 * @author Lenovo x220
 */
public class Product implements IProduct
{    
  private DAL.Interfaces.IProductDAO productDAO;
  private ICharacteristic characteristicBOL;    

  @Autowired
  public void setCharacteristicBOL(Characteristic characteristicBOL) {
      this.characteristicBOL = characteristicBOL;
  }


  @Autowired
  public void setProductDAO(IProductDAO productDAO) {
      this.productDAO = productDAO;
  }

  public BOLO.Product MakeProductFormFromId(String productID) throws Exception 
  {
    DEL.Product prod = productDAO.getProductByID( productID);
    /* Construct the form binding object aka command object: */
    BOLO.Product newProduct = new BOLO.Product();
    newProduct = productDAO.Convert(prod);
    return newProduct;

  }

  public BOLO.Product ConvertSingle(DEL.Product del_product) throws Exception 
  {
    BOLO.Product product = new BOLO.Product();
    // Convert set to list
    List<DEL.Characteristic> del_characteristics = new ArrayList<DEL.Characteristic>();
    del_characteristics.addAll( del_product.getCharacteristics());

    product.setCharacteristics( characteristicBOL.Convert(del_characteristics)  );
    product.setTitle(del_product.getTitle());
    product.setWhatIsIt(del_product.getWhatIsIt());
    product.setWhoMadeIt(del_product.getWhoMadeIt());

    return product;
  }

  public DEL.Product ConvertToDel(BOLO.Product product) throws Exception
  {
    return productDAO.getProductByID(product.getIdentifier());        
  }

  public void deleteProductsByID(String productID) throws Exception
  {
    productDAO.deleteProductByID(productID);
  }

  public BOLO.Product getProductById(String token, String productID) throws Exception
  {
    return productDAO.Convert( productDAO.getProductByID(productID) );
  }

  public void addProduct(BOLO.Product prod) throws Exception
  {
    DEL.Product delProduct = new DEL.Product(prod.getTitle());
      delProduct.setWhatIsIt(prod.getWhatIsIt());
      delProduct.setWhoMadeIt(prod.getWhoMadeIt());        
      delProduct.setReviews(new HashSet<DEL.Review>());
      delProduct.setCharacteristics(new HashSet<DEL.Characteristic>());
      delProduct.setImageURL("");        

    productDAO.addProduct(delProduct);
  }

  public ArrayList<BOLO.Product> getAllProducts(String token) throws Exception
  {
    ArrayList<BOLO.Product> results = new ArrayList<BOLO.Product>();
    for( DEL.Product del_prod : productDAO.getAllProducts())
    {
      results.add( productDAO.Convert(del_prod));
    }
    return results;
  }

  private Set<DEL.Characteristic> getCharacteristics(BOLO.Product prod) 
  {
    Set<DEL.Characteristic> all = new HashSet<DEL.Characteristic>();
    for( BOLO.ProductCharacteristic productCharacteristic : prod.getCharacteristics())
    {
      DEL.Characteristic c = new DEL.Characteristic();
      c.setDescription(productCharacteristic.getDescription());
      c.setName(productCharacteristic.getTitle());
      all.add(c);
    }
    return all;
  }
    
}
