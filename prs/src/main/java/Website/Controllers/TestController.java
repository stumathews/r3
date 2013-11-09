package Website.Controllers;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

 /**
  * Controller that manages /test routes
  * @author Stuart Mathews <stuart@stuartmathews.com>
  */
@Controller
@RequestMapping( value="/Test" )
public class TestController 
{ 	
    private BOLO.sessions.ISessionUserDetails sessionUserDetails;
    
    @Autowired
    public void setSessionUserDetails(BOLO.sessions.ISessionUserDetails sessionUserDetails) {
        this.sessionUserDetails = sessionUserDetails;
    }   
    
    @RequestMapping(value="sessions",method = RequestMethod.GET)
    public ModelAndView testSessions(ModelMap model) throws Exception
    {	
        ModelAndView mv = new ModelAndView();
        mv.addObject("sessionUserDetails", sessionUserDetails);
        mv.setViewName("Testing/SessionTest");
        return mv;
    }
    
    @RequestMapping(value="test",method = RequestMethod.GET)
    public ModelAndView test(ModelMap model) throws Exception
    {	
        ModelAndView mv = new ModelAndView();        
        mv.setViewName("Testing/test");
        return mv;
    }
}