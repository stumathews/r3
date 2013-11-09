/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL.Interfaces;

import BOLO.Review;
import java.util.List;

/**
 * A Contract for writing Review business logic
 * @author Lenovo x220
 */
public interface IReview {
    
    public void SaveReview(Review theReview) throws Exception;

    public List<BOLO.Review> getAllReviews() throws Exception;
}
