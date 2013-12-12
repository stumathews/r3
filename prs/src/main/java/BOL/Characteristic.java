
package BOL;
import BOL.Interfaces.ICharacteristic;
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

    public void addProductCharacteristic(String productID, String title, String description, String review) throws Exception
    {
        try
        {
            DEL.Product product = productDAO.getProductByID(productID);
            characteristicDAO.addProductCharacteristic(product, title, description,review);
        }
        catch(Exception e)
        {
            throw new Exception("Unable to add product characteristic",e);
        }
    }

    public List<BOLO.ProductCharacteristic> getProductCharacteristics(String productID) throws Exception 
    {
        //TODO: This should be returning a BOLO object
        List<DEL.Characteristic> results = new ArrayList<DEL.Characteristic>();
        List<BOLO.ProductCharacteristic> ret = new ArrayList<BOLO.ProductCharacteristic>();
        try 
        {
            results = characteristicDAO.getProductCharacteristics(productID);
            for( DEL.Characteristic ch : results )
            {
                BOLO.ProductCharacteristic productChar = new BOLO.ProductCharacteristic();
                productChar.setDescription(ch.getDescription());
                productChar.setProduct( productDAO.Convert(ch.getProduct()) );
                productChar.setReview(ch.getReview());
                productChar.setTitle(ch.getName());
                ret.add(productChar);
            }
        } 
        catch (Exception e) 
        {
            throw new Exception("Unable to get product characteristics.",e);
        }
        return ret;
    }

    public List<BOLO.ProductCharacteristic> getAllCharacteristics() throws Exception {
        return characteristicDAO.getAllCharacteristics();
    }
}
