/**
 * This class represents the Administrator service. 
 * Each method represents operation that the service exposes.
 * 
 * This service is used for ad hoc testing and administering of the underlying services application
 */

package WSL;

import BSL.Interfaces.ILoginAdmin;
import WSL.Interfaces.IAdminFacade;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Manages interactions with Administrative web service facilities in the product
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
@SOAPBinding( parameterStyle=ParameterStyle.WRAPPED,
        style=SOAPBinding.Style.RPC,
        use=SOAPBinding.Use.LITERAL)//Optional 
@WebService(serviceName = "AdminFacade",
        portName = "AdminFacadePort",
        targetNamespace = "http://www.stuartmathews.com/AdminFacade")  
public class AdminFacade implements IAdminFacade
{
    @Autowired
    private ILoginAdmin loginAdmin;

    /**
    * Authenticates the client.
    * @param username
    * @param password
    * @return a token hash that can be used on subsequent web service calls.
    * @throws Exception authentication fails.
    */        
    public String Authenticate(@WebParam( name="username") String username, 
                               @WebParam( name="password")  String password) throws Exception
    {      
       return loginAdmin.authenticate(username, password);	        
    }  	
}
