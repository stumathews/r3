package BSL;

import BOL.Interfaces.IRecommendation;
import BOL.Interfaces.IServiceAuthoriser;
import BOLO.Recommendation;
import BSL.Interfaces.IRecommendationAdmin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stuartm
 */
public class RecommendationAdmin implements IRecommendationAdmin
{
    private BOL.Interfaces.IRecommendation recommendationBO;
    private IServiceAuthoriser serviceAuthorisor; // Authorisation provider
    
    @Autowired
    public void setAuth(IServiceAuthoriser auth) {
        this.serviceAuthorisor = auth;
    }
    
    @Autowired
    public void setRecommendationBO(IRecommendation recommendationBO) throws Exception 
    {        
        this.recommendationBO = recommendationBO;
    }
    
    @Transactional
    public void createRecommendation(String token, Recommendation theRecommendation) throws Exception 
    {
        serviceAuthorisor.authorise(token);
        recommendationBO.createRecommendation(theRecommendation);                       
    }    
    
    @Transactional
    public List<BOLO.Recommendation> getAllRecommendations(String token) throws Exception 
    {
        serviceAuthorisor.authorise(token);
        return recommendationBO.getAllRecommendations();
    }
}
