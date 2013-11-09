/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Interfaces;

import BOLO.Recommendation;
import java.util.List;

/**
 * A contract for implementing new Recommendation Data access objects
 * @author stuartm
 */
public interface IRecommendationDAO 
{
    void createRecommendation( BOLO.Recommendation recommendation) throws Exception;
    public List<Recommendation> getAllRecommendations() throws Exception;
}
