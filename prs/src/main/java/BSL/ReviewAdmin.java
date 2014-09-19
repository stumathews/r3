package BSL;

import BOL.Interfaces.IServiceAuthoriser;
import BSL.Interfaces.IReviewAdmin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class ReviewAdmin implements IReviewAdmin 
{
    @Autowired
    public BOL.Interfaces.IServiceAuthoriser serviceAuthorisor; // Authorisation provider
    
    @Autowired
    public BOL.Review ReviewLogic;
    
    @Transactional
    public void SaveReview(String token, BOLO.Review theReview) throws Exception 
    {
        getAccess().authorise(token);
        getLogic().SaveReview(theReview);
    }
    
    @Transactional
    public List<BOLO.Review> getAllReviews(String token) throws Exception
    {
        getAccess().authorise(token);
        return getLogic().getAllReviews();
    }    

    @Transactional
    public List<BOLO.Review> getProductReviews(String token, String productID) throws Exception 
    {
       getAccess().authorise(token); 
       return getLogic().getProductReviews(productID);
    }

    @Transactional
    public List<BOLO.Review> getUserReviews(String username, String tokenString) throws Exception 
    {      
      getAccess().authorise(tokenString);
      return getLogic().getUserReviews(username);
    }
    
    
     
    private IServiceAuthoriser getAccess()
    {
      return this.serviceAuthorisor;
    }
    private BOL.Review getLogic()
    {
      return this.ReviewLogic;
    }
}
