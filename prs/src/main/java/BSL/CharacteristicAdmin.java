
package BSL;

import BOL.Interfaces.ICharacteristic;
import BOL.Interfaces.IServiceAuthoriser;
import BSL.Interfaces.ICharacteristicAdmin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacteristicAdmin implements ICharacteristicAdmin 
{    
    @Autowired
    public IServiceAuthoriser serviceAuthorisor; 
    @Autowired
    public ICharacteristic characteristicBOL;
    
    @Transactional
    public void addCharacteristic( String token, String name, String description ) throws Exception
    {        
        getAccess().authorise(token);
        getLogic().addCharacteristic(name, description);        
    }
   
    @Transactional
    public void addProductCharacteristic(String token, String productID, String title, String description) throws Exception
    {       
        getAccess().authorise(token);
        getLogic().addProductCharacteristic(productID, title, description);                       
    }  
        
    @Transactional
    public List<BOLO.ProductCharacteristic> getProductCharacteristics(String token, String productID) throws Exception
    {        
        getAccess().authorise(token);
        return getLogic().getProductCharacteristics(productID);
    }
    
    @Transactional
    public List<BOLO.ProductCharacteristic> getAllCharacteristics(String token) throws Exception
    {         
        getAccess().authorise(token);
        return getLogic().getAllCharacteristics();
    }
    
    public void setCharacteristicBOL(ICharacteristic characteristicBOL) 
    {
        this.characteristicBOL = characteristicBOL;
    }     
    
    public void setAuth(IServiceAuthoriser auth) 
    {
        this.serviceAuthorisor = auth;
    }
    
    public ICharacteristic getLogic()
    {
      return characteristicBOL;
    }
    
    public IServiceAuthoriser getAccess() 
    {
      return serviceAuthorisor;
    }
    
}
