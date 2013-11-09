/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Interfaces;

import BOLO.ProductCharacteristic;
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
    public void addProductCharacteristic(Product product, String title, String description, String review) throws Exception;
    public List<Characteristic> getProductCharacteristics(String productID) throws Exception;
    public List<ProductCharacteristic> getAllCharacteristics() throws Exception;
    
}
