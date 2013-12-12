/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL.Interfaces;

import DEL.Characteristic;
import java.util.List;

/**
 * Contract for implementing new Characteristic business logic
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
public interface ICharacteristic 
{
    public void addCharacteristic( String name, String Description);

    public void addProductCharacteristic(String productID, String title, String description, String review) throws Exception;

    public List<BOLO.ProductCharacteristic> getProductCharacteristics(String productID) throws Exception;
    public List<BOLO.ProductCharacteristic> getAllCharacteristics() throws Exception;
}
