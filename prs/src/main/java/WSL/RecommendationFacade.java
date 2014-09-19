package WSL;

import BOLO.Wrappers.RecommendationList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Recommendation web service
 * @author Stuart Mathews <stumathews@gmail.com>
 */
@SOAPBinding( parameterStyle=SOAPBinding.ParameterStyle.WRAPPED, style=SOAPBinding.Style.RPC, use=SOAPBinding.Use.LITERAL)//Optional 
@WebService(serviceName = "RecommendationFacade",portName = "RecommendationFacadePort", targetNamespace = "http://www.stuartmathews.com/RecommendationFacade")
public class RecommendationFacade 
{
    @Autowired
    private BSL.Interfaces.IRecommendationAdmin recommendationAdmin;
        
    /**
     * Get all the recommendations in the systems
     */
    @WebMethod(operationName = "GetAllRecommendations")
    public RecommendationList GetAllRecommendations(@WebParam(name = "token") String token) throws Exception
    {
        RecommendationList result = new RecommendationList();        
        result.items = recommendationAdmin.getAllRecommendations(token);
        return result;     
   
    }
}
