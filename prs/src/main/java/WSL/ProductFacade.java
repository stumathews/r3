
package WSL;


import BSL.Interfaces.IProductAdmin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Manages interactions with the product web services
 * @author Stuart Mathews <stuart@stuartmathews.com>
 */
@SOAPBinding( parameterStyle=SOAPBinding.ParameterStyle.WRAPPED,
        style=SOAPBinding.Style.RPC,
        use=SOAPBinding.Use.LITERAL)//Optional 
@WebService(serviceName = "ProductFacade",
        portName = "ProductFacadePort",
        targetNamespace = "http://www.stuartmathews.com/ProductFacade")
public class ProductFacade 
{    
    @Autowired
    private IProductAdmin productAdmin;
 
    /**
     * Adds a product to the database
     * @param token authentication token
     * @param product to add to the db
     * @throws Exception if unable to do so
     */
    @WebMethod(operationName = "addProduct")
    public void CreateProduct(@WebParam(name = "token") String token,
                           @WebParam(name = "product") BOLO.Product product) throws Exception
    {
       productAdmin.addProduct(token, product);        
    }
    
}
