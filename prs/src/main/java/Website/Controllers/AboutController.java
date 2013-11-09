package Website.Controllers;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
/**
 * Controller that manages /about routes
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
@Controller
@RequestMapping("/About")
public class AboutController
{ 
    /**
     * Default get method show the about page view
     * @param model
     * @return show the about page view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String welcome(ModelMap model)
    {                
        model.addAttribute("message", "Welcome to the about page");
        return "About";
    }
 
}