package Website.Controllers;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
/**
 * Welcome controller manages /welcome routes
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
@Controller
@RequestMapping(value = "/Welcome")
public class WelcomeController 
{
    /**
     * Default get method fetches welcome view
     * @param model
     * @return welcome view
     */
    @RequestMapping(method = RequestMethod.GET)	
    public String printWelcome(ModelMap model) 
    { 
        model.addAttribute("message", "Welcome to the welcome page");
        return "Welcome"; 
    }
 
}