package BSL;

import BOL.Interfaces.IServiceAuthoriser;
import BOLO.Recommendation;
import BSL.Interfaces.IRecommendationAdmin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class RecommendationAdmin implements IRecommendationAdmin
{
    @Autowired
    public BOL.Interfaces.IRecommendation recommendationBO;
    
    @Autowired
    public IServiceAuthoriser serviceAuthorisor; // Authorisation provider
  
    @Transactional
    public void createRecommendation(String token, Recommendation theRecommendation) throws Exception 
    {
        getAccess().authorise(token);
        getLogic().createRecommendation(theRecommendation);        
    }    

    @Transactional
    public List<BOLO.Recommendation> getAllRecommendations(String token) throws Exception 
    {
         getAccess().authorise(token);
        return getLogic().getAllRecommendations();
    }
    
    private BOL.Interfaces.IRecommendation getLogic()
    {
      return recommendationBO;
    }
    
    private IServiceAuthoriser getAccess()
    {
      return serviceAuthorisor;
    }
}
