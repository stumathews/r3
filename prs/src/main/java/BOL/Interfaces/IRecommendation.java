/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL.Interfaces;

import BOLO.Recommendation;
import java.util.List;

/**
 * A contract for implementing new recommendation logic
 * @author stuartm
 */
public interface IRecommendation 
{
    public void createRecommendation( BOLO.Recommendation recommendation) throws Exception;

    public List<Recommendation> getAllRecommendations() throws Exception;
}
