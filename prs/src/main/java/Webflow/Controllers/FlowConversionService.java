
package Webflow.Controllers;

import BOLO.ProductCharacteristic;
import BOLO.Wrappers.CharacteristicList;
import BSL.Interfaces.ICharacteristicAdmin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.binding.convert.converters.StringToObject;
import org.springframework.stereotype.Service;


@Service("conversionService")
public class FlowConversionService extends DefaultConversionService
{
  @Autowired    
  public ICharacteristicAdmin characAdmin;
    
  @Override
  protected void addDefaultConverters(){
    super.addDefaultConverters();
    MyCharacteristicConverter myCharacteristicConverter = new MyCharacteristicConverter(List.class);    
    addConverter("myCharacteristicConverter",myCharacteristicConverter);
    
  }

   
  private class MyCharacteristicConverter extends StringToObject  
  {
             
    public MyCharacteristicConverter(Class objectClass) {
      super(objectClass);
    }
        
    @Override
    protected Object toObject(String string, Class type) throws Exception 
    {      
       return characAdmin.getCharacteristicByID(Long.parseLong(string));
      
    }

    @Override
    protected String toString(Object o) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  

    
  }
}
