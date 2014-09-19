package WSL;

import BOLO.Wrappers.ReviewList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Web service for dealing with reviews.
 * @author Stuart Mathews <stumathews@gmail.com>
 */
@SOAPBinding( parameterStyle=SOAPBinding.ParameterStyle.WRAPPED, style=SOAPBinding.Style.RPC, use=SOAPBinding.Use.LITERAL)//Optional 
@WebService(serviceName = "ReviewFacade", portName = "ReviewFacadePort", targetNamespace = "http://www.stuartmathews.com/ReviewFacade")
public class ReviewFacade 
{
    @Autowired
    private BSL.Interfaces.IReviewAdmin reviewAdmin;
        
    /** 
     * Gets all the reviews
     */
    @WebMethod(operationName = "GetAllReviews")    
    public ReviewList GetAllReviews(@WebParam(name = "token") String token) throws Exception 
    {
        ReviewList ret = new ReviewList();
        ret.items = reviewAdmin.getAllReviews(token);
        return ret;
    }
}

    
