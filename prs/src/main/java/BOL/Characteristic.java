package BOL;

import BOL.Interfaces.ICharacteristic;
import BOL.Interfaces.IProduct;
import BOL.Interfaces.IReview;
import BOLO.ProductCharacteristic;
import DAL.Interfaces.ICharacteristicsDAO;
import DAL.Interfaces.IProductDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Business logic for characteristics
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public class Characteristic implements ICharacteristic
{
    private ICharacteristicsDAO characteristicDAO;
    private IProductDAO productDAO;
    private IProduct productLogic;
    private IReview reviewLogic;
    
    
    @Autowired
    public void setProductLogic(IProduct productLogic) {
        this.productLogic = productLogic;
    }

    @Autowired
    public void setReviewLogic(IReview reviewLogic) {
        this.reviewLogic = reviewLogic;
    }    
    
    @Autowired
    public void setCharacteristicDAO(ICharacteristicsDAO characteristicDAO) {
        this.characteristicDAO = characteristicDAO;
    }
    
    @Autowired
    public void setProductDAO(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    
    
    public void addCharacteristic( String name, String Description)
    {
        characteristicDAO.addCharacteristic(name, Description);
    }

    public void addProductCharacteristic(String productID, String title, String description) throws Exception
    {
      try
      {            
          characteristicDAO.addProductCharacteristic(productDAO.getProductByID(productID), title, description);
      }
      catch(Exception e)
      {
          throw new Exception("Unable to add product characteristic",e);
      }
    }

    public List<BOLO.ProductCharacteristic> getProductCharacteristics(String productID) throws Exception 
    {
      try 
      {
          DEL.Product del_product                     = productDAO.getProductByID(productID);            

          List<BOLO.ProductCharacteristic> bolo_chars = new ArrayList<ProductCharacteristic>();

          for( DEL.Characteristic dch : del_product.getCharacteristics())
          {
            BOLO.ProductCharacteristic bch = new ProductCharacteristic();
            bch.setDescription(dch.getDescription());
            bch.setId(dch.getId());                
            bch.setTitle(dch.getName());

            bolo_chars.add(bch);

          }
          return bolo_chars;
      } 
      catch (Exception e) 
      {
        throw new Exception("Unable to get product characteristics.",e);
      }        
    }

    public List<BOLO.ProductCharacteristic> getAllCharacteristics() throws Exception 
    {
      return characteristicDAO.getAllCharacteristics();
    }

    public List<BOLO.ProductCharacteristic> Convert(List<DEL.Characteristic> characteristics) throws Exception 
    {
      List<BOLO.ProductCharacteristic> all = new ArrayList<ProductCharacteristic>();

      for( DEL.Characteristic del_char : characteristics)
      {
        BOLO.ProductCharacteristic bolo_char = new ProductCharacteristic();
        bolo_char.setDescription(del_char.getDescription());
        bolo_char.setId(del_char.getId());
        BOLO.Product bolo_product = productLogic.ConvertSingle(del_char.getProduct()); 
        bolo_char.setTitle(del_char.getName());
        all.add(bolo_char);
      }

      return all;
    }

    private BOLO.ProductCharacteristic ConvertSingle(DEL.Characteristic ch, DEL.Product product)  throws Exception
    {
        BOLO.ProductCharacteristic pch = new ProductCharacteristic();
          pch.setDescription(ch.getDescription());
          pch.setTitle(ch.getName());
       
        return pch;
    }

    public List<DEL.CharacteristicReview> ConvertToDels(List<BOLO.CharacteristicReview> characteristicReviews) throws Exception 
    {
      List<DEL.CharacteristicReview> dels = new ArrayList<DEL.CharacteristicReview>();
      for( BOLO.CharacteristicReview characteristicReview : characteristicReviews)
      {
        DEL.CharacteristicReview dCharacteristicReview = new DEL.CharacteristicReview();
        DEL.Characteristic dCharacteristic = new DEL.Characteristic();
        DEL.User user = new DEL.User();
        user.setId(0L);
        user.setPassword(characteristicReview.getUser().getPassword());
        user.setUsername(characteristicReview.getUser().getUsername());            

        dCharacteristic.setCreator(user);
        dCharacteristic.setDescription(characteristicReview.getCharacteristic().getDescription());
        dCharacteristic.setId(characteristicReview.getCharacteristic().getId());
        dCharacteristic.setName(characteristicReview.getCharacteristic().getTitle());

        dCharacteristic.setProduct(productDAO.getProductByID(dCharacteristic.getProduct().getId().toString()));
        dCharacteristic.setUseful_value(0);

        dCharacteristicReview.setCharacteristic(dCharacteristic);
        dCharacteristicReview.setId(characteristicReview.getId());
        dCharacteristicReview.setReview_text(characteristicReview.getReview_text());
        dCharacteristicReview.setUser(null);
        dels.add(dCharacteristicReview);
      }
      return dels;
    }

  public DEL.Characteristic getCharacteristicById(Long ID) throws Exception 
  {
    return characteristicDAO.getCharacteristic(ID);
  }

  public List<DEL.Characteristic> getProductDELCharacteristics(String token, String productID) throws Exception 
  {
    DEL.Product del_product  = productDAO.getProductByID(productID);    
    List<DEL.Characteristic> list = new ArrayList<DEL.Characteristic>();
    for( DEL.Characteristic ch : del_product.getCharacteristics())
      list.add(ch);
    return  list;
  }
}
