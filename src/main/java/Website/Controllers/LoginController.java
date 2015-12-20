package Website.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Deals with the /logon requests
 * @author Stuart
 */
@Controller
public class LoginController 
{
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String login()
    {
        return "login";
    }
}
