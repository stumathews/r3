
package BSL;

import BOL.Interfaces.ICharacteristic;
import BOL.Interfaces.IServiceAuthoriser;
import BSL.Interfaces.ICharacteristicAdmin;
import DEL.Characteristic;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business Service layer for Characteristics
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
@Service
public class CharacteristicAdmin implements ICharacteristicAdmin 
{    
    private IServiceAuthoriser serviceAuthorisor;
    private ICharacteristic characteristicBOL;

    @Autowired
    public void setCharacteristicBOL(ICharacteristic characteristicBOL) 
    {
        this.characteristicBOL = characteristicBOL;
    }     
    
    @Autowired
    public void setAuth(IServiceAuthoriser auth) {
        this.serviceAuthorisor = auth;
    }
    
    @Transactional
    public void addCharacteristic( String token, String name, String description ) throws Exception
    {        
        serviceAuthorisor.authorise(token);            
        characteristicBOL.addCharacteristic(name, description);        
    }

    @Transactional
    public void addProductCharacteristic(String token, String productID, String title, String description, String review) throws Exception
    {       
        serviceAuthorisor.authorise(token);            
        characteristicBOL.addProductCharacteristic(productID, title, description, review);               
    }
    
    
    /**
     * Gets all the characteristics for the product
     * @param token
     * @param productID
     * @return a List of Characteristics
     * @throws Exception 
     */
    @Transactional
    public List<DEL.Characteristic> getProductCharacteristics(String token, String productID) throws Exception
    {
        List<Characteristic> results = new ArrayList<Characteristic>();
        serviceAuthorisor.authorise(token);
        results = characteristicBOL.getProductCharacteristics(productID);        
        return results;
    }
    
    @Transactional
    public List<BOLO.ProductCharacteristic> getAllCharacteristics(String token) throws Exception
    {         
        serviceAuthorisor.authorise(token);
        return characteristicBOL.getAllCharacteristics();  
    }
    
}
