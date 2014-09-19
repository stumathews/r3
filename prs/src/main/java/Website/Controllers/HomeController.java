package Website.Controllers;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 /**
  * Controller that manages / routes
  * @author Stuart Mathews <stuart@stuartmathews.com>
  */
@Controller
@RequestMapping( value="/" )
public class HomeController 
{ 		
    
    
    /**
     * Default get method shows the home page view
     * @param model
     * @return home page view
     * @throws Exception 
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model) throws Exception
    {	
        
        return "Home";
    }	
}