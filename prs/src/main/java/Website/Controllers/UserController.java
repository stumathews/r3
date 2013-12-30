package Website.Controllers;
 
import BOL.Interfaces.IUserSessionManager;
import BSL.Interfaces.ILoginAdmin;
import BSL.Interfaces.IUserAdmin;
import BSL.LoginAdmin;
import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller that manages /user routes
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
@Controller
@RequestMapping( value="/User" )
public class UserController 
{
    private IUserAdmin userAdmin;
    private BSL.Interfaces.ILoginAdmin loginAdmin;
    private BOL.Interfaces.IUserSessionManager userSessionManager; // each logged in user as their own userSessionManager...

    @Autowired
    public void setUserSessionManager(IUserSessionManager userSessionManager) {
        this.userSessionManager = userSessionManager;
    }

    @Autowired
    public void setLoginAdmin(BSL.Interfaces.ILoginAdmin loginAdmin) {
        this.loginAdmin = loginAdmin;        
    }

    @Autowired
    public void setUserAdmin(IUserAdmin userAdmin) 
    {
        this.userAdmin = userAdmin;
    }
    
    /**
     * Default get method, shows all users
     * @param model
     * @return view of all users
     * @throws Exception 
     */
    @RequestMapping( method = RequestMethod.GET)
    public String get(ModelMap model) throws Exception
    {
        return listUsers(model);
    }

    /**
     * Show add user view
     * @return add user view
     */
    @RequestMapping(value = "/ShowAddUser", method = RequestMethod.GET)
    public String addUser(ModelMap model) { 
        model.addAttribute("user", new BOLO.User());
        return "Users/AddUser";
    }

    /**
     * Shows all users view
     * @param model
     * @return all users view
     * @throws Exception 
     */
    @RequestMapping(value = "/ShowUserList", method = RequestMethod.GET)
    public String listUsers(ModelMap model) throws Exception
    {        
        ArrayList<DEL.User> users = userAdmin.getAllUsers(userSessionManager.getTokenString());
        model.addAttribute("users", users);
        return "Users/ShowUsers";
    }

    /**
     * Processes a AddUserForm and adds the user info in it to the db
     * @param form the form backing object aka command object for a user creation form
     * @param model
     * @return list of users view
     * @throws Exception 
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(ModelMap model, @Valid @ModelAttribute("user") BOLO.User user, BindingResult result ) throws Exception 
    {
        if( result.hasErrors() )
        {
            return "Users/AddUser";
        }
        
        String username = user.getUsername();
        String password = user.getPassword();
        userAdmin.createUser(userSessionManager.getTokenString(),username, password);
        return "redirect:/User/ShowUserList";
    }
    
    /**
     * Deletes a user 
     * @param username user name 
     * @param model
     * @return list of users
     * @throws Exception 
     */
    @RequestMapping(value = "/Delete/{user}", method = RequestMethod.GET)
    public String deleteUser( @PathVariable("user") String username, ModelMap model) throws Exception
    {
        userAdmin.deleteUser(userSessionManager.getTokenString(),username);
        return "redirect:/User/ShowUserList";
    }
}

