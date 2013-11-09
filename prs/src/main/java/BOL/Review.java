/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BOL;

import BOL.Interfaces.IReview;
import DAL.Interfaces.IReviewDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Review business logic
 * @author Stuart Mathews <stumathews@gmail.com>
 */
public class Review implements IReview 
{

    private IReviewDAO reviewDAO;    

    @Autowired
    public void setReviewDAO(IReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }
    
  
    
    public List<BOLO.Review> getAllReviews() throws Exception
    {
        return reviewDAO.getAllReviews();
    }
    

    public void SaveReview(BOLO.Review theReview) throws Exception {
        reviewDAO.SaveReview( theReview );
    }
    
}
