
package BOL;
import BOL.Interfaces.ICharacteristic;
import BOL.Interfaces.IProduct;
import BOL.Interfaces.IReview;
import BOLO.ProductCharacteristic;
import DAL.Interfaces.ICharacteristicsDAO;
import DAL.Interfaces.IProductDAO;
import Utility.SwapableCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
            DEL.Product product = productDAO.getProductByID(productID);
            characteristicDAO.addProductCharacteristic(product, title, description);
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

    public List<BOLO.ProductCharacteristic> getAllCharacteristics() throws Exception {
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

    public List<DEL.Characteristic> ConvertToDels(List<BOLO.ProductCharacteristic> characteristics) throws Exception 
    {
        List<DEL.Characteristic> dels = new ArrayList<DEL.Characteristic>();
        for( BOLO.ProductCharacteristic bch : characteristics)
        {
            DEL.Characteristic dch = new DEL.Characteristic();
            dch.setCreator(null);
            dch.setDescription(bch.getDescription());
            dch.setId(bch.getId());
            dch.setName(bch.getTitle());
            dch.setUseful_value(0);// not used            
        }
        return dels;
    }
}
