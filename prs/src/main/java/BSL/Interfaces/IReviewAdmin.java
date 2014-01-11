/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BSL.Interfaces;

import BOLO.Review;
import java.util.List;

/**
 * Contract for writing new Review Business Service layers
 * @author Lenovo x220
 */
public interface IReviewAdmin 
{    
    public List<BOLO.Review> getAllReviews(String token) throws Exception;    
    public void SaveReview(String token, Review theReview) throws Exception;
    public List<BOLO.Review> getProductReviews(String token, String productID) throws Exception;
    public List<BOLO.Review> getUserReviews(String username, String tokenString) throws Exception;
}
