package Website.Controllers;
 
import BOL.Interfaces.IUserSessionManager;
import BSL.Interfaces.ICharacteristicAdmin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 /**
  * Controller that manages / routes
  * @author Stuart Mathews <stuart@stuartmathews.com>
  */
@Controller
@RequestMapping( value="/Characteristic" )
public class CharacteristicController 
{ 
    private ICharacteristicAdmin characteristicAdmin;
    private BOL.Interfaces.IUserSessionManager userSessionManager; // each logged in user as their own userSessionManager...

    @Autowired
    public void setUserSessionManager(IUserSessionManager userSessionManager) {
        this.userSessionManager = userSessionManager;
    }

    @Autowired
    public void setCharacteristicAdmin(ICharacteristicAdmin characteristicAdmin) {
        this.characteristicAdmin = characteristicAdmin;
    }
    
    @RequestMapping(value = "ShowCharacteristics", method = RequestMethod.GET)
    public String get(ModelMap model) throws Exception
    {		
      String token = userSessionManager.GetCurrentUserSession()
                                         .getSessionToken()
                                         .getTokenString();
        List<BOLO.ProductCharacteristic> characteristics = characteristicAdmin.getAllCharacteristics(token);
        model.addAttribute("characteristics", characteristics);
        
        return "Characteristic/ShowCharacteristics";
    }	
}