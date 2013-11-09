/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Interfaces;

import BOLO.Review;
import java.util.List;

/**
 * A contract for implementing new Review Data access objects
 * @author Lenovo x220
 */
public interface IReviewDAO {  

    public void SaveReview(Review theReview);
    public List<BOLO.Review> getAllReviews();
}
