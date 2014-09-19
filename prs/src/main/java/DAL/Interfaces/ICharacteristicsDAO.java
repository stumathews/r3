package DAL.Interfaces;

import DEL.Characteristic;
import DEL.Product;
import java.util.List;

/**
 * Contract for Characteristics Data Access Objects
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface ICharacteristicsDAO 
{
    
    public void addCharacteristic( String name, String description);
    public void addProductCharacteristic(Product product, String title, String description) throws Exception;
    public List<DEL.Characteristic> getProductCharacteristics(String productID) throws Exception;
    public List<BOLO.ProductCharacteristic> getAllCharacteristics() throws Exception;
    public BOLO.ProductCharacteristic Convert( DEL.Characteristic characteristic, DEL.Product product) throws Exception;
    public Characteristic getCharacteristic(Long characteristic_id) throws Exception;
    
}
