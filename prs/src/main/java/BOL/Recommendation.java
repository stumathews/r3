/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL;

import BOL.Interfaces.IRecommendation;
import DAL.Interfaces.IRecommendationDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Recommendation business logic
 * @author stuartm
 */
public class Recommendation implements IRecommendation
{
    private IRecommendationDAO recommendationDAO;

    @Autowired
    public void setRecommendationDAO(IRecommendationDAO recommendationDAO) {
        this.recommendationDAO = recommendationDAO;
    }
    
    public void createRecommendation(BOLO.Recommendation recommendation) throws Exception {
        // We can call into the DAl now 
        recommendationDAO.createRecommendation(recommendation);
    }

    public List<BOLO.Recommendation> getAllRecommendations() throws Exception {
        return recommendationDAO.getAllRecommendations();
    }
    
}
