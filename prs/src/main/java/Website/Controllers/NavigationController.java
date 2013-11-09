package Website.Controllers;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UrlPathHelper;

 /**
  * Controller that manages /navigation routes
  * @author Stuart Mathews <stuart@stuartmathews.com>
  */
@Controller
@RequestMapping( value="/Navigation" )
public class NavigationController 
{		
    /***
    * Creates the source of the links that will appear in the navigation
    * menu on the left. This is made to be dynamic so that as new ones are
    * needed, they can be added from here or pulled from the db.
    * @param model
    * @param request
    * @return the view which is the navigation menu
    * @throws Exception 
    */
    @RequestMapping(value = "NavMenu", method = RequestMethod.GET)
    public String getNavMenu(ModelMap model, HttpServletRequest request  ) throws Exception
    {	
        Map<String,Map<String,String>> stacks = new HashMap<String,Map<String,String>>();
        Map<String,String> products           = new HashMap<String,String>();        
        Map<String,String> users              = new HashMap<String,String>();
        Map<String, String> reviews           = new HashMap<String,String>();
        Map<String, String> recommendations   = new HashMap<String,String>();
        Map<String, String> characteristics   = new HashMap<String,String>();

          
        products.put("Show all products", "Product/ShowProductList");            
        users.put("Show all users", "User/ShowUserList");
        recommendations.put("Show all recommendations", "Recommendation/ShowRecommendations");
        reviews.put("Show all reviews", "Review/ShowAllReviews");
        characteristics.put("Show all characteristics","Characteristic/ShowCharacteristics");

        stacks.put("Products", products);
        stacks.put("Users", users);        
        stacks.put("Recommendations", recommendations);        
        stacks.put("Reviews", reviews);
        stacks.put("Characteristics", characteristics);

        model.addAttribute("stacks",stacks);
        // Get the current URL
        model.addAttribute("current_url", new UrlPathHelper().getOriginatingRequestUri(request));             
        return "Common/NavMenu"; 
    }

    /***
    * Creates the source of all the links that will appear on the teop menu.
    * @param model
    * @param request
    * @return the view which is the top menu
    * @throws Exception 
    */
    @RequestMapping(value="TopMenu", method = RequestMethod.GET)
    public String getTopMenu(ModelMap model, HttpServletRequest request ) throws Exception
    {	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();       
        
        Map<String,String> links = new HashMap<String,String>();
        /* Links are all relative to '/' ie they are not absolute as in http://server/app*/
        links.put("Home", "");
        links.put("About", "About");
        links.put("Welcome", "Welcome"); 
        model.addAttribute("productName", "R3");            
        model.addAttribute("links",links);        
        if( auth != null)
        {
            model.addAttribute("isAuthenticated", auth.isAuthenticated());
            if( auth.isAuthenticated() )
            {
                String logged_in_user = auth.getName();
                model.addAttribute("logged_in_user", logged_in_user );
            }
        }
        // Get the current URL
        model.addAttribute("current_url", new UrlPathHelper().getOriginatingRequestUri(request));
        return "Common/TopMenu"; 
    }        
}