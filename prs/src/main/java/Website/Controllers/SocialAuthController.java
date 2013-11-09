package Website.Controllers;
 


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
/**
 * Controller that manages /auth routes
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
@RequestMapping(value = "/auth")
@Controller
public class SocialAuthController
{ 
    @Autowired
    private SocialAuthTemplate socialAuthTemplate;
    
    @Autowired
    private BOLO.sessions.SessionUserDetails sessionUserDetails;

    @RequestMapping(value = "/authSuccess")
    public ModelAndView getRedirectURL(final HttpServletRequest request) throws Exception 
    {
        // We're authenticated with something, so save that in the session bean - cool
        sessionUserDetails.setIsAuthenticated(true);
        
        ModelAndView mv = new ModelAndView();
        List<Contact> contactsList = new ArrayList<Contact>();
        SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
        AuthProvider provider = manager.getCurrentAuthProvider();
        

        if( provider == null)
        {                    
            mv.setViewName("Social/authSuccessOnly");
            return mv;
        }

        contactsList = provider.getContactList();
        if (contactsList != null && contactsList.size() > 0) {
                for (Contact p : contactsList) {
                        if (!StringUtils.hasLength(p.getFirstName())
                                        && !StringUtils.hasLength(p.getLastName())) {
                                p.setFirstName(p.getDisplayName());
                        }
                }
        }
        mv.addObject("profile", provider.getUserProfile());
        mv.addObject("contacts", contactsList);
        mv.setViewName("Social/authSuccessWithProfile");

        return mv;
    }
    
    @RequestMapping( method = RequestMethod.GET)
    public String get(ModelMap model) throws Exception
    {
        return "redirect:/auth/start";    
    }
    
    @RequestMapping(value = "/start", method = RequestMethod.GET)    
    public String showSocialAuthPage(ModelMap model) throws Exception
    {
        return "Social/SocialAuthPage";
    }
 
}