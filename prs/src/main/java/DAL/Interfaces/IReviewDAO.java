package DAL.Interfaces;

import BOLO.Review;
import DEL.User;
import java.util.List;

/**
 * A contract for implementing new Review Data access objects
 * @author Lenovo x220
 */
public interface IReviewDAO {  

    public void SaveReview(Review theReview) throws Exception;
    public List<DEL.Review> getAllReviews();
    public List<DEL.Review> getProductReviews(String productID) throws Exception;
    public List<DEL.Review> getUserReviews(User user) throws Exception;
}
