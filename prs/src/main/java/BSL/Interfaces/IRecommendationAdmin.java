package BSL.Interfaces;

import java.util.List;

/**
 * Contract for writing new Recommendation Business Service layers
 * @author stuartm
 */
public interface IRecommendationAdmin 
{
    /**
     * Saves a recommendation entity into the database
     * @param token
     * @param theRecommendation
     * @throws Exception
     */
    public void createRecommendation(String token, BOLO.Recommendation theRecommendation) throws Exception; 
    public List<BOLO.Recommendation> getAllRecommendations(String GetGenAdminAuthToken) throws Exception;
}
