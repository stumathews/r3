/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BSL;


import BOL.Interfaces.IServiceAuthoriser;
import BOLO.Review;
import BSL.Interfaces.IReviewAdmin;
import DAL.Interfaces.IReviewDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class ReviewAdmin implements IReviewAdmin {

    private BOL.Interfaces.IServiceAuthoriser serviceAuthorisor; // Authorisation provider
    private IReviewDAO reviewDAO;
    private BOL.Review ReviewLogic;
    
    @Autowired
    public void setReviewLogic(BOL.Review ReviewLogic) {
        this.ReviewLogic = ReviewLogic;
    }

    @Autowired
    public void setReviewDAO(IReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }
    
    @Autowired
    public void setAuth(IServiceAuthoriser auth) {
        this.serviceAuthorisor = auth;
    }
    
    @Transactional
    public void SaveReview(String token, BOLO.Review theReview) throws Exception 
    {
        serviceAuthorisor.authorise(token);
        ReviewLogic.SaveReview(theReview);
    }
    
    @Transactional
    public List<BOLO.Review> getAllReviews(String token) throws Exception
    {
        serviceAuthorisor.authorise(token);
        return ReviewLogic.getAllReviews();
    }    

    @Transactional
    public List<BOLO.Review> getProductReviews(String token, String productID) throws Exception 
    {
       serviceAuthorisor.authorise(token); 
       return ReviewLogic.getProductReviews(productID);
    }

    @Transactional
    public List<BOLO.Review> getUserReviews(String username, String tokenString) throws Exception 
    {      
      serviceAuthorisor.authorise(tokenString);
      return ReviewLogic.getUserReviews(username);
    }
}
